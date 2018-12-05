<?php
require_once __DIR__ . '/../../../vendor/autoload.php';
require_once 'config.php';

use Colonization\Constants\Types;
use Colonization\Model\Colony;
use Colonization\Model\Prices;
use Colonization\Service\ColonyCreatingService;
use Workerman\Connection\TcpConnection;
use Workerman\Worker;


$server = new Worker("websocket://0.0.0.0:2346");

$server->count = 1;

$server->onConnect = function (TcpConnection $connection) {
    $connection->send(json_encode([
        'type' => Types::ALL_COLONIES,
        'data' => Colony::where('dead', '0')->get(),
        'prices' => Prices::first()
    ]));
};

$server->onMessage = function (TcpConnection $connection, string $data) {
    $arr = json_decode($data, true);
    switch ($arr['type']) {
        case Types::CREATE_COLONY:
            $connection->send($colonyJson = json_encode([
                "type" => Types::COLONY_CREATED,
                "data" => ColonyCreatingService::createColony($arr['data'])
            ]));
            break;
    }
};

$server->onClose = function (TcpConnection $connection) {};

Worker::runAll();