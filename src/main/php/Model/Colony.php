<?php

namespace Colonization\Model;

use Illuminate\Database\Eloquent\Model;

/**
 * Class Colony
 * @property int $id
 * @property string $name
 * @property string $location
 * @property int $type
 * @property int $water_availability
 * @property int $wood_availability
 * @property int $metal_availability
 * @property int $fertility
 * @property bool $war
 * @property int $living_level
 * @property int $money
 * @property int $lifespan
 * @property int $population_count
 * @property int $age
 * @property int $experience
 * @property int $food
 * @property int $weapon
 * @property int $tools
 */
class Colony extends Model {
    const UPDATED_AT = null;
    const CREATED_AT = null;

    protected $table = 'colonies';

    protected $fillable
        = ['name', 'location', 'type', 'water_availability', 'wood_availability', 'metal_availability', 'fertility'];
}