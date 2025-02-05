<?php
include 'Fun.php';
include 'PassWord_Data.php';
$path = "./Discuss_Data/";
$name = scandir($path, 1);
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
$list_size = array();
$list_name = array();
$name = array();
foreach ($list as $value){
    if($value=='.' || $value=='..'){
        continue;
    }
    $name = scandir($path . $value);
    $size = count($name) - 2;
    if($size > 0){
        $list_name[] = $size . "\n" . $value;
    }
}
if(count($list_name) == 0){
    die("no_list");
}
foreach($list_name as $value){
    if($value=='.' || $value=='..'){
        continue;
    }
    $list_size[] = explode("\n", $value)[0];
}

if(count($list_size) == 0){
    die("no_size");
}
rsort($list_size);

$list = array();
$list_index = 0;
while($list_index < count($list_size)){
    foreach($list_name as $value){
        if(count($list) >= 9999){
            break;       
        } 
        if(explode("\n", $value)[0] != $list_size[$list_index]){
            continue;
        }
        $list[] = explode("\n", $value)[1];
        $list_name = remove_array($list_name, $value);
    }
    $list_index++;
}

echo implode("\n", $list);


