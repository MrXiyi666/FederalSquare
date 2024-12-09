<?php
    $Square_PassWord = "federal-square";
    $Read_PassWord = $_POST['Read_PassWord'];

    if(!empty($Square_PassWord)){
        if(empty($Read_PassWord)){
            die("Null_PassWord");
        }

        if($Read_PassWord === $Square_PassWord){

        }else{
            die("Error_PassWord");
        }
    }
