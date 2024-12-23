<?php

    include 'PassWord_Data.php';
    $account = $_POST['account'];
    $name = $_POST['name'];
    $data = $_POST['data'];

    if(!fopen("./Account/$account/Data/$name" . ".json", "w")){
		die ("no_account");	
	}
	
	$file = fopen("./Square_Data/$name" . ".json", "w");
	if(!$file){
		die ("no_network");
	}
	
	echo "ok";
    fwrite($file, $data);
    fclose($file);
    mkdir("./Discuss_Data/$name", 0777, true);
