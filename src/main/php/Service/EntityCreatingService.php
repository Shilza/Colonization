<?php

namespace Colonization\Service;


use Colonization\Constants\Addictions;
use Colonization\Model\Entity;

class EntityCreatingService extends Service {
    public static function createEntity(int $colonyId): Entity {
        $randIndexes = [
            'leadership' => self::generateIndex(),
            'strength' => self::generateIndex(),
            'intelligence' => self::generateIndex(),
            'militancy' => self::generateIndex()
        ];

        $indexes = array_merge($randIndexes, [
            'diplomacy' => self::generateIndex(),
            'enterprise' => self::generateIndex()
        ]);

        return Entity::create(array_merge($indexes, [
            'name' => static::generateName(),
            'colony_id' => $colonyId,
            'addiction' => self::generateAddiction($indexes),
            'birth_date' => 0
        ]));
    }


    private static function generateDiplomacy(array $indexes): int {
        return
            (($result =
                $indexes['leadership'] / 100 * 25
                + $indexes['strength'] / 100 * 25
                + $indexes['intelligence'] / 100 * 50
                - $indexes['militancy'] / 100 * 40) > 0 ? $result : 0);
    }

    private static function generateEnterprise(array $indexes): int {
        return
            $indexes['leadership'] / 100 * 15
            + $indexes['strength'] / 100 * 30
            + $indexes['intelligence'] / 100 * 40
            + $indexes['militancy'] / 100 * 15;
    }


    private static function generateAddiction(array $indexes): int {
        $arr = [
            Addictions::MILITARY => self::generateMilitaryAddiction($indexes),
            Addictions::FARMER => self::generateFarmerAddiction($indexes),
            Addictions::TRADER => self::generateTraderAddiction($indexes),
            Addictions::ARTISAN => self::generateArtisanAddiction($indexes)
        ];

        uasort($arr, function (float $first, float $second): int {
            if ($second === $first)
                return 0;
            return $second < $first ? -1 : 1;
        });

        return array_first(array_keys($arr));
    }

    private static function generateMilitaryAddiction(array $indexes): float {
        return $indexes['militancy'] / 100 * 50
            + $indexes['strength'] / 100 * 20
            + $indexes['leadership'] / 100 * 20
            + $indexes['intelligence'] / 100 * 5
            + $indexes['enterprise'] / 100 * 5
            - $indexes['diplomacy'] / 100 * 30;
    }

    private static function generateFarmerAddiction(array $indexes): float {
        return $indexes['strength'] / 100 * 50
            + $indexes['intelligence'] / 100 * 20
            + $indexes['diplomacy'] / 100 * 20
            + $indexes['enterprise'] / 100 * 5
            + $indexes['leadership'] / 100 * 5
            - $indexes['militancy'] / 100 * 30;
    }

    private static function generateTraderAddiction(array $indexes): float {
        return $indexes['enterprise'] / 100 * 50
            + $indexes['diplomacy'] / 100 * 20
            + $indexes['intelligence'] / 100 * 20
            - $indexes['strength'] / 100 * 5
            + $indexes['leadership'] / 100 * 5
            - $indexes['militancy'] / 100 * 30;
    }

    private static function generateArtisanAddiction(array $indexes): float {
        return $indexes['intelligence'] / 100 * 50
            + $indexes['enterprise'] / 100 * 20
            + $indexes['strength'] / 100 * 20
            + $indexes['diplomacy'] / 100 * 5
            + $indexes['leadership'] / 100 * 5
            - $indexes['militancy'] / 100 * 25;
    }


    private static function generateName(): string {
        return mt_rand(100000, 999999);
    }
}