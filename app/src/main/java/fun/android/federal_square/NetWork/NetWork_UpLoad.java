package fun.android.federal_square.NetWork;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;

import fun.android.federal_square.system.Static;
import fun.android.federal_square.window.Window_Progress_Bar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class NetWork_UpLoad {
    private final Activity activity;
    private final String class_name;
    private String file_name;
    private final Window_Progress_Bar window_progress_bar;

    public NetWork_UpLoad(Activity activity){
        this.activity = activity;
        this.class_name = this.getClass().getSimpleName();
        window_progress_bar = new Window_Progress_Bar(activity);
    }

    /**
     * 刷新进度条UI
     * @param progress 下载进度 (0-100)
     */
    private void update(int progress){
        window_progress_bar.setProgress(progress);
    }

    public void Start(String url, File file){
        if(file == null || !file.exists()){
            Log.e(class_name, "文件为空或不存在");
            window_progress_bar.setText("文件为空或不存在");
            window_progress_bar.close();
            return;
        }
        file_name = file.getName();
        if (file_name.isEmpty()) {
            Log.e(class_name, "文件名为空");
            window_progress_bar.setText("文件名为空");
            window_progress_bar.close();
            return;
        }

        // --- 核心修改点 1: 使用 ProgressRequestBody 包装文件 ---
        // 1. 猜测 MIME 类型
        MediaType mediaType = MediaType.parse(guessMimeType(file_name));
        // 2. 创建 ProgressRequestBody 实例，并传入进度回调
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(file, mediaType,
                (bytesWritten, contentLength) -> {
                    // 计算百分比
                    int progress = (int) ((bytesWritten * 100) / contentLength);
                    // --- 核心修改点 2: 调用 update 更新 UI ---
                    update(progress);
                });

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        // 3. 将包装后的 requestBody 添加到请求中
        builder.addFormDataPart("upload", file.getName(), progressRequestBody);

        // 构建请求体
        RequestBody requestBody = builder.build();

        // 构建请求
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Static.okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                // 请求结束后，无论成功失败，都关闭进度条
                try {
                    if (response.isSuccessful()) {
                        // 使用 try-with-resources 自动管理 body 的关闭
                        try (ResponseBody body = response.body()) {
                            String string = body.string();
                            Log.d(class_name, "上传成功，服务器返回: " + string);
                            window_progress_bar.setText("上传成功");
                        } catch (Exception e) {
                            Log.e(class_name, "处理响应体时发生异常", e);
                            window_progress_bar.setText("上传失败");
                        }
                    } else {
                        Log.e(class_name, "上传失败，HTTP 状态码: " + response.code());
                        window_progress_bar.setText("上传失败");
                    }
                } finally {
                    window_progress_bar.close();
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                final String errorMsg = "请求出错: " + e.getMessage();
                Log.w(class_name, errorMsg);
                window_progress_bar.setText("请求出错");
                window_progress_bar.close();
            }
        });
    }

    /**
     * 简单的 MIME 类型猜测
     */
    private String guessMimeType(String fileName) {
        // (你的 guessMimeType 方法代码保持不变)
        if (fileName == null || fileName.isEmpty()) {
            return "application/octet-stream";
        }
        String extension = getFileExtension(fileName).toLowerCase();
        switch (extension) {
            case "jpg": case "jpeg": return "image/jpeg";
            case "png": return "image/png";
            case "gif": return "image/gif";
            case "webp": return "image/webp";
            case "bmp": return "image/bmp";
            case "svg": return "image/svg+xml";
            case "mp3": return "audio/mpeg";
            case "wav": return "audio/wav";
            case "ogg": return "audio/ogg";
            case "m4a": return "audio/mp4";
            case "flac": return "audio/flac";
            case "mp4": return "video/mp4";
            case "3gp": return "video/3gpp";
            case "mkv": return "video/x-matroska";
            case "webm": return "video/webm";
            case "avi": return "video/x-msvideo";
            case "ts": return "video/MP2T";
            case "txt": return "text/plain";
            case "html": case "htm": return "text/html";
            case "json": return "application/json";
            case "xml": return "application/xml";
            case "pdf": return "application/pdf";
            case "doc": return "application/msword";
            case "docx": return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "xls": return "application/vnd.ms-excel";
            case "xlsx": return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "ppt": return "application/vnd.ms-powerpoint";
            case "pptx": return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            default: return "application/octet-stream";
        }
    }

    /**
     * 获取文件扩展名的辅助方法
     */
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    // --- 将 ProgressRequestBody 作为内部类放在这里，或者单独放在一个文件中 ---
    public static class ProgressRequestBody extends RequestBody {
        private final File file;
        private final MediaType mediaType;
        private final ProgressListener listener;

        public ProgressRequestBody(File file, MediaType mediaType, ProgressListener listener) {
            this.file = file;
            this.mediaType = mediaType;
            this.listener = listener;
        }

        @Override
        public MediaType contentType() {
            return mediaType;
        }

        @Override
        public long contentLength() throws IOException {
            return file.length();
        }

        @Override
        public void writeTo(@NonNull BufferedSink sink) throws IOException {
            long fileLength = contentLength();
            long bytesWritten = 0;

            try (Source source = Okio.source(file)) {
                Buffer buffer = new Buffer();
                long readCount;
                while ((readCount = source.read(buffer, 8192)) != -1) {
                    sink.write(buffer, readCount);
                    bytesWritten += readCount;
                    listener.onProgress(bytesWritten, fileLength);
                }
            }
        }

        public interface ProgressListener {
            void onProgress(long bytesWritten, long contentLength);
        }
    }
}
