<?php

include 'PassWord_Data.php';


if(!is_dir('./Square_Data')){
    die("no");
}

$_array = scandir('./Square_Data', 1);
$_return = "";
foreach ($_array as $value){
    if(!empty($_return)){
        break;
    }
    if($value=='.' || $value=='..'){
        continue;
    }else{
        $_return = $value;
    }
}

if(empty($_return)){
    die("no");
}

echo $_return;
