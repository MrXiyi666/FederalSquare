<?php
    include 'PassWord_Data.php';
    $path = $_POST['path'] ?? '';
    if(!isset($path) and strlen($path) == 0){
        die("no_file");
    }
    if(!is_file($path)){
        die ("no_file");  
    }
	$str = file_get_contents($path);
	if(!$str){
		die("no_file");
	}
    if(strlen($str) == 0){
        die ("no_file");
    }
    echo $str;