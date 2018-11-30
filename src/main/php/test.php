<?php

require_once "../../../vendor/autoload.php";
require_once "config.php";

use Colonization\Constants\Addictions;
use Colonization\Model\Colony;
use Colonization\Service\ColonyCreatingService;

for($i = 0; $i < 500; $i++)
    ColonyCreatingService::createColony(['color' => '1', 'name' => mt_rand(0, 999999), 'location' => '1']);

$arr = [Addictions::ARTISAN => 0, Addictions::TRADER => 0, Addictions::FARMER => 0, Addictions::MILITARY => 0];

foreach(Colony::all() as $colony)
    $arr[$colony['type']]++;

var_dump($arr);