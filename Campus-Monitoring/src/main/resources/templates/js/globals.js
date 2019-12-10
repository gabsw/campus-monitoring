// valores de teste
// TODO: ir buscar dados ao utilizador do login

/*var userAtual = new Utilizador("pedro_bastos",
    "Pedro Ferreira Bastos",
    "campus.monitoring.ies+pedrobastos@gmail.com",
    true); // classe

 */

//alert("globals recebeu userLogger -> " + userLogged._name);

var userLogged = JSON.parse(window.localStorage.getItem("userLogged"));


console.log("RECEBI " + window.localStorage.getItem("userLogged"));

var userAtual = setupUserLogged();
var espacoAtual = setupEspacoAtual();


console.log("ESPACO ATUAL DO LS " + window.localStorage.getItem("espacoAtual"));
//alert("userAtual globals -> " + userAtual._name);

//var refSantiago = new Espaco('Refeitório de Santiago', 25.00, 15.00, 60, 40, 1000);
//var refCrasto = new Espaco('Refeitório do Castro', 26.00, 16.00, 65, 45, 1000);
//var cafetariaEsan = new Espaco('Cafetaria da ESAN', 24.00, 14.00, 70, 35, 1000);
//var ieeta = new Espaco('IEETA', 25.00, 20.00, 60, 40, 1000);
// var espacos = [refSantiago, refCrasto, cafetariaEsan];



//userAtual.espacos = [refSantiago, refCrasto, cafetariaEsan];


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
    console.log("e -> " + espacoNome);
    return userAtual.getEspacoPorNome(espacoNome);

}


function showEspacosUserMenu() {

    var espacosDropdown = "";

    console.log("userAtual name -> ", userAtual.name);
    console.log("userAtual espacos -> ", userAtual.espacos);

    console.log("espacoAtual name -> ", espacoAtual.name);


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
    linhaHistorico.style.display = "block";

    if(!userAtual.admin){
        linhaHistorico.style.display = "none";
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

function setFirstActive() {

    var firstLi = $("#espacosDropdown li:first")[0];

    var a = firstLi.children[0]; // buscar a tag <a>

    a.className = "active";

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
        console.log("algumtemActive -> ", algumTemActive);
        // TODO: recarregar a página
        var li = $("#espacosDropdown li:first");

        var a = li.children[0]; // buscar a tag <a>

        a.className = "active";
    }

    // TODO: mudar o espaco atual (nome -> classe)

}