let isPricesAreShown = false;

document.getElementById("prices_button").onclick = function () {
    isPricesAreShown = !isPricesAreShown;

    if(isPricesAreShown)
        document.getElementById("prices").style.display = "block";
    else
        document.getElementById("prices").style.display ="none";
};