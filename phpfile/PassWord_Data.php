<?php
    $PassWord = $_POST['PassWord'];

    $File_Name = "Access_PassWord.txt";
    if(is_file($File_Name)){
        $PassWord = file_get_contents($File_Name);
        if(strlen($PassWord) > 0){
            if(empty($PassWord)){
                die("Null_PassWord");
            }
            if($PassWord != $PassWord){
                die("Error_PassWord");
            }
        }
    }

