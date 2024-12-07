<?php

    $post_time = $_POST['post_time'];
    $data = $_POST['data'];
    $account_id = $_POST['account_id'];

    if(
        (!isset($post_time) and strlen($post_time) == 0)
        and
        (!isset($account_id) and strlen($account_id) == 0)
        and
        (!isset($data) and strlen($data) == 0)
    ){
        die("no");
    }
    $path = "./Account/$account_id/Collection/$post_time" . ".json";
    if(file_exists($path)){
        die("cunzai");
    }
    $file = fopen($path, "w");
    if(!$file){
        die("no_file");
    }
    echo "yes_file";
    fwrite($file, $data);
    fclose($file);



