console.log("INICIO");

var locais_url = 'http://localhost:8080/local/';




function getMethod(){
    var f = 4;


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






function showDadosCantina(espaco){
    var titulo = espaco + " | " + "Novembro, " + " 2019";
    $("#nomeEspacoTabela").text(titulo);

    console.log("espaco arg -> ", espaco);
    var espacoEncoded = encodeURIComponent(espaco);
    //console.log("espaco encoded -> ", espacoEncoded);
    var uri = "http://localhost:8080/weather-stats/"+espacoEncoded;
    console.log("uri -> ", uri)


    $.ajax({
        url: uri

    }).then(function(data) {
        console.log("ENTREI no alterar espaco");

        var tabela = "";
        for (var i = 0; i < data.length; i++) {


            var elemento = data[i];
            console.log("doc -> ", elemento);
            // style="display:none;"
            var linha = `<tr id="${i+1}" >
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
    });
}



$(document).ready(function(){
    getMethod();
    var espacoSelected = $( "#selectEspaco option:selected" ).text();
    console.log("Espaço inicial -> " + espacoSelected);
    var currentDate = $( "#calendar" ).datepicker( "getDate" );
    console.log("currentDate -> " + currentDate);

    // mostrar os dados do espaco inicialmente selecionado
    showDadosCantina(espacoSelected);


    $("#selectEspaco").change(function(){
        espacoSelected = $(this).children("option:selected").text();
        console.log("Selecionaste -> " + espacoSelected);
        showDadosCantina(espacoSelected);
    });


});


