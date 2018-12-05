
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
    formInfoStyle.border = "1px solid " + colony.color;
    formInfoStyle.background = "white";

    let colonyParametersStyle = colonyParameters.style;
    colonyParametersStyle.display = "flex";
    colonyParametersStyle.width = "400px";
    colonyParametersStyle.justifyContent = "space-around";
    colonyParametersStyle.flexDirection = "row";

    let colonyName = document.createElement("span");
    colonyName.style.marginBottom = "3%";
    colonyName.style.color = "red";
    colonyName.textContent = colony.name;

    let nameOfResources = document.createElement("div");
    setupNameOfResources(nameOfResources);

    let resources = document.createElement("div");
    setupResources(colony, resources);

    massAppendChild(colonyParameters, [nameOfResources, resources]);
    massAppendChild(formInfo, [colonyName, colonyParameters]);

    document.getElementById("root").appendChild(formInfo);
}

function setupNameOfResources(container) {
    let resourcesStyle = container.style;
    resourcesStyle.display = "flex";
    resourcesStyle.flexDirection = "column";

    let title = document.createElement("span");
    title.textContent = "Resource";

    let type = document.createElement("span");
    type.textContent = "Type";

    let water = document.createElement("span");
    water.textContent = "Water";

    let wood = document.createElement("span");
    wood.textContent = "Wood";

    let metal = document.createElement("span");
    metal.textContent = "Metal";

    let fertility = document.createElement("span");
    fertility.textContent = "Fertility";

    let money = document.createElement("span");
    money.textContent = "Money";

    let war = document.createElement("span");
    war.textContent = "War";

    let livingLevel = document.createElement("span");
    livingLevel.textContent = "Living level";

    let lifespan = document.createElement("span");
    lifespan.textContent = "Lifespan";

    let age = document.createElement("span");
    age.textContent = "Age";

    let food = document.createElement("span");
    food.textContent = "Food";

    let weapon = document.createElement("span");
    weapon.textContent = "Weapon";

    let tools = document.createElement("span");
    tools.textContent = "Tools";

    massAppendChild(container, [
        title, type, water, wood, metal,
        fertility, livingLevel, money, lifespan,
        age, food, weapon, tools
    ]);
}

function setupResources(colony, container) {

    let resourcesStyle = container.style;
    resourcesStyle.display = "flex";
    resourcesStyle.flexDirection = "column";

    let title = document.createElement("span");
    title.textContent = "Data";

    let type = document.createElement("span");
    type.textContent = colony.type;

    let water = document.createElement("span");
    water.textContent = colony.water_availability;

    let wood = document.createElement("span");
    wood.textContent = colony.wood_availability;

    let metal = document.createElement("span");
    metal.textContent = colony.metal_availability;

    let fertility = document.createElement("span");
    fertility.textContent = colony.fertility;

    let money = document.createElement("span");
    money.textContent = colony.money ? colony.money : 0;

    let war = document.createElement("span");
    war.textContent = colony.war ? colony.war : 0;

    let livingLevel = document.createElement("span");
    livingLevel.textContent = colony.livingLevel ? colony.money : 0;

    let lifespan = document.createElement("span");
    lifespan.textContent = colony.lifespan ? colony.lifespan : 0;

    let age = document.createElement("span");
    age.textContent = colony.age ? colony.age : 0;

    let food = document.createElement("span");
    food.textContent = colony.food ? colony.food : 0;

    let weapon = document.createElement("span");
    weapon.textContent = colony.weapon ? colony.weapon : 0;

    let tools = document.createElement("span");
    tools.textContent = colony.tools ? colony.tools : 0;

    massAppendChild(container, [
        title, type, water, wood, metal,
        fertility, livingLevel, money, lifespan,
        age, food, weapon, tools
    ]);
}

function closeAllInfoForms() {
    for (let i = 0; i < colonies.length; i++) {
        if(document.getElementById("colonyFormInfo" + colonies[i].name))
            document.getElementById("colonyFormInfo" + colonies[i].name).remove();
    }
}