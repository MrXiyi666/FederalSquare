<?php
    include 'PassWord_Data.php';

    $path = $_POST['path'];
    if(!is_file($path)) {
        die("no_file");
    }
    if(!unlink($path)){
        die("no_delete");
    }
    echo "yes";
