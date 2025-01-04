<?php
    include 'PassWord_Data.php';
    $path = $_POST['path'];
    $data = $_POST['data'];


    $file = fopen($path, "w");
    if(!$file){
        die("no");
    }
	echo "ok";
    fwrite($file, $data);
    fclose($file);
    
