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
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AlertDialog dialog = new AlertDialog.Builder(activity, R.style.AlertDialog_Null).create();
        View view = View.inflate(activity, R.layout.window_video_view, null);
        final ExoPlayer player = new ExoPlayer.Builder(activity).build();
        PlayerView playerView = view.findViewById(R.id.video_view);
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
                    window.setNavigationBarColor(Color.BLACK);
                    window.setStatusBarColor(Color.BLACK);
                    playerView.hideController();
                }
            }

            @Override
            public void onPlayerErrorChanged(@Nullable PlaybackException error) {
                Player.Listener.super.onPlayerErrorChanged(error);
                Fun.mess(activity, "播放错误", 2000);
                player.release();
                dialog.dismiss();
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                window.setNavigationBarColor(Color.WHITE);
                window.setStatusBarColor(Color.TRANSPARENT);
            }
        });

        dialog.setOnCancelListener(dialog1 -> {
            player.release();
            dialog.dismiss();
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            window.setNavigationBarColor(Color.WHITE);
            window.setStatusBarColor(Color.TRANSPARENT);
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
