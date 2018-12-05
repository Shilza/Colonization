let socket = new WebSocket("ws://127.0.0.1:2346/");

socket.onopen = function() {
    console.log("Соединение установлено.");
};

socket.onclose = function(event) {
    alert('Code: ' + event.code + '\nReason: ' + event.reason);
};

socket.onmessage = function(event) {
    const data = JSON.parse(event.data);

    switch (data.type) {
        case ALL_COLONIES:
            setColonies(data.data);
            break;
        case COLONY_CREATED:
            createColony(data.data);
            break;
    }
};

socket.onerror = function(error) {
    alert(error.message);
};