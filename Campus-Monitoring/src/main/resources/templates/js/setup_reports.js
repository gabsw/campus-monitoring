var dataInicio = null;
var dataFim = null;
var espacoSelected = null;

var desempenhoDict = {"Boa":"btn-success", "Média":"btn-warning", "Má":"btn-danger"};


function clearAllFields() {
    dataInicio = null;
    dataFim = null;

    $("#averageRating").html("---");
    $("#averageAlarmsPerDay").html("---");
    $("#numberOngoingAlarms").html("---");
    $("#totalMaxTempAlarms").html("---");
    $("#totalMinTempAlarms").html("---");
    $("#totalMaxHumAlarms").html("---");
    $("#totalMinHumAlarms").html("---");
    $("#totalMaxCo2Alarms").html("---");

    //var botaoPerformance = $("#botaoPerformance")[0];
    $("#botaoPerformance")[0].innerText = "----";
    $("#botaoPerformance")[0].className = "btn btn-lg";
}

function getReportByLocal(localname, startDate, endDate){

    var titulo = localname + " | (de " + startDate + " a " + endDate + ")";
    $("#nomeEspacoTabela").text(titulo);

    var espacoEncoded = encodeURIComponent(localname);

    var uri = hostURL + "/local/"+espacoEncoded+"/report?start_date="+startDate+"&end_date="+endDate;

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        }

    }).then(function(data) {

        var averageRating = data.averageRating;
        var averageAlarmsPerDay = data.averageAlarmsPerDay;
        var numberOngoingAlarms = data.numberOngoingAlarms;
        var totalMaxTempAlarms = data.totalMaxTempAlarms;
        var totalMinTempAlarms = data.totalMinTempAlarms;
        var totalMaxHumAlarms = data.totalMaxHumAlarms;
        var totalMinHumAlarms = data.totalMinHumAlarms;
        var totalMaxCo2Alarms = data.totalMaxCo2Alarms;
        var performance = data.performance;

        if(averageRating === null) $("#averageRating").html("ND");
        else $("#averageRating").html(averageRating);

        $("#averageAlarmsPerDay").html(averageAlarmsPerDay);
        $("#numberOngoingAlarms").html(numberOngoingAlarms);
        $("#totalMaxTempAlarms").html(totalMaxTempAlarms);
        $("#totalMinTempAlarms").html(totalMinTempAlarms);
        $("#totalMaxHumAlarms").html(totalMaxHumAlarms);
        $("#totalMinHumAlarms").html(totalMinHumAlarms);
        $("#totalMaxCo2Alarms").html(totalMaxCo2Alarms);

        $("#botaoPerformance")[0].innerText = performance;

        var botao = $("#botaoPerformance");

        // reset das classes
        $("#botaoPerformance")[0].className = "";
        botao.addClass("btn");
        botao.addClass("btn-lg");
        botao.addClass(desempenhoDict[performance]);

        $("#textInput").val(performance);
    });
}


function formatarData(dataString) {
    // dataString: formato mes/dia/ano

    var dataSplitted = dataString.split("/");
    var mes = dataSplitted[0];
    var dia = dataSplitted[1];
    var ano = dataSplitted[2];

    var dataFinal = ano + "-" + mes + "-" + dia;

    return dataFinal;

}


function compararDatas(startDate, endDate) {
    // args: String: formato ano-mes-dia
    // garantir que a data de início é anterior à de fim

    var dataInicio = new Date(startDate);
    var dataFim = new Date(endDate);

    return dataInicio < dataFim;

}


function listarEspacosUser(){
    var espacosSelect = "";

    for (var i = 0; i < userAtual.espacos.length; i++){
        var espaco = userAtual.espacos[i];
        var opcaoEspaco = `<option value="${espaco.name}">${espaco.name}</option>`
        espacosSelect = espacosSelect.concat(opcaoEspaco);
    }

    $("#selectEspaco").html(espacosSelect);
}


$(document).ready(function(){
    showUserMenu();
    showUserProfileNamePhoto();
    listarEspacosUser();

    // alterar o aspeto e links dos espacos no menu de dropdown
    resetLiActive();
    setHref("board_geral.html");

    espacoSelected = $( "#selectEspaco option:selected" ).text();
    $("#nomeEspacoTabela").text(espacoSelected);

    clearAllFields();


    $("#selectEspaco").change( function(){
        espacoSelected = $(this).children("option:selected").text();
        // atualizar datas
        $('#nomeEspacoTabela').text(espacoSelected);

        $('#inputCalendario').val("").datepicker("update");
        $('#inputCalendarioEnd').val("").datepicker("update");

        clearAllFields();
    });



    $('#calendario').on('changeDate', function(event) {
        dataInicio = formatarData($('#inputCalendario').val());

        if(dataFim !== null){
            if(compararDatas(dataInicio, dataFim)){
                getReportByLocal(espacoSelected, dataInicio, dataFim);
            }
            else{
                alert("A data de início deve ser anterior à de fim!");
            }
        }

    });

    $('#calendarioEnd').on('changeDate', function(event) {
        dataFim = formatarData($('#inputCalendarioEnd').val());

        if(dataInicio !== null){
            if(compararDatas(dataInicio, dataFim)){
                getReportByLocal(espacoSelected, dataInicio, dataFim);
            }
            else{
                alert("A data de início deve ser anterior à de fim!");
            }
        }

    });

});

