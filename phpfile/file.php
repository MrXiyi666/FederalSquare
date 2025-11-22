<?php
//创建文件夹
function boolean create_folder($name)
{
    if (!is_dir($name)) {
        $result = mkdir($name);
        return $result;
    } else {
        return true;
    }
}
//创建多层文件夹
function create_many_folder($name)
{
    if (!is_dir($name)) {
        $result = mkdir($name, 0755, true);
        if ($result) {
            echo "多级文件夹创建成功！";
        } else {
            echo "多级文件夹创建失败！";
        }
    } else {
        return true;
    }
}
?>
