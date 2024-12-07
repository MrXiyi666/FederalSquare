<?php
    $account = $_POST['account'];
    $name = $_POST['name'];
    $data = $_POST['data'];
    if( (!isset($account) and strlen($account) == 0) and (!isset($name) and strlen($name) == 0) and (!isset($data) and strlen($data) == 0)){
        die("no_data");
    }
	$file = fopen("./Square_Data/$name" . ".json", "w");
	if(!$file){
		die ("no_network");
	}
	
	if(!fopen("./Account/$account/Data/$name" . ".json", "w")){
		die ("no_account");	
	}

	echo "ok";
    fwrite($file, $data);
    fclose($file);
    mkdir("./Discuss_Data/$name", 0777, true);
