var hostURL = 'http://deti-engsoft-12.ua.pt';
//var hostURL = 'http://localhost:8080';

var userLogged;


function setupUser(data) {

    var username = data.username;
    var email = data.email;
    var nome = data.name;
    var admin = data.admin;
    var dataLocals = data.locals;

    var locais = [];

    for(var i = 0; i<dataLocals.length; i++){

        var doc = dataLocals[i];

        var espaco = new Espaco(doc.name, doc.max_temp_limit, doc.min_temp_limit, doc.max_hum_limit, doc.min_hum_limit, doc.max_co2_limit);

        locais.push(espaco);
    }

    var utilizador = new Utilizador(username, nome, email, admin);

    utilizador.espacos = locais;

    return utilizador;

}


function requestUser() {

    var insertedUsername = document.getElementById("usernameInput").value;
    var insertedPassword = document.getElementById("passwordInput").value;

    var uri = hostURL + "/users/authentication/" + insertedUsername + "/" + insertedPassword;

    var loginSuccess = false;

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(insertedUsername + ":" + insertedPassword));
        },
        error: function($xhr)
        {
            var erroDoc = $xhr.responseJSON;

            var erro1 = "User not found " + insertedUsername;
            var erro2 = "Login failed.";

            if (erroDoc["message"] === erro1){
                showDangerAlerts(true, false);
            }

            else if(erroDoc["message"] === erro2){
                showDangerAlerts(false, true);
            }

            else{
                showCannotCheckLoginAlert();
            }

        }

    }).then(function(data) {

        userLogged = setupUser(data);

        loginSuccess = true;

    });

    return loginSuccess;

}


function removeDangerAlerts() {
    var userNameAlert = document.getElementById("alertaUsername");
    var passwordAlert = document.getElementById("alertaPassword");
    var verificacaoAlert = document.getElementById("alertaVerificacao");

    if (userNameAlert.style.display === "block") {
        userNameAlert.style.display = "none";
    }

    if (passwordAlert.style.display === "block") {
        passwordAlert.style.display = "none";
    }

    if (verificacaoAlert.style.display === "block") {
        verificacaoAlert.style.display = "none";
    }
}


function showDangerAlerts(showUserAlert, showPasswordAlert) {

    var userNameAlert = document.getElementById("alertaUsername");
    var passwordAlert = document.getElementById("alertaPassword");

    if(showUserAlert){
        userNameAlert.style.display = "block";
    }
    if(showPasswordAlert){
        passwordAlert.style.display = "block";
    }

}


function showCannotCheckLoginAlert() {
    var verificacaoAlert = document.getElementById("alertaVerificacao");
    verificacaoAlert.style.display = "block";
}



$(document).ready(function(){
    removeDangerAlerts();

    $( "#loginForm" ).submit(function( event ) {
        removeDangerAlerts();

        var sucesso = requestUser();

        if (!sucesso) {
            event.preventDefault();
        } else {
            removeDangerAlerts();
            window.localStorage.setItem("userLogged", JSON.stringify(userLogged));
            window.localStorage.setItem("espacoAtual", userLogged.espacos[0].name);
        }

    });



});