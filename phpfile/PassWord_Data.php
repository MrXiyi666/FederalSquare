<?php
    $Read_PassWord = $_POST['PassWord'];

    $File_Name = "Access_PassWord.txt";
    if(is_file($File_Name)){
        $PassWord = file_get_contents($File_Name);
		if($PassWord and strlen($PassWord) > 0){
		   if(empty($Read_PassWord)){
                die("Null_PassWord");
            }
            if($Read_PassWord != $PassWord){
                die("Error_PassWord");
            }
		}
    }