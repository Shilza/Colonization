<?php

namespace Colonization\Service;


class Service {
    protected static function generateIndex(){
        return mt_rand(0, 10);
    }
}