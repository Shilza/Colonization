<?php

namespace Colonization\Service;


use Colonization\Model\Entity;

class EntityService extends Service {
    public static function createEntity(int $colonyId) : Entity {
        $randIndexes = ['leadership' => self::generateIndex(), 'strength' => self::generateIndex(),
            'intelligence' => self::generateIndex(), 'militancy' => self::generateIndex()];
        $indexes = array_merge($randIndexes, ['diplomacy' => self::generateDiplomacy($randIndexes),
            'enterprise' => self::generateEnterprise($randIndexes)]);

        return Entity::create(array_merge($indexes, ['name' => static::generateName(), 'colony_id' => $colonyId]));
    }

    private static function generateDiplomacy(array $indexes) : int{
        return $indexes['leadership'] / 100 * 25 + $indexes['strength'] / 100 * 25
            + $indexes['intelligence'] / 100 * 50 - $indexes['militancy'] / 100 * 40;
    }

    private static function generateEnterprise(array $indexes) : int{
        return $indexes['leadership'] / 100 * 15 + $indexes['strength'] / 100 * 30
            + $indexes['intelligence'] / 100 * 40 + $indexes['militancy'] / 100 * 15;
    }

    private static function generateName(): string{
        return mt_rand(100000, 999999);
    }
}