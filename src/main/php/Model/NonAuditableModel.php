<?php

namespace Colonization\Model;


use Illuminate\Database\Eloquent\Model;

class NonAuditableModel extends Model {
    const UPDATED_AT = null;
    const CREATED_AT = null;
}