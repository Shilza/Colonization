<?php
require_once __DIR__ . '/vendor/autoload.php';

use Workerman\Worker;

$server = new Worker("websocket://0.0.0.0:2346");

$server->count = 4;

$server->onConnect = function ($connection) {
    var_dump($connection);
    echo "New connection\n";
};

$server->onMessage = function ($connection, $data) {
    // Send hello $data
    $connection->send('hello ' . $data);
};

$server->onClose = function ($connection) {
    echo "Connection closed\n";
};

Worker::runAll();