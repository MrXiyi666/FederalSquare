<?php

    include 'PassWord_Data.php';
    $path = $_POST['path'];

    if(!isset($path) and strlen($path) == 0){
        die("no_file");
    }
    if(!is_file($path)){
       echo 'no_file';  
    }
	
	$str = file_get_contents($path);
    if(strlen($str) == 0){
        echo "no_file";
    }
    echo $str;