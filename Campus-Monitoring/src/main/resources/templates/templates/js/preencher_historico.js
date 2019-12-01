console.log("INICIO");

var locais_url = 'http://localhost:8080/local/';

function createCORSRequest(method, url) {
    var xhr = new XMLHttpRequest();
    if ("withCredentials" in xhr) {
        // XHR for Chrome/Firefox/Opera/Safari.
        xhr.open(method, url, true);
    } else if (typeof XDomainRequest != "undefined") {
        // XDomainRequest for IE.
        xhr = new XDomainRequest();
        xhr.open(method, url);
    } else {
        // CORS not supported.
        xhr = null;
    }
    return xhr;
}

// Helper method to parse the title tag from the response.
function getTitle(text) {
    return text.match('<title>(.*)?</title>')[1];
}

// Make the actual CORS request.
function makeCorsRequest() {
    // This is a sample server that supports CORS.
    var url = 'http://html5rocks-cors.s3-website-us-east-1.amazonaws.com/index.html';

    var xhr = createCORSRequest('GET', url);
    if (!xhr) {
        alert('CORS not supported');
        return;
    }

    // Response handlers.
    xhr.onload = function() {
        var text = xhr.responseText;
        var title = getTitle(text);
        alert('Response from CORS request to ' + url + ': ' + title);
    };

    xhr.onerror = function() {
        alert('Woops, there was an error making the request.');
    };

    xhr.send();
}

//var url = 'http://api.alice.com/cors';
var xhr = createCORSRequest('GET', locais_url);
xhr.send();

//makeCorsRequest();


/*

$.getJSON('http://localhost:8080/local/', function(data) {

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
*/


/*
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

*/


$(document).ready(function(){
    var espacoSelected = $( "#selectEspaco option:selected" ).text();
    console.log("Espaço inicial -> " + espacoSelected);
    var currentDate = $( "#calendar" ).datepicker( "getDate" );
    console.log("currentDate -> " + currentDate);


    $("#selectEspaco").change(function(){
        espacoSelected = $(this).children("option:selected").text();
        console.log("Selecionaste -> " + espacoSelected);
    });

    $("#calendar").change(function(){
        var data = $(this).getDate();
        console.log("Selecionaste data -> " + data);
    });

    $("#calendar").datepicker({
        onSelect: function(dateText, inst) {
            var dateAsString = dateText; //the first parameter of this function
            var dateAsObject = $(this).datepicker( 'getDate' ); //the getDate method
            console.log("Data selecionada -> " + dateAsString);
        }
    });
});


