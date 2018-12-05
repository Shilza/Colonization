<?php

namespace Colonization\Model;

use Illuminate\Database\Eloquent\Collection;
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
 * @property Collection $entities
 */
class Colony extends NonAuditableModel {


    protected $table = 'colonies';

    protected $fillable
        = ['name', 'location', 'type', 'water_availability', 'wood_availability', 'metal_availability', 'fertility', 'color'];

    public function entities(){
        return $this->hasMany('Colonization\Model\Entity');
    }
}