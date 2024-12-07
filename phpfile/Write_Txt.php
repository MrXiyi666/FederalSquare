<?php
    $path = $_POST['path'];
    $data = $_POST['data'];
    if( (!isset($path) and strlen($path) == 0) and (!isset($data) and strlen($data) == 0)){
        die("no");
    }
    $file = fopen($path, "w");
    if($file){
        echo "ok";
        fwrite($file, $data);
        fclose($file);
    }else{
        echo "no";
    }
    
