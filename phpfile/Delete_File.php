<?php
    include 'PassWord_Data.php';
    $path = $_POST['path'] ?? '';
	
	if(empty($path) || strlen($path) === 0){
	    die("no_file");
    }
    if(!is_file($path)) {
        die("no_file");
    }
    if(!unlink($path)){
        die("no_delete");
    }
    echo "yes";