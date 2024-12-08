<?php

if(!is_dir('./Square_Data')){
    die("no");
}
$_array = scandir('./Square_Data', 1);
$_return = array();
foreach ($_array as $value){
    if($value=='.' || $value=='..'){
        continue;
    }
    $_return[] = $value;
}
if(count($_return) == 0){
    die("no");
}
echo implode("\n", $_return);
