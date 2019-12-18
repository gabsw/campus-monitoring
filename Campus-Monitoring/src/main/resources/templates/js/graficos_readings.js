
var tempArray = [];
var humArray = [];
var co2Array = [];
var eixoX = [];

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}



async function showDadosBoardEspacoAtual(localName){

    var espacoEncoded = encodeURIComponent(localName);

    var uri = hostURL + "/local/"+espacoEncoded+"/weather-readings/latest?limit=65"; // buscar a ultima hora


    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        },
        error: function()
        {

            alert("Não foi possível obter leituras para " + espacoAtual.name);


            lineChartDataTemp.labels = ["00:00", "00:05", "00:10", "00:15", "00:20", "00:25"];
            lineChartDataHum.labels = ["00:00", "00:05", "00:10", "00:15", "00:20", "00:25"];
            lineChartDataCO2.labels = ["00:00", "00:05", "00:10", "00:15", "00:20", "00:25"];

            lineChartDataTemp.datasets[0].data= [0,0,0,0,0,0];
            lineChartDataHum.datasets[0].data= [0,0,0,0,0,0];
            lineChartDataCO2.datasets[0].data= [0,0,0,0,0,0];

            var chart1 = document.getElementById("line-chart").getContext("2d");
            window.myLine = new Chart(chart1).Line(lineChartDataTemp, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

            var chart2 = document.getElementById("line-chart-2").getContext("2d");
            window.myLine = new Chart(chart2).Line(lineChartDataHum, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

            var chart3 = document.getElementById("line-chart-3").getContext("2d");
            window.myLine = new Chart(chart3).Line(lineChartDataCO2, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });
        }

    }).then(function(data) {


        if(data.length===0){

            alert("Não existem leituras para " + espacoAtual.name);

            lineChartDataTemp.labels = ["00:00", "00:05", "00:10", "00:15", "00:20", "00:25"];
            lineChartDataHum.labels = ["00:00", "00:05", "00:10", "00:15", "00:20", "00:25"];
            lineChartDataCO2.labels = ["00:00", "00:05", "00:10", "00:15", "00:20", "00:25"];

            lineChartDataTemp.datasets[0].data= [0,0,0,0,0,0];
            lineChartDataHum.datasets[0].data= [0,0,0,0,0,0];
            lineChartDataCO2.datasets[0].data= [0,0,0,0,0,0];

            var chart1 = document.getElementById("line-chart").getContext("2d");
            window.myLine = new Chart(chart1).Line(lineChartDataTemp, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

            var chart2 = document.getElementById("line-chart-2").getContext("2d");
            window.myLine = new Chart(chart2).Line(lineChartDataHum, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

            var chart3 = document.getElementById("line-chart-3").getContext("2d");
            window.myLine = new Chart(chart3).Line(lineChartDataCO2, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });


        }else {

            for(var i = data.length-1; i >= 0; i--){

                doc = data[i];

                var temperatura = parseFloat(doc["temperature"]);
                var humidade = doc["humidity"];
                var co2 = doc["co2"];

                var hora = doc.dateTime.split("T")[1].split(":")[0];
                var minutos = doc.dateTime.split("T")[1].split(":")[1];
                var minutosInt = parseInt(minutos);

                if(minutosInt%5!==0){
                    continue;
                }

                if(eixoX.length===13){
                    console.log("shift: ", eixoX.shift());
                    eixoX.push(hora+":"+minutos);
                }else{
                    eixoX.push(hora+":"+minutos);
                }


                if(tempArray.length===13){
                    tempArray.shift();
                }
                tempArray.push(temperatura);


                if(humArray.length===13){
                    humArray.shift();
                }
                humArray.push(humidade);

                if(co2Array.length===13){
                    co2Array.shift();
                }
                co2Array.push(co2);

            }


            lineChartDataTemp.labels = eixoX;
            lineChartDataHum.labels = eixoX;
            lineChartDataCO2.labels = eixoX;

            lineChartDataTemp.datasets[0].data= tempArray;
            lineChartDataHum.datasets[0].data= humArray;
            lineChartDataCO2.datasets[0].data= co2Array;


            var chart1 = document.getElementById("line-chart").getContext("2d");
            window.myLine = new Chart(chart1).Line(lineChartDataTemp, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

            var chart2 = document.getElementById("line-chart-2").getContext("2d");
            window.myLine = new Chart(chart2).Line(lineChartDataHum, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

            var chart3 = document.getElementById("line-chart-3").getContext("2d");
            window.myLine = new Chart(chart3).Line(lineChartDataCO2, {
                responsive: true,
                scaleLineColor: "rgba(0,0,0,.2)",
                scaleGridLineColor: "rgba(0,0,0,.05)",
                scaleFontColor: "#c5c7cc"
            });

        }
    });


    await sleep(61000);

    if(espacoAtual.name === localName) showDadosBoardEspacoAtual(localName);
    else return ;
}



$(document).ready(function(){

    showDadosBoardEspacoAtual(espacoAtual.name);



    $("#espacosDropdown").click(function () {
        showDadosBoardEspacoAtual(espacoAtual.name);
    })

});