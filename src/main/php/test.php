<?php

require_once "../../../vendor/autoload.php";
require_once "config.php";

use Colonization\Constants\Addictions;
use Colonization\Model\Colony;
use Colonization\Service\ColonyCreatingService;

//var_dump(Colony::avg('lifespan'));

//for($i = 0; $i < 500; $i++)
//    ColonyCreatingService::createColony(['color' => '1', 'name' => mt_rand(0, 999999), 'location' => '1']);
//
//$arr = [Addictions::ARTISAN => 0, Addictions::TRADER => 0, Addictions::FARMER => 0, Addictions::MILITARY => 0];
//
//foreach(Colony::all() as $colony)
//    $arr[$colony['type']]++;
//
//var_dump($arr);


//1.33
$average = 60;

$result = 60 / 100 * 20;

//100 + (perDay - perDay*$coefficient) * lifespan / 5;
//100 + perDay *(1 - $coefficient) * lifespan / 5 = 0
//perDay * (1 - $coefficient) * lifespan / 5 = -100
//(1 - $coefficient) * lifespan / 5 = -100 / perDay
//(1 - $coefficient) * lifespan = -500 / perDay
// 1 - $coefficient = -500 / (lifespan * perDay)
// $coefficient - 1 = 500 / (lifespan * perDay)
// $coefficient = 500 / (lifespan * perDay) + 1

$vitality = 100;
$coefficient = 500 / 50 / 30 + 1;

for($i = 0; $i < 10; $i++) {
    $vitality -= 30 * $coefficient;
    $vitality += 30;
}

echo $coefficient . "\n";
echo $vitality;