package fun.android.federal_square.NetWork;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class ProgressRequestBody extends RequestBody {

    private final File file;
    private final MediaType mediaType;
    private final ProgressListener listener;

    public ProgressRequestBody(File file, MediaType mediaType, ProgressListener listener) {
        this.file = file;
        this.mediaType = mediaType;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public long contentLength() throws IOException {
        return file.length(); // 文件总长度
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        long fileLength = contentLength();
        long bytesWritten = 0;

        try (Source source = Okio.source(file)) {
            Buffer buffer = new Buffer();
            long readCount;
            // 使用 while 循环，逻辑更直观
            while ((readCount = source.read(buffer, 8192)) != -1) {
                sink.write(buffer, readCount);
                bytesWritten += readCount;
                listener.onProgress(bytesWritten, fileLength);
            }
        }
    }



    // 进度回调接口
    public interface ProgressListener {
        void onProgress(long bytesWritten, long contentLength);
    }

}
