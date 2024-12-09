<?php

    include 'PassWord_Data.php';
    $path = $_POST['path'];


    if(!isset($path) and strlen($path) == 0){
        die("no_file");
    }
if(file_exists($path)){
    $str = file_get_contents($path);
    if(strlen($str) > 0){
        echo $str;
    }else{
        echo "no_file";
    }
}else{
    echo 'no_file';
}
