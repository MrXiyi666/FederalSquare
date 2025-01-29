package fun.android.federal_square;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

@GlideModule
public final class MyAppGlideModule extends AppGlideModule{

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        int diskCacheSize = 1024 * 1024 * 10000;

        builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "glide_cache", diskCacheSize));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(999999);
        dispatcher.setMaxRequestsPerHost(999999);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .dispatcher(dispatcher)
                .build();
        registry.replace(GlideUrl.class, InputStream.class,new OkHttpUrlLoader.Factory(okHttpClient));
    }
}