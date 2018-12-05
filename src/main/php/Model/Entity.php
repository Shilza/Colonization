<?php

namespace Colonization\Model;

/**
 * Class Entity
 * @package Model
 * @property int $id
 * @property int $strength
 * @property int $leadership
 * @property int $intelligence
 * @property int $militancy
 * @property int $diplomacy
 * @property int $age
 * @property int $enterprise
 */
class Entity extends NonAuditableModel {

    protected $table = 'entities';

    protected $fillable = [
        'leadership',
        'strength',
        'intelligence',
        'militancy',
        'diplomacy',
        'enterprise',
        'colony_id',
        'addiction'
    ];
}