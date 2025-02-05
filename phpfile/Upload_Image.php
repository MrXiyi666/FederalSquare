<?php

    include 'PassWord_Data.php';
    $Account_name = $_POST['Account'] ?? '';
    if(empty($Account_name) || strlen($Account_name) === 0){
	    die("no_up");
    }
    if($_FILES["file"]["error"] > 0){
        die("no_up");
    }

    if(
		move_uploaded_file($_FILES["file"]["tmp_name"], "./Account/$Account_name/Image_Resources/" . $_FILES["file"]["name"])
	){
	    echo "ok";
	}else{
		echo "no_file";
	}