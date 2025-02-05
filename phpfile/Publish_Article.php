<?php
    include 'PassWord_Data.php';
    $account = $_POST['account'] ?? '';
    $name = $_POST['name'] ?? '';
    $data = $_POST['data'] ?? '';
    if(empty($account) || strlen($account) === 0){
	    die("no_account");
    }
	if(empty($name) || strlen($name) === 0){
	    die("no_account");
    }
	if(empty($data) || strlen($data) === 0){
	    die("no_account");
    }
    if(!fopen("./Account/$account/Data/$name" . ".json", "w")){
		die ("no_account");	
	}
	
	$file = fopen("./Square_Data/$name" . ".json", "w");
	if(!$file){
		die ("no_network");
	}
    $writ = fwrite($file, $data);
	fclose($file);
	// 检查写入是否成功
    if ($writ === false) {
        die("no_account");
    }
    $mkk = mkdir("./Discuss_Data/$name", 0777, true);
	if ($mkk === false) {
		unlink("./Square_Data/$name" . ".json");
        die("no_account");
    }
    echo "ok";