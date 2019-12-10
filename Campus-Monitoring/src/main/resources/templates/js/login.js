

var listaUsers = [];

var userLogged;



function loadUsers() {
    /* TODO: fazer request por users e guardá-los (só para testar)
    *   otimização: fazer request cada vez que o utilizador
    *   clica em login e ver se devolveu algo */

    var user1 = new Utilizador("pedro_bastos",
        "Pedro Ferreira Bastos",
        "campus.monitoring.ies+pedrobastos@gmail.com",
        true); // classe



    var user2 = new Utilizador('joao_marques', 'Joao Almeida Marques', 'campus.monitoring.ies+joaomarques@gmail.com', true);
    var user3 = new Utilizador('maria_cardoso', 'Maria Silva Cardoso', 'campus.monitoring.ies+mariacardoso@gmail.com', false);
    var user4 = new Utilizador('joana_martins', 'Joana Costa Martins', 'campus.monitoring.ies+joanamartins@gmail.com', false);

    var refSantiago = new Espaco('Refeitório de Santiago', 25.00, 15.00, 60, 40, 1000);
    var refCrasto = new Espaco('Refeitório do Castro', 26.00, 16.00, 65, 45, 1000);
    var cafetariaEsan = new Espaco('Cafetaria da ESAN', 24.00, 14.00, 70, 35, 1000);
    var ieeta = new Espaco('IEETA', 25.00, 20.00, 60, 40, 1000);

    user1.espacos = [refSantiago, refCrasto, cafetariaEsan];
    user2.espacos = [ieeta];
    user3.espacos = [cafetariaEsan];
    user4.espacos = [cafetariaEsan];

    listaUsers = [user1, user2, user3, user4];

    //console.log("listaUsers -> ", listaUsers);

}





function checkUsername() {
    // TODO: falta robustez nisto

    var insertedUsername = document.getElementById("usernameInput").value;

    console.log("insertedUsername -> ", insertedUsername);


    for (var i=0; i<listaUsers.length; i++){

        var u = listaUsers[i];

        console.log("u for -> ", u.username);

        if (u.username === insertedUsername){
            console.log(insertedUsername + " existe!");
            userLogged = u;
            //alert("userLogged -> " + userLogged.name + " (" + userLogged.username + ")");
            return true;
        }
    }

    return false;

}


function checkPassword() {
    // TODO: verificacao password
    return true;
}



function removeDangerAlerts() {


    var userNameAlert = document.getElementById("alertaUsername");
    var passwordAlert = document.getElementById("alertaPassword");

    if (userNameAlert.style.display === "block") {
        userNameAlert.style.display = "none";
    }

    if (passwordAlert.style.display === "block") {
        passwordAlert.style.display = "none";
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



function validateUser() {

    console.log("ENTREI NA VALIDACAO")

    removeDangerAlerts();

    var userIsValid = checkUsername() && checkPassword();


    if (!userIsValid){
        if (!checkUsername()){
            showDangerAlerts(true, false);
        }

        if (!checkPassword()){
            showDangerAlerts(false, true);
        }
    }


    return userIsValid;

}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}


async function showDebug(){
    if(validateUser()){
        removeDangerAlerts();
        //userAtual = userLogged;

        await sleep(10000);
    }

}


$(document).ready(function(){
    loadUsers();
    console.log("listaUsers -> ", listaUsers);
    removeDangerAlerts();


    $( "#loginForm" ).submit(async function( event ) {
        if ( validateUser() ) {
            removeDangerAlerts();
            //alert("vou entrar como -> " + userLogged.name);


            window.localStorage.setItem("userLogged", JSON.stringify(userLogged));
            alert("guardar " + userLogged.espacos[0].name);
            window.localStorage.setItem("espacoAtual", userLogged.espacos[0].name);

            //document.sessionStorage["form-login-data"] =  $('this').serialize();
            //document.location.href = 'board_geral.html';

            //alert("form data " + JSON.stringify(userLogged));
            return ;
        }

        event.preventDefault();
    });



});