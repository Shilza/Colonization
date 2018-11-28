<?php
require_once __DIR__ . '/vendor/autoload.php';
require_once 'config.php';

use Workerman\Connection\TcpConnection;
use Workerman\Worker;


$server = new Worker("websocket://0.0.0.0:2346");

$server->count = 4;

$server->onConnect = function (TcpConnection $connection) {};

$server->onMessage = function (TcpConnection $connection, string $data) {
    $arr = json_decode($data);
};

$server->onClose = function (TcpConnection $connection) {};

//Worker::runAll();
