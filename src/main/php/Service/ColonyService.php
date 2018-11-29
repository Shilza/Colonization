<?php

namespace Colonization\Service;


use Colonization\Model\Colony;

class ColonyService {
    public static function createColony(array $data) : void{
        $withIndexes = array_merge($data, ['water_availability' => static::generateIndex(),
            'wood_availability' => static::generateIndex(), 'metal_available' => static::generateIndex(),
            'fertility' => static::generateIndex()]);
        Colony::create(array_merge($withIndexes, ['type' => static::generateType($withIndexes)]));
    }

    private static function generateIndex(){
        return mt_rand(0, 10);
    }

    private static function generateType(array $indexes){
        return mt_rand(0, 3);
    }
}