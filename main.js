const circleSize = 60;

document.getElementById("mainCanvas").addEventListener("click", function (event) {

    const {pageX, pageY} = event;
    if (document.getElementById("mainContainer"))
        document.getElementById("mainContainer").remove();

    createContainer(pageX, pageY);
});

function drawEmptyColony(pageX, pageY) {
    let ctx = document.getElementById("mainCanvas");
    let context = ctx.getContext("2d");
    context.beginPath();

    const {r, g, b} = generateColonyColor();
    context.strokeStyle = 'rgb(' + r + ',' + g + ',' + b + ')';
    context.lineWidth = 3;
    context.arc(pageX, pageY, circleSize, 0, 2 * Math.PI);

    context.stroke();
}

function createContainer(pageX, pageY) {
    let container = document.createElement("div");
    let colonyName = document.createElement("input");
    let button = document.createElement("button");

    container.id = "mainContainer";
    let containerStyle = container.style;
    containerStyle.position = "absolute";
    containerStyle.left = pageX + 80 + "px";
    containerStyle.top = pageY - 100 + "px";

    button.textContent = "Ok";
    button.addEventListener("click", function() {
        document.getElementById("mainContainer").remove();
        drawEmptyColony(pageX, pageY);
    });

    let element = document.getElementById("root");
    container.appendChild(colonyName);
    container.appendChild(button);
    element.appendChild(container);
}

function generateColonyColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);

    return {r, g, b};
}

let socket = new WebSocket("ws://127.0.0.1:2346/");

//
// socket.onopen = function() {
//     alert("Соединение установлено.");
// };
//
// socket.onclose = function(event) {
//     if (event.wasClean) {
//         alert('Соединение закрыто чисто');
//     } else {
//         alert('Обрыв соединения'); // например, "убит" процесс сервера
//     }
//     alert('Код: ' + event.code + ' причина: ' + event.reason);
// };
//
// socket.onmessage = function(event) {
//     alert("Получены данные " + event.data);
// };
//
// socket.onerror = function(error) {
//     alert("Ошибка " + error.message);
// };
