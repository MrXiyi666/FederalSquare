<?php
    include 'PassWord_Data.php';
    $path = $_POST['path'] ?? '';
    if(!isset($path) and strlen($path) == 0){
        die("no_folder");
    }
    if(!is_dir($path)){
        die("no");
    }
    $_array = scandir($path, 1);
    $_return = array();
    foreach ($_array as $value){
        if(count($_return) >= 9999){
            break;
        }
        if($value=='.' || $value=='..'){
            continue;
        }
        if(!is_file($path . "/$value")){
            continue;
        }
		$str = file_get_contents($path . "/$value");
        if(strlen($str) == 0){
           continue;
        }
		 $_return[] = $str;
    }
    if(count($_return) == 0){
        die("no");
    }
    echo implode("\n", $_return);