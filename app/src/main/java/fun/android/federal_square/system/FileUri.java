package fun.android.federal_square.system;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Log;
import android.webkit.MimeTypeMap;

public class FileUri {
    private static final String TAG = "FileUriUtils";

    /**
     * 获取 Uri 指向文件的大小（以字节为单位）。
     *
     * @param context  应用上下文，用于获取 ContentResolver。
     * @param fileUri  指向文件的 Uri。
     * @return 文件的大小（字节）；如果获取失败（如 Uri 无效、权限不足等），则返回 -1。
     */
    public static long getUriFileSize(Context context, Uri fileUri) {
        if (context == null || fileUri == null) {
            Log.w(TAG, "Context 或 Uri 为 null。");
            return -1;
        }

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            // 仅查询我们需要的 SIZE 列，以提高效率
            cursor = contentResolver.query(fileUri, new String[]{OpenableColumns.SIZE}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                if (sizeIndex != -1) {
                    return cursor.getLong(sizeIndex);
                }
            }
        } catch (SecurityException e) {
            Log.e(TAG, "查询 Uri: " + fileUri + " 的文件大小时权限被拒绝。", e);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "查询文件大小时遇到无效的 Uri: " + fileUri, e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Log.w(TAG, "无法获取 Uri: " + fileUri + " 的文件大小。");
        return -1;
    }

    /**
     * 获取 Uri 指向文件的扩展名（不包含点号）。
     *
     * @param context  应用上下文，用于获取 ContentResolver。
     * @param fileUri  指向文件的 Uri。
     * @return 文件的扩展名（例如 "jpg", "pdf"）；如果获取失败，则返回 null。
     */
    public static String getFileExtension(Context context, Uri fileUri) {
        if (context == null || fileUri == null) {
            Log.w(TAG, "Context 或 Uri 为 null。");
            return null;
        }

        ContentResolver contentResolver = context.getContentResolver();
        String mimeType;
        try {
            mimeType = contentResolver.getType(fileUri);
        } catch (SecurityException e) {
            Log.e(TAG, "获取 Uri: " + fileUri + " 的 MIME 类型时权限被拒绝。", e);
            return null;
        }

        if (mimeType == null) {
            Log.w(TAG, "Uri: " + fileUri + " 的 MIME 类型为 null。");
            return null;
        }

        // 从 MIME 类型映射到文件扩展名
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
    }

    /**
     * 获取 Uri 指向文件的显示名称。
     *
     * @param context  应用上下文，用于获取 ContentResolver。
     * @param fileUri  指向文件的 Uri。
     * @return 文件的显示名称（例如 "IMG_2023.jpg"）；如果获取失败，则返回 null。
     */
    public static String getFileName(Context context, Uri fileUri) {
        if (context == null || fileUri == null) {
            Log.w(TAG, "Context 或 Uri 为 null。");
            return null;
        }

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            // 仅查询我们需要的 DISPLAY_NAME 列，以提高效率
            cursor = contentResolver.query(fileUri, new String[]{OpenableColumns.DISPLAY_NAME}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (nameIndex != -1) {
                    return cursor.getString(nameIndex);
                }
            }
        } catch (SecurityException e) {
            Log.e(TAG, "查询 Uri: " + fileUri + " 的文件名时权限被拒绝。", e);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "查询文件名时遇到无效的 Uri: " + fileUri, e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        Log.w(TAG, "无法获取 Uri: " + fileUri + " 的文件名。");
        return null;
    }
}
