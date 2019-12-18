

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}



async function getLatestReadingByLocal(localName){

    var espacoEncoded = encodeURIComponent(localName);

    var uri = hostURL + "/local/"+espacoEncoded+"/weather-readings/latest";

    /* TODO:
    *   - meter a percentagem a atualizar graficamente
    * */

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        },
        error: function()
        {
            //alert("Não existem leituras para o espaço selecionado.");

            var tempChart = $("#easypiechart-red");
            var humChart = $("#easypiechart-blue");
            var co2Chart = $("#easypiechart-orange");

            tempChart[0].children[0].innerText = "---";
            humChart[0].children[0].innerText = "---";
            co2Chart[0].children[0].innerText= "---";

            tempChart.attr("data-percent", 0);
            humChart.attr("data-percent", 0);
            co2Chart.attr("data-percent", 0);
        }

    }).then(function(data) {
        console.log("ENTREI no latest Reading com " + espacoAtual.name);

        data = data[0];

        var tempChart = $("#easypiechart-red");
        var humChart = $("#easypiechart-blue");
        var co2Chart = $("#easypiechart-orange");

        if(data===null){

            tempChart[0].children[0].innerText = "---";
            humChart[0].children[0].innerText = "---";
            co2Chart[0].children[0].innerText= "---";

            tempChart.attr("data-percent", 0);
            humChart.attr("data-percent", 0);
            co2Chart.attr("data-percent", 0);

            return;
        }


        var temperatura = data["temperature"];
        var humidade = data["humidity"];
        var co2 = data["co2"];

        var tempPercentagem = (temperatura/(espacoAtual.max_temp_limit + espacoAtual.min_temp_limit))*100;
        var humPercentagem = (humidade/(espacoAtual.max_hum_limit + espacoAtual.min_hum_limit))*100;
        var co2Percentagem = (co2/espacoAtual.max_co2_limit)*100;

        tempChart[0].children[0].innerText = temperatura;
        humChart[0].children[0].innerText = humidade;
        co2Chart[0].children[0].innerText= co2;

        var tempRound = Math.round(tempPercentagem);
        var humRound =  Math.round(humPercentagem);
        var co2Round = Math.round(co2Percentagem);

        tempChart.attr("data-percent", tempRound);
        humChart.attr("data-percent", humRound);
        co2Chart.attr("data-percent", co2Round);


    });


    await sleep(61000);

    if(espacoAtual.name === localName) getLatestReadingByLocal(localName);
    else return ;

}



$(document).ready(function(){
    showUserMenu();
    showUserProfileNamePhoto();

    changeEspacoAtual(espacoAtual.name);
    setHref("#");

    getLatestReadingByLocal(espacoAtual.name);


    $("#espacosDropdown").click(function () {
        getLatestReadingByLocal(espacoAtual.name);
    })

});