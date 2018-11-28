const circleSize = 60;
const minimalColonySpace = 40;
let colonies = [];

function createNewColony(pageX, pageY, name) {
    colonies.push({location: {x: pageX, y: pageY}, name: name});
    drawEmptyColony(pageX, pageY);
}

function drawEmptyColony(pageX, pageY) {
    let context = document.getElementById("mainCanvas").getContext("2d");
    context.beginPath();

    const {r, g, b} = generateColonyColor();
    context.strokeStyle = 'rgb(' + r + ',' + g + ',' + b + ')';
    context.lineWidth = 3;
    context.arc(pageX, pageY, circleSize, 0, 2 * Math.PI);

    context.stroke();
}

function generateColonyColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);

    return {r, g, b};
}

function massAppendChild(mainElement, elements) {
    for (let i = 0; i < elements.length; i++)
        mainElement.appendChild(elements[i]);
}