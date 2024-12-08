<?php
    $Account_name = $_POST['Account'];
    if(!isset($Account_name) and strlen($Account_name) == 0){
        die("no");
    }
	if(!is_dir('./Account/' . $Account_name . '/Data/')){
        die("no");
    }
   
   
   $_array = scandir('./Account/' . $Account_name . '/Data/');
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
?>
