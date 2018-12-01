
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