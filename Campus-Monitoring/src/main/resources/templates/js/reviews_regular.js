var espacoSelected = null;

var verTodosOsComentarios = true;


function formatarDataPost(date) {
    // arg: objeto da data
    // formato objetivo: 2019-12-11T12:20:30

    var date_splitted = date.toString().split(" ");

    var timestamp = date_splitted[4];
    var dia = date_splitted[2];
    var ano  = date_splitted[3];
    var mes = date.getMonth() + 1;

    return ano + "-" + mes + "-" + dia + "T" + timestamp;
}


function clearComment() {
    $("#selectRating").prop("selectedIndex", 0);
    $("#btn-input")[0].value = "";
}


function submeterComentario(){

    var username = userAtual.username;
    var rating = $( "#selectRating option:selected" ).text();
    var localName = espacoSelected;
    var content = $("#btn-input")[0].value;

    var data = formatarDataPost(new Date()); // data do post do comentario

    if (username.length > 30 || localName > 100 || content > 100){
        alert("Os comentários estão limitados a 100 caracteres.");
    }
    else{
        var espacoEncoded = encodeURIComponent(espacoSelected);

        var uri = hostURL + "/local/" + espacoEncoded + "/reviews/";

        var dadosJSON = {
            "localName" : localName.toString(),
            "username" : username.toString(),
            "dateTime" : data.toString(),
            "rating" : parseInt(rating),
            "content" : content.toString()
        };

        $.ajax({
            url: uri,
            async: false,
            beforeSend: function (xhr) {
                xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
            },
            method: "POST",
            dataType: "json",
            processData : false,
            contentType : 'application/json',
            data: JSON.stringify(dadosJSON)
        });

        // recarregar comentários
        if(verTodosOsComentarios){
            getReviewsByLocal(espacoSelected);
        }
        else{
            getUserReviewsByLocal(espacoSelected);
        }

        clearComment();
    }

}



function eliminarComentario(commentID) {

    var uri = hostURL + "/review/"+parseInt(commentID);

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        },
        type: 'DELETE',
        success: function(result) {
            getUserReviewsByLocal(espacoSelected); // recarregar comentários
        }
    });

    getUserReviewsByLocal(espacoSelected); // recarregar comentários

    return true;
}


function getUserReviewsByLocal(localname) {
    verTodosOsComentarios = false;

    var userAtualusername = userAtual.username;
    var usernameEncoded = encodeURIComponent(userAtualusername);

    var uri = hostURL + "/review/username/"+usernameEncoded;

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        },
        error: function()
        {
            var alerta = `<div class="alert alert-danger" role="alert" data-dismiss="alert">
                                Não foi possível obter os seus comentários para ${localname}.
                          </div>`;

            $("#listaComentarios").html(alerta);
        }

    }).then(function(data) {

        var reviewsHTML = "";
        var allReviews = data.content;

        if (allReviews.length === 0){
            var alerta = `<div class="alert alert-info" role="alert" data-dismiss="alert">
                                Ainda não tem comentários para ${localname}.
                          </div>`;

            $("#listaComentarios").html(alerta);

            return;
        }


        for (var i = allReviews.length-1; i >= 0; i--){
            var doc = allReviews[i];
            var local = doc.localName;

            if(local !== localname){
                continue;
            }

            var commentID = doc.id;
            var username = doc.username;

            var data = doc.dateTime.split("T")[0];
            var hora = doc.dateTime.split("T")[1].split(":")[0];
            var minutos = doc.dateTime.split("T")[1].split(":")[1];

            var rating = doc.rating;
            var comentario = doc.content;

            var estrelas = "";
            var iconEstrela = "<i class=\"fa fa-star\" style=\"color: #f0ad4e\"></i>";

            for (var r = 1; r<=rating; r++){
                estrelas = estrelas.concat(iconEstrela);
            }

            var linha = `<li class="left clearfix"><span class="chat-img pull-left">
								<img src="pictures/avatar_60.png" alt="User Avatar" class="img-circle" />
								</span>
                            <div class="chat-body clearfix">     
                                <div class="pull-left">
                                    <div class="header"><strong class="primary-font">${username}</strong> <small class="text-muted">${data} | ${hora}:${minutos}</small></div>
                                ${estrelas}
                                <p>${comentario}</p> 
                                </div>    
                                <div class="pull-right">
                                    <button id = ${commentID} class="btn btn-danger btn-md" onclick="eliminarComentario(this.id)"><em class="fa fa-trash"></em></button>
                                </div>                           
                            </div>
                         
                        </li>`;

            reviewsHTML = reviewsHTML.concat(linha);
        }

        $("#listaComentarios").html(reviewsHTML);
    });

}


function getReviewsByLocal(localname){
    verTodosOsComentarios = true;

    var titulo = localname;
    $("#nomeEspacoTabela").text("Comentários sobre " + titulo);

    var espacoEncoded = encodeURIComponent(localname);

    var uri = hostURL + "/local/"+espacoEncoded+"/reviews";

    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        },
        error: function()
        {
            var alerta = `<div class="alert alert-danger" role="alert" data-dismiss="alert">
                                Não foi possível obter comentários para ${localname}.
                          </div>`;

            $("#listaComentarios").html(alerta);

            return;
        }

    }).then(function(data) {
        var reviewsHTML = "";

        var allReviews = data.content;

        if (allReviews.length === 0){
            var alerta = `<div class="alert alert-info" role="alert" data-dismiss="alert">
                                Não existem comentários para ${localname}.
                          </div>`;

            $("#listaComentarios").html(alerta);

            return;
        }


        for (var i = allReviews.length-1; i >= 0; i--){

            var doc = allReviews[i];

            var username = doc.username;

            var data = doc.dateTime.split("T")[0];
            var hora = doc.dateTime.split("T")[1].split(":")[0];
            var minutos = doc.dateTime.split("T")[1].split(":")[1];

            var rating = doc.rating;
            var comentario = doc.content;

            var estrelas = "";
            var iconEstrela = "<i class=\"fa fa-star\" style=\"color: #f0ad4e\"></i>";

            for (var r = 1; r<=rating; r++){
                estrelas = estrelas.concat(iconEstrela);
            }

            var linha = `<li class="left clearfix"><span class="chat-img pull-left">
								<img src="pictures/avatar_60.png" alt="User Avatar" class="img-circle" />
								</span>
                            <div class="chat-body clearfix">     
                                <div class="header"><strong class="primary-font">${username}</strong> <small class="text-muted">${data} | ${hora}:${minutos}</small></div>
                                ${estrelas}
                                <p>${comentario}</p>
                            </div>
                        </li>`;

            reviewsHTML = reviewsHTML.concat(linha);
        }

        $("#listaComentarios").html(reviewsHTML);
    });
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
    $("#nomeEspacoTabela").text("Comentários sobre " + espacoSelected);

    getReviewsByLocal(espacoSelected);


    $("#selectEspaco").change( function(){
        espacoSelected = $(this).children("option:selected").text();
        $('#nomeEspacoTabela').text(espacoSelected);
        getReviewsByLocal(espacoSelected);
    });








});

