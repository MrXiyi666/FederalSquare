package fun.android.federal_square.data;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import fun.android.federal_square.App;

public class VideoCacheDataSourceFactory implements DataSource.Factory {
    private final Context context;
    private final DefaultDataSourceFactory defaultDataSourceFactory;

    public VideoCacheDataSourceFactory(Context context) {
        this.context = context;
        this.defaultDataSourceFactory = new DefaultDataSourceFactory(context, "video_cache");
    }

    @Override
    public DataSource createDataSource() {
        return new CacheDataSource(
                App.getVideoCache(),
                defaultDataSourceFactory.createDataSource(),
                CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);
    }
}
