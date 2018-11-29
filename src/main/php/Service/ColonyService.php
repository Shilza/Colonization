<?php

namespace Colonization\Service;


use Colonization\Model\Colony;

class ColonyService {
    public static function createColony(array $data) : void{
        $indexes = ['water_availability' => static::generateIndex(),
            'wood_availability' => static::generateIndex(), 'metal_available' => static::generateIndex(),
            'fertility' => static::generateIndex()];
        Colony::create(array_merge($indexes, ['name' => $data['name'], 'location' => json_encode($data['location']),
            'type' => static::generateType($indexes)]));
    }

    private static function generateIndex(){
        return mt_rand(0, 10);
    }

    private static function generateType(array $indexes){
        return mt_rand(0, 3);
    }
}