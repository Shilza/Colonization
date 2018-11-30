<?php

namespace Colonization\Model;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Entity
 * @package Model
 * @property int $id
 * @property string $name
 * @property int $strength
 * @property int $leadership
 * @property int $intelligence
 * @property int $militancy
 * @property int $diplomacy
 * @property int $birth_date
 * @property int $enterprise
 */
class Entity extends Model {
    const UPDATED_AT = null;
    const CREATED_AT = null;

    protected $table = 'entities';

    protected $fillable = ['leadership', 'strength', 'intelligence', 'militancy', 'diplomacy', 'enterprise', 'name', 'colony_id', 'addiction'];
}