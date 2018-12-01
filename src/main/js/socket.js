let socket = new WebSocket("ws://127.0.0.1:2346/");

socket.onopen = function() {
    console.log("Соединение установлено.");
};

socket.onclose = function(event) {
    if (event.wasClean) {
        console.log('Соединение закрыто чисто');
    } else {
        console.log('Обрыв соединения');
    }
    console.log('Код: ' + event.code + ' причина: ' + event.reason);
};

socket.onmessage = function(event) {
    const {data} = event;

    switch (data.type) {
        case ALL_COLONIES:
            break;
        case COLONY_CREATED:
            createColony(data.data);
            break;
    }
};

socket.onerror = function(error) {
    console.log("Ошибка " + error.message);
};