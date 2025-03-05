package fun.android.federal_square.fun;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.webkit.MimeTypeMap;

public class FileUri {
    public static int getUriFileSize(Context context, Uri fileUri) {
        if (context == null || fileUri == null) {
            return 0;
        }

        try {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(fileUri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                if (sizeIndex != -1) {
                    long fileSize = cursor.getLong(sizeIndex);
                    cursor.close();
                    // 将 long 转换为 int，如果超出 int 范围，返回 0
                    if (fileSize > Integer.MAX_VALUE || fileSize < Integer.MIN_VALUE) {
                        return 0;
                    }
                    return (int) fileSize;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public static String getFileExtension(Context context, Uri fileUri) {
        if (context == null || fileUri == null) {
            return null;
        }

        String mimeType = context.getContentResolver().getType(fileUri);
        if (mimeType == null) {
            return null;
        }

        return MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType);
    }

    public static String getFileName(Context context, Uri fileUri) {
        if (context == null || fileUri == null) {
            return null;
        }

        String fileName = null;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(fileUri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (nameIndex != -1) {
                    fileName = cursor.getString(nameIndex);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fileName == null) {
            fileName = fileUri.getPath();
            if (fileName != null) {
                int lastSlash = fileName.lastIndexOf('/');
                if (lastSlash != -1) {
                    fileName = fileName.substring(lastSlash + 1);
                }
            }
        }

        return fileName;
    }
}
