package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AlertDialog;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import java.util.Objects;
import fun.android.federal_square.R;
import fun.android.federal_square.data.VideoCacheDataSourceFactory;
import fun.android.federal_square.fun.Fun;

public class 查看视频窗口 {
    private static ExoPlayer player;
    public static void 启动_Dialog(Activity activity, String url){
        player = null;
        Window window = activity.getWindow();
        window.setNavigationBarColor(Color.BLACK);
        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Null).create();
        View view = View.inflate(activity, R.layout.window_video_view, null);
        RelativeLayout relati = view.findViewById(R.id.relati);
        player = new ExoPlayer.Builder(activity).build();
        PlayerView playerView = view.findViewById(R.id.video_view);
        VideoCacheDataSourceFactory dataSourceFactory = new VideoCacheDataSourceFactory(activity);
        MediaItem mediaItem = MediaItem.fromUri(url);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem);
        player.setMediaSource(mediaSource);
        player.prepare();
        player.play();
        playerView.setPlayer(player);
        player.addListener(new Player.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_ENDED) {

                }
                if(playbackState == Player.STATE_READY){

                }
            }
            @Override
            public void onPlayerError(PlaybackException error) {
                Player.Listener.super.onPlayerError(error);
                Fun.mess(activity, "播放错误");
                if(player!=null){
                    player.release();
                    player = null;
                }
                if(dialog!=null){
                    dialog.dismiss();
                }
                Window window = activity.getWindow();
                window.setNavigationBarColor(Color.WHITE);
            }
        });
        dialog.setOnCancelListener(dialog1 -> {
            if(player!=null){
                player.release();
                player = null;
            }
            if(dialog!=null){
                dialog.dismiss();
            }
            Window window1 = activity.getWindow();
            window1.setNavigationBarColor(Color.WHITE);
        });
        dialog.setView(view);
        dialog.setCancelable(true);
        Objects.requireNonNull(dialog.getWindow()).clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
    }
}
