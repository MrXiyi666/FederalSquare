<?php

    include 'PassWord_Data.php';
    //创建账号功能
	$account = $_POST['account'];
	$path = $_POST['path'];
	$data = $_POST['data'];
    if( (!isset($account) and strlen($account) == 0) and
        (!isset($path) and strlen($path) == 0) and
        (!isset($data) and strlen($data) == 0)
    ){
        die("no_account");
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
echo "ok";
fwrite($file, $data);
fclose($file);
mkdir($path . '/Data', 0777, true);
mkdir($path . "/Collection", 0777, true);
mkdir($path . "/Image_Resources", 0777, true);

	
