<?php

namespace Colonization\Service;


use Colonization\Model\Colony;

class ColonyService extends Service {
    public static function createColony(array $data) : Colony{
        $indexes = ['water_availability' => static::generateIndex(),
            'wood_availability' => static::generateIndex(), 'metal_available' => static::generateIndex(),
            'fertility' => static::generateIndex()];
        return Colony::create(array_merge($indexes, ['name' => $data['name'], 'location' => json_encode($data['location']),
            'type' => static::generateType($indexes), 'color' => $data['color']]));
    }

    private static function generateType(array $indexes){
        return mt_rand(0, 3);
    }
}