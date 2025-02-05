<?php
    include 'PassWord_Data.php';
    $path = $_POST['path'] ?? '';
    $data = $_POST['data'] ?? '';

    if(empty($path) || strlen($path) === 0){
	    die("no");
    }
	if(empty($data) || strlen($data) === 0){
	    die("no");
    }
    
    $file = fopen($path, "w");
    if(!$file){
        die("no");
    }
    $writ = fwrite($file, $data);
	fclose($file);
	// 检查写入是否成功
    if ($writ === false) {
        die("no");
    }
	echo "ok";