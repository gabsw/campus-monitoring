var dataAtual;
var mesAtual;
var anoAtual;
var dadosMes = {};


function getLastDayOfMonth(data) {
    // argumento: data (classe)
    var dateWithLastDay = new Date(data.getFullYear(), data.getMonth()+1, 0);
    var lastDayOfMonth = dateWithLastDay.toDateString().split(" ")[2];

    return lastDayOfMonth;
}

function getMonthName(numeroMes) {
    var nome = "SEM_NOME";

    switch (numeroMes) {
        case 1:
            nome = "Janeiro";
            break;
        case 2:
            nome = "Fevereiro";
            break;
        case 3:
            nome = "Março";
            break;
        case 4:
            nome = "Abril";
            break;
        case 5:
            nome = "Maio";
            break;
        case 6:
            nome = "Junho";
            break;
        case 7:
            nome = "Julho";
            break;
        case 8:
            nome = "Agosto";
            break;
        case 9:
            nome = "Setembro";
            break;
        case 10:
            nome = "Outubro";
            break;
        case 11:
            nome = "Novembro";
            break;
        case 12:
            nome = "Dezembro";
            break;
        default:
            break;

    }

    return nome;

}


function decomporData(data){

    var dia = data.getDate();
    var mes = data.getMonth()+1;
    var nomeMes = getMonthName(mes);
    var ano = data.getFullYear();

    return [dia,mes,nomeMes,ano]
}


function showDadosCantina(espaco, data){

    // argumento: data (classe)
    var dataDecomposta = decomporData(data);
    var mes = dataDecomposta[1];
    if (mes.toString().length < 2){
        mes = "0"+mes;
    }
    var nomeMes = dataDecomposta[2];
    var ano = dataDecomposta[3];
    var ultimoDiaMes = getLastDayOfMonth(data);

    var startDate = ano + "-" + mes + "-" + "01";
    var endDate = ano + "-" + mes + "-" + ultimoDiaMes;


    var titulo = espaco + " | " + nomeMes + ", " + ano;
    $("#nomeEspacoTabela").text(titulo);

    var espacoEncoded = encodeURIComponent(espaco);

    var uri = hostURL + "/weather-stats/"+espacoEncoded+"?start_date="+startDate+"&end_date="+endDate;

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        }

    }).then(function(data) {

        dadosMes = {};

        if(data.length === 0){
            alert("Não existem dados para o espaço selecionado.");
        }

        var tabela = "";
        for (var i = 0; i < data.length; i++) {

            var elemento = data[i];
            var elementoDate = elemento.date;
            var diaChave = elementoDate.split("-")[2];

            var diai = i+1;

            dadosMes['dia'+diaChave] = [elemento.date, elemento.tempMax, elemento.tempMin, elemento.tempAvg, elemento.humMax,
                elemento.humMin,
                elemento.humAvg,
                elemento.co2Max,
                elemento.co2Min,
                elemento.co2Avg];

            var linha = `<tr id="${'dia'+diai}" >
                        <th scope="row">${elemento.date}</th>
                        <td>${elemento.tempMax}</td>
                        <td>${elemento.tempMin}</td>
                        <td>${elemento.tempAvg}</td>
                        <td>${elemento.humMax}</td>
                        <td>${elemento.humMin}</td>
                        <td>${elemento.humAvg}</td>
                        <td>${elemento.co2Max}</td>
                        <td>${elemento.co2Min}</td>
                        <td>${elemento.co2Avg}</td>
                    </tr>`;

            tabela = tabela.concat(linha);
        }

        $("#corpoTabela").html(tabela);
        console.log("dicionario: ", dadosMes);
    });
}



function showDadosByData(espaco, data){
    // argumento: data (classe)
    var dataDecomposta = decomporData(data);
    var mes = dataDecomposta[1];
    if (mes.toString().length < 2){
        mes = "0"+mes;
    }
    var nomeMes = dataDecomposta[2];
    var ano = dataDecomposta[3];
    var ultimoDiaMes = getLastDayOfMonth(data);

    var startDate = ano + "-" + mes + "-" + "01";
    var endDate = ano + "-" + mes + "-" + ultimoDiaMes;


    var titulo = espaco + " | " + nomeMes + ", " + ano;
    $("#nomeEspacoTabela").text(titulo);

    var espacoEncoded = encodeURIComponent(espaco);

    var uri = hostURL + "/weather-stats/"+espacoEncoded+"?start_date="+startDate+"&end_date="+endDate;


    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        }

    }).then(function(data) {

        dadosMes = {};

        if(data.length === 0){
            alert("Não existem dados para o espaço selecionado.");
        }

        for (var i = 0; i < data.length; i++) {
            var elemento = data[i];
            var elementoDate = elemento.date;
            var diaChave = elementoDate.split("-")[2];

            dadosMes['dia'+diaChave] = [elemento.date, elemento.tempMax, elemento.tempMin, elemento.tempAvg, elemento.humMax,
                elemento.humMin,
                elemento.humAvg,
                elemento.co2Max,
                elemento.co2Min,
                elemento.co2Avg];
        }

    });



}


function verMesTodo(){
    var i = 0;
    var tabela = "";

    for (var dia in dadosMes) {
        var diai = i+1;

        var linha = `<tr id="${'dia'+diai}" >
                        <th scope="row">${dadosMes[dia][0]}</th>
                        <td>${dadosMes[dia][1]}</td>
                        <td>${dadosMes[dia][2]}</td>
                        <td>${dadosMes[dia][3]}</td>
                        <td>${dadosMes[dia][4]}</td>
                        <td>${dadosMes[dia][5]}</td>
                        <td>${dadosMes[dia][6]}</td>
                        <td>${dadosMes[dia][7]}</td>
                        <td>${dadosMes[dia][8]}</td>
                        <td>${dadosMes[dia][9]}</td>
                    </tr>`

        tabela = tabela.concat(linha);

        i++;
    }

    $("#corpoTabela").html(tabela);

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


    var espacoSelected = $( "#selectEspaco option:selected" ).text();

    var dataInicial = new Date(); // comeca com a data do momento

    // mostrar os dados do espaco inicialmente selecionado
    showDadosCantina(espacoSelected, dataInicial);

    mesAtual = decomporData(new Date())[1];
    anoAtual = decomporData(new Date())[3];

    $("#selectEspaco").change( function(){
        espacoSelected = $(this).children("option:selected").text();
        // atualizar datas
        dataAtual = dataInicial;
        mesAtual = decomporData(dataInicial)[1];
        anoAtual = decomporData(dataInicial)[3];
        $('#inputCalendario').val("").datepicker("update"); // reset do valor visível do calendário
        showDadosCantina(espacoSelected, dataInicial);
    });



    $('#calendario').on('changeDate', function(event) {
        var data = new Date(event.format());

        if(data.toDateString().length !== 15){
            return;
        }

        var novoMes = decomporData(data)[1];
        var novoAno = decomporData(data)[3];

        if((mesAtual!==novoMes) || (anoAtual!==novoAno)){
            showDadosByData(espacoSelected, data);
            mesAtual = novoMes;
            anoAtual = novoAno;
        }

        var dia = decomporData(data)[0];
        if(dia.toString().length<2){
            dia = "0"+dia;
        }
        var diaSelecionado = "dia"+dia;

        var tabela = "";

        var dadosDoDia = dadosMes[diaSelecionado];

        if(dadosDoDia == null || dadosDoDia.length<9){
            alert("Não há dados para a data selecionada!")
        }
        else{
            var linha = `<tr id="${diaSelecionado}" >
                        <th scope="row">${dadosDoDia[0]}</th>
                        <td>${dadosDoDia[1]}</td>
                        <td>${dadosDoDia[2]}</td>
                        <td>${dadosDoDia[3]}</td>
                        <td>${dadosDoDia[4]}</td>
                        <td>${dadosDoDia[5]}</td>
                        <td>${dadosDoDia[6]}</td>
                        <td>${dadosDoDia[7]}</td>
                        <td>${dadosDoDia[8]}</td>
                        <td>${dadosDoDia[9]}</td>
                    </tr>`;

            tabela = tabela.concat(linha);
        }

        $("#corpoTabela").html(tabela);

        dataAtual = data;

    });


});


