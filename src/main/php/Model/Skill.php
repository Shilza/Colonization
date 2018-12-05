<?php

namespace Colonization\Model;


/**
 * Class Skill
 * @package Colonization\Model
 * @property int $id
 * @property int $entity_id
 * @property int $military
 * @property int $farming
 * @property int $crafting
 */
class Skill extends NonAuditableModel {

    protected $fillable = ['entity_id'];
}