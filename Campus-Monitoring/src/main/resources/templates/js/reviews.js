var espacoSelected = null;



function getReviewsByLocal(localname){
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
								<img src="../picturesres/avatar_60.png" alt="User Avatar" class="img-circle" />
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

