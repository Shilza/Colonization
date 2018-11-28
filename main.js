const circleSize = 60;
const minimalColonySpace = 40;
let colonies = [];

document.getElementById("mainCanvas").addEventListener("mousemove", function (event) {
    const {pageX, pageY} = event;

    const colony = onColony(pageX, pageY);
    if (colony && !document.getElementById("colonyName")) {
        let colonyNameLabel = document.createElement("span");
        let colonyNameLabelStyle = colonyNameLabel.style;

        colonyNameLabel.id = "colonyName";
        colonyNameLabelStyle.position = "absolute";
        colonyNameLabelStyle.left = colony.location.x - 10 + "px";
        colonyNameLabelStyle.top = colony.location.y - circleSize - minimalColonySpace + 10 + "px";
        colonyNameLabel.textContent = colony.name;

        const element = document.getElementById("root");
        element.appendChild(colonyNameLabel);
    } else if (!colony && document.getElementById("colonyName")) {
        document.getElementById("colonyName").remove();
    }
});

document.getElementById("mainCanvas").addEventListener("click", function (event) {
    const {pageX, pageY} = event;
    if (document.getElementById("mainContainer"))
        document.getElementById("mainContainer").remove();

    const colony = onColony(pageX, pageY);
    if (!colony && canCreateColony(pageX, pageY))
        createCreateContainer(pageX, pageY);
    else if (!document.getElementById('colonyFormInfo' + colony.name))
        createColonyFormInfo(colony);
});

function createColonyFormInfo(colony) {
    let formInfo = document.createElement("div");
    let parameters = document.createElement("div");

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

    let parametersStyle = parameters.style;
    parametersStyle.display = "flex";
    parametersStyle.width = "500px";
    parametersStyle.justifyContent = "space-around";
    parametersStyle.flexDirection = "row";

    let colonyName = document.createElement("span");
    colonyName.textContent = colony.name;

    let resources = document.createElement("div");
    let resourcesStyle = resources.style;
    resourcesStyle.display = "flex";
    resourcesStyle.flexDirection = "column";

    let social = document.createElement("div");
    let socialStyle = social.style;
    socialStyle.display = "flex";
    socialStyle.flexDirection = "column";

    let complexResources = document.createElement("div");
    let complexResourcesStyle = complexResources.style;
    complexResourcesStyle.display = "flex";
    complexResourcesStyle.flexDirection = "column";

    let resourcesTitle = document.createElement("div");
    resourcesTitle.textContent = "Basic resources";

    let socialTitle = document.createElement("div");
    socialTitle.textContent = "Social";

    let complexResourcesTitle = document.createElement("div");
    complexResourcesTitle.textContent = "Complex resources";

    customAppendChild(resources, [resourcesTitle]);
    customAppendChild(social, [socialTitle]);
    customAppendChild(complexResources, [complexResourcesTitle]);
    customAppendChild(parameters, [resources, social, complexResources]);
    customAppendChild(formInfo, [colonyName, parameters]);

    let element = document.getElementById("root");
    element.appendChild(formInfo);
}

function customAppendChild(mainElement, elements) {
    for (let i = 0; i < elements.length; i++)
        mainElement.appendChild(elements[i]);
}

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
    for (let i = 0; i < colonies.length; i++) {
        if (inRange(pageX, pageY, colonies[i].location, circleSize * 2 + minimalColonySpace))
            return false;
    }
    return true;
}

function createNewColony(pageX, pageY, name) {
    colonies.push({location: {x: pageX, y: pageY}, name: name});
    drawEmptyColony(pageX, pageY);
}

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

function createCreateContainer(pageX, pageY) {
    let container = document.createElement("div");
    let newColonyName = document.createElement("input");
    let button = document.createElement("button");

    container.id = "mainContainer";
    let containerStyle = container.style;
    containerStyle.position = "absolute";
    containerStyle.left = pageX + "px";
    containerStyle.top = pageY + "px";

    newColonyName.id = "newColonyName";

    button.textContent = "Ok";
    button.addEventListener("click", function () {
        const name = document.getElementById("newColonyName").value;
        document.getElementById("mainContainer").remove();

        if (name)
            createNewColony(pageX, pageY, name);
    });

    if (document.getElementById('colonyFormInfo'))
        document.getElementById('colonyFormInfo').remove();

    let element = document.getElementById("root");
    container.appendChild(newColonyName);
    container.appendChild(button);
    element.appendChild(container);
}

function generateColonyColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);

    return {r, g, b};
}
