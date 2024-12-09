<?php
    include 'PassWord_Data.php';
    //创建评论功能
    $square_time = $_POST['square_time'];
    $discuss_time = $_POST['discuss_time'];
    $discuss_data = $_POST['discuss_data'];

    $path = './Discuss_Data/' . $square_time . '/';
    if( (!isset($square_time) and strlen($square_time) == 0) and (!isset($discuss_data) and strlen($discuss_data) == 0)
        and (!isset($discuss_time) and strlen($discuss_time) == 0)
    ){
        die("no_discuss");
    }
    if(!is_dir($path)){
        die("no_square");
    }
    $file = fopen($path . $discuss_time . '.json', "w");
    if(!$file){
        die("create_file_error");
    }
    fwrite($file, $discuss_data);
    fclose($file);
    echo "ok";


