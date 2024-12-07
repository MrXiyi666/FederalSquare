<?php

    $account_id = $_POST['account_id'];

    $path = "./Account/$account_id/Collection";
    if(!is_dir($path)){
        die("no");
    }
    $name = scandir($path);
    $list = array();
    foreach ($name as $value){
        if($value=='.' || $value=='..'){
            continue;
        }
        $list[] = $value;
    }
    if(count($list) == 0){
        die("no");
    }

    echo implode("\n", $list);
