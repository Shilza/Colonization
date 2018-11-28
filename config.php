<?php

use Illuminate\Database\Capsule\Manager as Capsule;

$capsule = new Capsule;

$capsule->addConnection([
    'driver'    => 'mysql',
    'host'      => 'localhost',
    'port'      => '22222',
    'database'  => 'test',
    'username'  => 'root',
    'password'  => '13371337Aa___',
    'charset'   => 'utf8',
    'collation' => 'utf8_unicode_ci'
]);

$capsule->bootEloquent();