<?php
    // 检查上传错误
    if ($_FILES["upload"]["error"] > 0) {
        die("错误：" . $_FILES["upload"]["error"]);
    }

    // 拼接文件信息
    $data = $_FILES["upload"]["name"] . "\n" .
            $_FILES["upload"]["type"] . "\n" .
            ($_FILES["upload"]["size"] / 1024) . "\n" .
            $_FILES["upload"]["tmp_name"];


    // 将信息写入 upload.txt（会覆盖原有内容）

    file_put_contents("upload.txt", $data);
    // basename() 函数确保我们只获取文件名，防止路径注入攻击
    $targetFileName = basename($_FILES["upload"]["name"]);
    // __DIR__ 是当前脚本所在的目录的绝对路径
    $targetPath = __DIR__ . "/" . $targetFileName;
    // 使用 move_uploaded_file() 函数将临时文件移动到目标位置
    if (move_uploaded_file($_FILES["upload"]["tmp_name"], $targetPath)) {
        echo "文件上传成功！已保存为: " . $targetFileName;
    } else {
        echo "文件上传失败！";
    }

?>
