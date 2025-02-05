<?php

    include 'PassWord_Data.php';
    //创建账号功能
	$account = $_POST['account'] ?? '';
	$path = $_POST['path'] ?? '';
	$data = $_POST['data'] ?? '';
    if( (!isset($account) and strlen($account) == 0) and
        (!isset($path) and strlen($path) == 0) and
        (!isset($data) and strlen($data) == 0)
    ){
        die("no_account");
    }
	if(file_exists($path)){
	   die("exists");
	}
		
    if(is_dir($path)){
        die("exists");
    }
    if(!mkdir($path, 0777, true)){
        die("no_account");
    }
    $file = fopen($path . '/' . $account . '.txt', "w");
    if(!$file){
        die("no_data");
    }
    
    $writ = fwrite($file, $data);
    fclose($file);
    if ($writ === false) {
        die("no_data");
    }
    $mkk = mkdir($path . '/Data', 0777, true);
    if ($mkk === false) {
		unlink($path . '/' . $account . '.txt');
        die("no_data");
    }
    $mkk = mkdir($path . "/Collection", 0777, true);
	if ($mkk === false) {
		unlink($path . '/' . $account . '.txt');
        die("no_data");
    }
    $mkk = mkdir($path . "/Image_Resources", 0777, true);
	if ($mkk === false) {
		unlink($path . '/' . $account . '.txt');
        die("no_data");
    }
	echo "ok";
