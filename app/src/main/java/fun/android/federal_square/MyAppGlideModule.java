package fun.android.federal_square;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.GlideBuilder;
import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 自定义磁盘缓存路径和大小
        String cacheDir = "image_cache";
        int diskCacheSizeBytes = 1024 * 1024 * 1000;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheDir, diskCacheSizeBytes));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }
}
