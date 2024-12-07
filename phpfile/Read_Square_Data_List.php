<?php

if(!is_dir('./Square_Data')){
    die("no");
}
$_array = scandir('./Square_Data', 1);
$_return = array();
$index = 0;
foreach ($_array as $value){
    if($index >= 100){
        break;
    }
    if($value=='.' || $value=='..'){
        continue;
    }else{
        $_return[] = $value;
        $index++;
    }
}
if(count($_return) == 0){
    die("no");
}
echo implode("\n", $_return);
