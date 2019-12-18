var hostURL = 'http://deti-engsoft-12.ua.pt';
//var hostURL = 'http://localhost:8080';
var hostURLLocal = 'http://localhost:8080';

/*var userAtual = new Utilizador("pedro_bastos",
    "Pedro Ferreira Bastos",
    "campus.monitoring.ies+pedrobastos@gmail.com",
    true); // classe

 */

//alert("globals recebeu userLogger -> " + userLogged._name);

var userLogged = JSON.parse(window.localStorage.getItem("userLogged"));


var userAtual = setupUserLogged();
var espacoAtual = setupEspacoAtual();


//--------------- FUNCOES


function setupUserLogged(){
    // (username, name, email, admin)
    var u = new Utilizador(userLogged._username, userLogged._name, userLogged._email, userLogged._admin);

    for (var i =0; i<userLogged._espacos.length; i++){
        var e = userLogged._espacos[i];

        // (name, max_temp_limit, min_temp_limit, max_hum_limit, min_hum_limit, max_co2_limit)
        var esp = new Espaco(e._name, e._max_temp_limit, e._min_temp_limit, e._max_hum_limit, e._min_hum_limit, e._max_co2_limit);

        u.espacos.push(esp);

    }

    return u;
}

function setupEspacoAtual() {

    var espacoNome = window.localStorage.getItem("espacoAtual");
    return userAtual.getEspacoPorNome(espacoNome);

}


function showEspacosUserMenu() {

    var espacosDropdown = "";

    for (var i = 0; i < userAtual.espacos.length; i++){

        var espaco = userAtual.espacos[i];

        var linhaEspaco = `<li onclick="changeEspacoAtual(this.textContent)" value="${espaco.name}"><a id="${espaco.name}" class="" href="#">
						<span class="fa fa-dashboard">&nbsp;</span> ${espaco.name}
					</a></li>`

        espacosDropdown = espacosDropdown.concat(linhaEspaco);

    }


    $("#espacosDropdown").html(espacosDropdown);

}


function showUserMenu(){
    showEspacosUserMenu();

    var linhaHistorico = document.getElementById("paginaHistorico");
    var linhaReports = document.getElementById("paginaReports");
    var linhaReviews = document.getElementById("paginaReviews");
    linhaHistorico.style.display = "block";
    linhaReports.style.display = "block";
    linhaReviews.style.display = "block";

    if(!userAtual.admin){
        linhaHistorico.style.display = "none";
        linhaReports.style.display = "none";
        linhaReviews.children[0].href = "reviews_regular.html"; // mudar para a página de comentários do trabalhador
    }
}


function showUserProfileNamePhoto(){
    var nomes = userAtual.name.split(" ");
    var numeroNomes = nomes.length;
    var nomeEapelido = nomes[0] + " " + nomes[numeroNomes-1];

    $("#nomeUser").text(nomeEapelido);
}


function setHref(valor) {

    var listaLi = $("#espacosDropdown li");

    for (var i=0; i<listaLi.length; i++){
        var li = listaLi[i];

        var a = li.children[0]; // buscar a tag <a>

        a.href = valor;

    }

}



function resetLiActive() {

    var listaLi = $("#espacosDropdown li");

    for (var i=0; i<listaLi.length; i++){
        var li = listaLi[i];

        var a = li.children[0]; // buscar a tag <a>

        a.className = "";

    }

}


function checkActive(){

    var algumTemActive = false;

    var listaLi = $("#espacosDropdown li");

    for (var i=0; i<listaLi.length; i++){
        var li = listaLi[i];

        var a = li.children[0]; // buscar a tag <a>


        if(a.className === "active"){
            algumTemActive = true;
            break;
        }

    }

    return algumTemActive;

}


function changeEspacoAtual(liValue){

    liValue = liValue.trim();

    window.localStorage.setItem("espacoAtual", liValue);

    espacoAtual = userAtual.getEspacoPorNome(liValue);

    var listaLi = $("#espacosDropdown li");

    for (var i=0; i<listaLi.length; i++){
        var li = listaLi[i];

        var a = li.children[0]; // buscar a tag <a>


        if(a.text.trim() === liValue){
            a.className = "active";
        }

        if (a.className === "active" && a.text.trim() !== liValue){
            a.className = "";
        }

    }


    if(!checkActive()){
        var li = $("#espacosDropdown li:first");

        var a = li.children[0]; // buscar a tag <a>

        a.className = "active";
    }

    if(checkActive()){ // atualizar quando estamos na própria página
        $("#breadCrumbEspaco")[0].innerText = espacoAtual.name;
        $("#headerEspaco")[0].textContent = espacoAtual.name;
    }


}