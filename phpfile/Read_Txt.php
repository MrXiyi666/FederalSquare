<?php
    $path = $_POST['path'];
    if(!isset($path) and strlen($path) == 0){
        die("no_file1");
    }
if(file_exists($path)){
    $str = file_get_contents($path);
    if(strlen($str) > 0){
        echo $str;
    }else{
        echo "no_file2";
    }
}else{
    echo 'no_file3';
}
