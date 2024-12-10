<?php
  include 'PassWord_Data.php';
  $path = $_POST['path'];

  if(!is_dir($path)){
      die("no_folder");
  }

  $_array = scandir($path);
  $_return = array();
  foreach ($_array as $value){
      if($value=='.' || $value=='..'){
          continue;
      }
      if(!is_file($path . "/$value")){
          continue;
      }
      $_return[] = $value;
  }
  if(count($_return) == 0){
      die("no");
  }
  echo implode("\n", $_return);

