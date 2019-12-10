//console.log("INICIO");

var hostURL = 'http://deti-engsoft-12.ua.pt';
var hostURLLocal = 'http://localhost:8080';

var dataAtual;
var mesAtual;
var anoAtual;
var dadosMes = {};


function getMethod(){
    var f = 0;


    if (f === 1) {
        $.getJSON('http://127.0.0.1:8080/local/', function(data) {

            console.log("ENTREI");

            var tabela = "";
            for (var i = 0; i < data.length; i++) {

                var elemento = data[i];
                // style="display:none;"
                var linha = `<tr id="${i+1}" >
                        <th scope="row">${elemento.name}</th>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.min_temp_limit}</td>
                        <td>${elemento.max_hum_limit}</td>
                        <td>${elemento.min_hum_limit}</td>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.min_hum_limit}</td>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.min_hum_limit}</td>
                    </tr>`

                //console.log("Linha ->" + linha);
                tabela = tabela.concat(linha);
            }


            $("#corpoTabela").html(tabela);
        });
    }

    else if (f === 2){
        $.getJSON('http://jsonplaceholder.typicode.com/users', function(data) {

            var tabela = "";
            for (var i = 0; i < data.length; i++) {

                var elemento = data[i];
                // style="display:none;"
                var linha = `<tr id="${i+1}" >
                        <th scope="row">${elemento.id}</th>
                        <td>${elemento.name}</td>
                        <td>${elemento.username}</td>
                        <td>${elemento.email}</td>
                        <td>${elemento.address['street']}</td>
                        <td>${elemento.address['zipcode']}</td>
                        <td>${elemento.address['city']}</td>
                        <td>${elemento.phone}</td>
                        <td>${elemento.website}</td>
                        <td>${elemento.company['name']}</td>
                    </tr>`

                //console.log("Linha ->" + linha);
                tabela = tabela.concat(linha);
            }


            $("#corpoTabela").html(tabela);
        });
    }
    else if(f==3) {
        $.ajax({
            url: "http://jsonplaceholder.typicode.com/users"
        }).then(function(data) {
            var tabela = "";
            for (var i = 0; i < data.length; i++) {

                var elemento = data[i];
                // style="display:none;"
                var linha = `<tr id="${i+1}" >
                        <th scope="row">${elemento.id}</th>
                        <td>${elemento.name}</td>
                        <td>${elemento.username}</td>
                        <td>${elemento.email}</td>
                        <td>${elemento.address['street']}</td>
                        <td>${elemento.address['zipcode']}</td>
                        <td>${elemento.address['city']}</td>
                        <td>${elemento.phone}</td>
                        <td>${elemento.website}</td>
                        <td>${elemento.company['name']}</td>
                    </tr>`

                //console.log("Linha ->" + linha);
                tabela = tabela.concat(linha);
            }


            $("#corpoTabela").html(tabela);
        });
    }

    else if(f===4){
        $.ajax({
            url: "http://localhost:8080/local/"

        }).then(function(data) {
            console.log("ENTREI");

            var tabela = "";
            for (var i = 0; i < data.length; i++) {

                var elemento = data[i];
                // style="display:none;"
                var linha = `<tr id="${i+1}" >
                        <th scope="row">${elemento.name}</th>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.min_temp_limit}</td>
                        <td>${elemento.max_hum_limit}</td>
                        <td>${elemento.min_hum_limit}</td>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.min_hum_limit}</td>
                        <td>${elemento.max_temp_limit}</td>
                        <td>${elemento.min_hum_limit}</td>
                    </tr>`

                //console.log("Linha ->" + linha);
                tabela = tabela.concat(linha);
            }


            $("#corpoTabela").html(tabela);
        });
    }
}


/*function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}*/


function getLastDayOfMonth(data) {
    // argumento: data (classe)
    //console.log("data var -> " + data);
    var dateWithLastDay = new Date(data.getFullYear(), data.getMonth()+1, 0);
    //console.log("dateWithLastDay -> ", dateWithLastDay);
    var lastDayOfMonth = dateWithLastDay.toDateString().split(" ")[2];
    //console.log("ultimo dia deste mês -> " + lastDayOfMonth);

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
            console.log("resultado NomeMes inválido!");
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
    //console.log("espaco encoded -> ", espacoEncoded);


    var uri = hostURL + "/weather-stats/"+espacoEncoded+"?start_date="+startDate+"&end_date="+endDate;
    console.log("uri -> ", uri);


    $.ajax({
        url: uri,
        async: false

    }).then(function(data) {
        console.log("ENTREI no alterar espaco");

        dadosMes = {};

        var tabela = "";
        for (var i = 0; i < data.length; i++) {


            var elemento = data[i];


            var elementoDate = elemento.date;

            var diaChave = elementoDate.split("-")[2];

            //console.log("doc -> ", elemento);
            // style="display:none;"
            var diai = i+1;

            dadosMes['dia'+diaChave] = [elemento.date, elemento.tempMax, elemento.tempMin, elemento.tempAvg, elemento.humMax,
                elemento.humMin,
                elemento.humAvg,
                elemento.co2Max,
                elemento.co2Min,
                elemento.co2Avg];

            //console.log("dadosMes dict -> ", dadosMes);


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
                    </tr>`

            //console.log("Linha ->" + linha);
            tabela = tabela.concat(linha);
        }


        $("#corpoTabela").html(tabela);
        console.log("dicionario: ", dadosMes);
    });
}



function showDadosByData(espaco, data){
    console.log("data arg by data -> ", data);
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
    //console.log("espaco encoded -> ", espacoEncoded);


    var uri = hostURL + "/weather-stats/"+espacoEncoded+"?start_date="+startDate+"&end_date="+endDate;
    console.log("uri -> ", uri)



    $.ajax({
        url: uri,
        async: false

    }).then(function(data) {
        console.log("ENTREI no showByData");

        dadosMes = {};


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



        console.log("dicionario local depois de encher -> ", dadosMes);


    });



}


function verMesTodo(){

    var i = 0;

    var tabela = "";

    console.log("ver mes todo dadosMes -> ", dadosMes);

    for (var dia in dadosMes) {

        console.log("dia dadosMes -> ", dia);

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

        //console.log("Linha ->" + linha);
        tabela = tabela.concat(linha);

        i++;
    }

    $("#corpoTabela").html(tabela);

}


function listarEspacosUser(){

    var espacosSelect = "";

    console.log("userAtual name -> ", userAtual.name);
    console.log("userAtual espacos -> ", userAtual.espacos);



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
    console.log("Espaço inicial -> " + espacoSelected);

    var dataInicial = new Date(); // comeca com a data do dia

    console.log("Data atual -> ", dataInicial.toDateString());

    // mostrar os dados do espaco inicialmente selecionado
    showDadosCantina(espacoSelected, dataInicial);

    mesAtual = decomporData(new Date())[1];
    anoAtual = decomporData(new Date())[3];



    console.log("mesAtual (inicial) -> " + mesAtual);
    console.log("anoAtual (inicial) -> " + anoAtual);

    $("#selectEspaco").change( function(){
        espacoSelected = $(this).children("option:selected").text();
        // atualizar datas
        dataAtual = dataInicial;
        mesAtual = decomporData(dataInicial)[1];
        anoAtual = decomporData(dataInicial)[3];
        console.log("Selecionaste -> " + espacoSelected);
        $('#inputCalendario').val(""); // reset do valor visível do calendário
        showDadosCantina(espacoSelected, dataInicial);
        //await sleep(1500);
    });



    $('#calendario').on('changeDate', function(event) {
        console.log("nova Data -> " + event.format());
        var data = new Date(event.format());


        console.log("data -> ", data);
        console.log("length da data -> " + data.toDateString().length);

        if(data.toDateString().length !== 15){
            console.log("A DATA É A MESMA!");
            return;
        }

        var novoMes = decomporData(data)[1];
        var novoAno = decomporData(data)[3];

        console.log("mesAtual (inicial) -> " + mesAtual);
        console.log("anoAtual (inicial) -> " + anoAtual);

        console.log("novoMes -> " + novoMes);
        console.log("novoAno -> " + novoAno);


        if((mesAtual!==novoMes) || (anoAtual!==novoAno)){
            console.log("ENTREI NO IF");
            showDadosByData(espacoSelected, data);
            //await sleep(1500);
            console.log("novo dadosMes -> ", dadosMes);
            mesAtual = novoMes;
            anoAtual = novoAno;

            console.log("mes atual -> ", mesAtual);
        }

        var dia = decomporData(data)[0];
        if(dia.toString().length<2){
            dia = "0"+dia;
        }
        var diaSelecionado = "dia"+dia;

        console.log("diaSelecionado -> ", diaSelecionado);

        var tabela = "";

        var dadosDoDia = dadosMes[diaSelecionado];

        console.log("dadosDoDia -> ", dadosDoDia);


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
                    </tr>`

            //console.log("Linha ->" + linha);
            tabela = tabela.concat(linha);
        }

        $("#corpoTabela").html(tabela);

        dataAtual = data;



    });


});


