<?php

function remove_array($jianzhi, $shuju){
    return array_filter($jianzhi, 
    function($value) use ($shuju) {
        return $value !== $shuju;
    });
}