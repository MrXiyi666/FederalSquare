package fun.android.federal_square.data;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;

public class ProgressRequestBody extends RequestBody {
    private final RequestBody requestBody;
    private final ProgressListener progressListener;
    private long bytesWritten = 0L;

    public interface ProgressListener {
        void onProgress(long bytesWritten, long contentLength, boolean done);
    }

    public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        BufferedSink bufferedSink = Okio.buffer(new ForwardingSink(sink) {
            @Override
            public void write(@NonNull Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                bytesWritten += byteCount;
                emitProgress(false);
            }
        });

        try {
            requestBody.writeTo(bufferedSink);
            bufferedSink.flush();
            emitProgress(true);
        } catch (IOException e) {
            emitProgress(true);
            throw e;
        }
    }

    private void emitProgress(boolean done) {
        long contentLength;
        try {
            contentLength = contentLength();
        } catch (IOException e) {
            contentLength = -1; // 处理异常，传递未知长度
        }
        progressListener.onProgress(bytesWritten, contentLength, done);
    }
}