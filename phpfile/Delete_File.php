<?php
    $path = $_POST['path'];
    if(!file_exists($path)) {
        die("no_file");
    }
    if(!unlink($path)){
        die("no_delete");
    }
    echo "yes";
