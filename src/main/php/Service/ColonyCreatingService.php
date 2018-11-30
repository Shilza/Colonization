<?php

namespace Colonization\Service;


use Colonization\Constants\Addictions;
use Colonization\Model\Colony;

class ColonyCreatingService extends Service {

    public static function createColony(array $data) {
        $colony = ColonyCreatingService::createColonyBasic($data);
        for ($i = 0; $i < 10; $i++)
            EntityCreatingService::createEntity($colony->id);

        $colony->type = ColonyCreatingService::generateType($colony);
        $colony->save();
    }

    private static function createColonyBasic(array $data): Colony {
        $indexes = [
            'water_availability' => static::generateIndex(),
            'wood_availability' => static::generateIndex(),
            'metal_available' => static::generateIndex(),
            'fertility' => static::generateIndex()
        ];

        return Colony::create(
            array_merge($indexes, [
                'name' => $data['name'],
                'location' => json_encode($data['location']),
                'color' => $data['color']
            ])
        );
    }


    private static function generateType(Colony $colony) {
        $indexes = self::countEntitiesAddictions($colony->entities->toArray());

        $indexes[Addictions::MILITARY] = (($indexes[Addictions::MILITARY] + self::calculateMilitaryIndex($colony)) / 2) * self::calculateMilitaryCoefficient(Colony::where('type', Addictions::MILITARY)->count(), Colony::where('type', '!=', Addictions::MILITARY)->count());
        $indexes[Addictions::TRADER] = (($indexes[Addictions::TRADER] + self::calculateTraderIndex($colony)) / 2) * self::calculateTraderCoefficient(Colony::where('type', Addictions::TRADER)->count(), Colony::where('type', '!=', Addictions::TRADER)->count());
        $indexes[Addictions::FARMER] = ($indexes[Addictions::FARMER] + self::calculateFarmerIndex($colony)) / 2;
        $indexes[Addictions::ARTISAN] = ($indexes[Addictions::ARTISAN] + self::calculateArtisanIndex($colony)) / 2;

        uasort($indexes, function (float $first, float $second): int {
            if ($second === $first)
                return 0;
            return $second < $first ? -1 : 1;
        });

        return  array_first(array_keys($indexes));
    }

    private static function countEntitiesAddictions(array $entities): array {
        $indexes = [Addictions::ARTISAN => 0, Addictions::TRADER => 0, Addictions::FARMER => 0, Addictions::MILITARY => 0];
        foreach ($entities as $entity)
            $indexes[$entity['addiction']]++;

        return $indexes;
    }

    private static function calculateFarmerIndex(Colony $colony): float {
        return $colony->fertility / 100 * 50
            + $colony->metal_availability / 100 * 15
            + $colony->water_availability / 100 * 30
            + $colony->wood_availability / 100 * 5;
    }

    private static function calculateMilitaryIndex(Colony $colony): float {
        return $colony->fertility / 100 * 10
            + $colony->metal_availability / 100 * 20
            + $colony->water_availability / 100 * 30
            + $colony->wood_availability / 100 * 20;
    }

    private static function calculateTraderIndex(Colony $colony): float {
        return $colony->fertility / 100 * 10
            + $colony->metal_availability / 100 * 20
            + $colony->water_availability / 100 * 20
            + $colony->wood_availability / 100 * 30;
    }

    private static function calculateArtisanIndex(Colony $colony): float {
        return $colony->fertility / 100 * 10
            + $colony->metal_availability / 100 * 35
            + $colony->water_availability / 100 * 20
            + $colony->wood_availability / 100 * 35;
    }

    private static function calculateMilitaryCoefficient($military, $others): float {
        if(!$others)
            return 0;
        else if(!$military)
            return 1;
        else
            return $others / $military;
    }

    private static function calculateTraderCoefficient($traders, $others): float {
        if(!$others)
            return 0;
        else if(!$traders)
            return 1;
        else
            return $others / 3 / $traders;
    }
}