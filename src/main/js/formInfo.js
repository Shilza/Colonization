
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