package fun.android.federal_square.window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AlertDialog;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.ui.PlayerView;
import java.util.Objects;
import fun.android.federal_square.App;
import fun.android.federal_square.R;
import fun.android.federal_square.fun.Fun;
import fun.android.federal_square.fun.Fun_账号;

public class 查看视频窗口 {

    @OptIn(markerClass = UnstableApi.class)
    public static void 启动_Dialog(Activity activity, String url){
        if(Fun_账号.GetID().isEmpty()){
            Fun.mess(activity, "没有登陆 无法查看");
            return;
        }
        activity.getWindow().setNavigationBarColor(Color.rgb(255,255,255));
        activity.getWindow().setStatusBarColor(Color.rgb(255,255,255));
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        View view = View.inflate(activity, R.layout.window_video_view, null);
        PlayerView playerView = view.findViewById(R.id.video_view);
        final ExoPlayer player = new ExoPlayer.Builder(activity).build();
        MediaItem mediaItem = MediaItem.fromUri(url);
        DefaultHttpDataSource.Factory httpDataSourceFactory = new DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(true);
        DefaultDataSource.Factory defaultDataSourceFactory = new DefaultDataSource.Factory(activity, httpDataSourceFactory);
        CacheDataSource.Factory cacheDataSourceFactory = new CacheDataSource.Factory()
                .setCache(App.getVideoCache())
                .setUpstreamDataSourceFactory(defaultDataSourceFactory)
                .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);

        MediaSource mediaSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(mediaItem);
        player.setMediaSource(mediaSource, true);
        playerView.setPlayer(player);
        player.prepare();
        player.play();
        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(@Player.State int state) {
                if (state == Player.STATE_READY) {
                    playerView.hideController();
                    activity.getWindow().setNavigationBarColor(Color.rgb(0,0,0));
                    activity.getWindow().setStatusBarColor(Color.rgb(0,0,0));
                }else if(state == Player.STATE_ENDED){
                    new Thread(()->{
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        view.post(()->{
                            player.seekTo(0);
                            player.play();
                        });
                    }).start();
                }
            }

            @Override
            public void onPlayerErrorChanged(@Nullable PlaybackException error) {
                Player.Listener.super.onPlayerErrorChanged(error);
                Fun.mess(activity, "播放错误", 2000);
                player.release();
                dialog.dismiss();
                activity.getWindow().setNavigationBarColor(Color.rgb(255,255,255));
                activity.getWindow().setStatusBarColor(Color.rgb(255,255,255));
            }
        });

        dialog.setOnCancelListener(V -> {
            player.release();
            dialog.dismiss();
            activity.getWindow().setNavigationBarColor(Color.rgb(255,255,255));
            activity.getWindow().setStatusBarColor(Color.rgb(255,255,255));
        });
        dialog.setView(view);
        Objects.requireNonNull(dialog.getWindow()).getDecorView().setPadding(0, 0, 0, 0);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
    }
}
