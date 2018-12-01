const circleSize = 60;
const minimalColonySpace = 40;
let colonies = [];

function generateColony(pageX, pageY, name) {
    const color = generateColonyColor();
    const colony = {location: {x: pageX, y: pageY}, name: name, color: color};

    socket.send(JSON.stringify({type: CREATE_COLONY, data: colony}));
}

function createColony(data) {
    colonies.push(data);

    drawEmptyColony(data.location.x, data.location.y, data.color);
}

function drawEmptyColony(pageX, pageY, color) {
    let context = document.getElementById("mainCanvas").getContext("2d");
    context.beginPath();

    context.strokeStyle = color;
    context.lineWidth = 3;
    context.arc(pageX, pageY, circleSize, 0, 2 * Math.PI);

    context.stroke();
}

function generateColonyColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);

    return '#' + rgbToHex(r) + rgbToHex(g) + rgbToHex(b);
}

function rgbToHex(rgb) {
    let hex = Number(rgb).toString(16);

    if (hex.length < 2)
        hex = "0" + hex;

    return hex;
}

function massAppendChild(mainElement, elements) {
    for (let i = 0; i < elements.length; i++)
        mainElement.appendChild(elements[i]);
}