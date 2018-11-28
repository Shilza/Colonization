
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
    for (let i = 0; i < colonies.length; i++) {
        if (inRange(pageX, pageY, colonies[i].location, circleSize * 2 + minimalColonySpace))
            return false;
    }
    return true;
}