
document.getElementById("mainCanvas").addEventListener("mousemove", function (event) {
    const {pageX, pageY} = event;

    const colony = onColony(pageX, pageY);
    if (canDisplayColonyName(colony)) {
        let colonyNameLabel = document.createElement("span");
        setupColonyNameLabel(colonyNameLabel, colony);

        document.getElementById("root").appendChild(colonyNameLabel);
    } else if (canRemoveColonyNameLabel(colony))
        document.getElementById("colonyName").remove();
});

function canDisplayColonyName(colony) {
    return colony && !document.getElementById("colonyName");
}

function canRemoveColonyNameLabel(colony) {
    return !colony && document.getElementById("colonyName");
}

function setupColonyNameLabel(colonyNameLabel, colony) {
    let colonyNameLabelStyle = colonyNameLabel.style;

    colonyNameLabel.id = "colonyName";
    colonyNameLabelStyle.position = "absolute";
    colonyNameLabelStyle.left = colony.location.x - 10 + "px";
    colonyNameLabelStyle.top = colony.location.y - circleSize - minimalColonySpace + 10 + "px";
    colonyNameLabel.textContent = colony.name;
}

document.getElementById("mainCanvas").addEventListener("click", function (event) {
    const {pageX, pageY} = event;

    if (document.getElementById("createMenu"))
        document.getElementById("createMenu").remove();

    closeAllInfoForms();

    const colony = onColony(pageX, pageY);
    if (!colony && canCreateColony(pageX, pageY))
        createCreateMenu(pageX, pageY);
    else if (colony)
        createColonyFormInfo(colony);
});

function onColony(pageX, pageY) {
    for (let i = 0; i < colonies.length; i++)
        if (inRange(pageX, pageY, colonies[i].location, circleSize))
            return colonies[i];

    return false;
}

function inRange(clickX, clickY, {x, y}, size) {
    return Math.pow(clickX - x, 2) + Math.pow(clickY - y, 2) <= Math.pow(size, 2);
}

function canCreateColony(pageX, pageY) {
    for (let i = 0; i < colonies.length; i++)
        if (inRange(pageX, pageY, colonies[i].location, circleSize * 2 + minimalColonySpace))
            return false;
    return true;
}

function createCreateMenu(pageX, pageY) {
    let menu = document.createElement("div");
    let newColonyName = document.createElement("input");
    let button = document.createElement("button");

    menu.id = "createMenu";
    let containerStyle = menu.style;
    containerStyle.position = "absolute";
    containerStyle.left = pageX + "px";
    containerStyle.top = pageY + "px";

    newColonyName.id = "newColonyName";

    button.textContent = "Create";
    button.addEventListener("click", function () {
        const name = document.getElementById("newColonyName").value;
        document.getElementById("createMenu").remove();

        if (name)
            generateColony(pageX, pageY, name);
    });

    if (document.getElementById('colonyFormInfo'))
        document.getElementById('colonyFormInfo').remove();

    massAppendChild(menu, [newColonyName, button]);
    document.getElementById("root").appendChild(menu);
}

function createColonyFormInfo(colony) {
    let formInfo = document.createElement("div");
    let colonyParameters = document.createElement("div");

    let formInfoStyle = formInfo.style;
    formInfo.id = "colonyFormInfo" + colony.name;
    formInfoStyle.position = "absolute";
    formInfoStyle.left = colony.location.x + 120 + "px";
    formInfoStyle.top = colony.location.y - circleSize - minimalColonySpace + 10 + "px";
    formInfoStyle.display = "flex";
    formInfoStyle.flexDirection = "column";
    formInfoStyle.alignItems = "center";
    formInfoStyle.justifyContent = "space-between";
    formInfoStyle.border = "1px solid black";

    let colonyParametersStyle = colonyParameters.style;
    colonyParametersStyle.display = "flex";
    colonyParametersStyle.width = "500px";
    colonyParametersStyle.justifyContent = "space-around";
    colonyParametersStyle.flexDirection = "row";

    let colonyName = document.createElement("span");
    colonyName.textContent = colony.name;

    let resources = document.createElement("div");
    setupResourcesUI(resources);

    let social = document.createElement("div");
    setupSocialUI(social);

    let complexResources = document.createElement("div");
    setupComplexResourcesUI(complexResources);

    massAppendChild(colonyParameters, [resources, social, complexResources]);
    massAppendChild(formInfo, [colonyName, colonyParameters]);

    document.getElementById("root").appendChild(formInfo);
}

function setupResourcesUI(resources) {
    let resourcesStyle = resources.style;
    resourcesStyle.display = "flex";
    resourcesStyle.flexDirection = "column";

    let title = document.createElement("div");
    title.textContent = "Basic resources";

    massAppendChild(resources, [title]);
}

function setupSocialUI(social) {
    let socialStyle = social.style;
    socialStyle.display = "flex";
    socialStyle.flexDirection = "column";

    let title = document.createElement("div");
    title.textContent = "Social";

    massAppendChild(social, [title]);
}

function setupComplexResourcesUI(complexResources) {
    let complexResourcesStyle = complexResources.style;
    complexResourcesStyle.display = "flex";
    complexResourcesStyle.flexDirection = "column";

    let title = document.createElement("div");
    title.textContent = "Complex resources";

    massAppendChild(complexResources, [title]);
}

function closeAllInfoForms() {
    for (let i = 0; i < colonies.length; i++) {
        if(document.getElementById("colonyFormInfo" + colonies[i].name))
            document.getElementById("colonyFormInfo" + colonies[i].name).remove();
    }
}
const circleSize = 60;
const minimalColonySpace = 40;
let colonies = [];

function setColonies(data) {
    for(let i = 0; i < data.length; i++)
        createColony(data[i]);
}

function generateColony(pageX, pageY, name) {
    const color = generateColonyColor();
    const colony = {location: {x: pageX, y: pageY}, name: name, color: color};

    socket.send(JSON.stringify({type: CREATE_COLONY, data: colony}));
}

function createColony(data) {
    colonies.push(data);

    const location = JSON.parse(data.location);

    drawEmptyColony(location.x, location.y, data.color);
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

const CREATE_COLONY = 1;
const ALL_COLONIES = 2;
const COLONY_CREATED = 3;
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
    console.log("Ошибка " + error.message);
};