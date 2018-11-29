<?php
require_once __DIR__ . '/../../../vendor/autoload.php';
require_once 'config.php';

use Colonization\Constants\Types;
use Colonization\Model\Colony;
use Colonization\Service\ColonyService;
use Workerman\Connection\TcpConnection;
use Workerman\Worker;


$server = new Worker("websocket://0.0.0.0:2346");

$server->count = 1;

$server->onConnect = function (TcpConnection $connection) {
    $connection->send(json_encode(['type' => Types::ALL_COLONIES, 'data' => Colony::all()]));
};

$server->onMessage = function (TcpConnection $connection, string $data) {
    $arr = json_decode($data, false);
    switch($arr['type']){
        case Types::CREATE_COLONY:
            ColonyService::createColony($arr['data']);
            break;
    }
};

$server->onClose = function (TcpConnection $connection) {};

Worker::runAll();