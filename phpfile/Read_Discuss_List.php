<?php
    $square_time = $_POST['square_time'];
    if(!isset($square_time) and strlen($square_time) == 0){
        die("no_square_time");
    }

    $path = "./Discuss_Data/$square_time";
    if(!is_dir($path)){
        die("no_list");
    }
    $_array = scandir($path, 1);
    $_return = array();
    $index = 0;
    foreach ($_array as $value){
        if($index < 30){
            if($value=='.' || $value=='..'){
                continue;
            }else{
                if(file_exists($path)){
                    $str = file_get_contents($path . "/$value");
                    if(strlen($str) > 0){
                        $_return[] = $str;
                        $index++;
                    }
                }

            }
        }else{
            break;
        }
    }
    if(count($_return) == 0){
        die("no_list");
    }
    echo implode("\n", $_return);
