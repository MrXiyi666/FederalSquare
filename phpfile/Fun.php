<?php

function remove_array($jianzhi, $shuju){
    $fujian = array();
    foreach($jianzhi as $value){
        if($shuju != $value){
            $fujian[] = $value;
        }
    }
    return $fujian;
}
