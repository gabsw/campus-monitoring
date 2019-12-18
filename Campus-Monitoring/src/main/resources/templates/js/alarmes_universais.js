

var alarmParameterClasses = {   "temperatura":"fa fa-thermometer-three-quarters alerta-temperatura",
                                "humidade":"fa fa-tint alerta-humidade",
                                "co2":"fa fa-cloud alerta-co2"};

var violationTypeText = {"max": "acima", "min": "abaixo"};
var parametersUnit =  {"temperatura":"ºC", "humidade":"%", "co2":"ppm"};



function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}




async function getAllAlarmsByLocal(localName){

    var espacoEncoded = encodeURIComponent(localName);

    var uri = hostURL + "/local/"+espacoEncoded+"/alarms/all";


    $.ajax({
        url: uri,
        async: false,
        beforeSend: function (xhr) {
            xhr.setRequestHeader ("Authorization", "Basic " + btoa(userAtual.username + ":" + userAtual.password));
        },
        error: function()
        {

            var alerta = `<div class="alert alert-danger" role="alert" data-dismiss="alert">
                                Não foi possível obter alertas para ${espacoAtual.name}.
                          </div>`;

            $("#listaAlarmes").html(alerta);
        }

    }).then(function(data) {

        var listaAlarmesHTML = "";

        var alarmes = data.content;


        if (alarmes.length === 0){

            var alerta = `<div class="alert alert-success" role="alert" data-dismiss="alert">
                                Não foram disparados alertas para ${espacoAtual.name}.
                          </div>`;

            $("#listaAlarmes").html(alerta);

            return;
        }


        for (var i = alarmes.length-1; i >= 0; i--){

            var doc = alarmes[i];

            var parameter = doc.violationParameter;
            var type = doc.violationType;
            var value = doc.violationValue;

            var classIcon = alarmParameterClasses[parameter];
            var typeText = violationTypeText[type];
            var unit = parametersUnit[parameter];

            var nomeParametroUpper = parameter.charAt(0).toUpperCase() + parameter.slice(1);

            var data = doc.startDate.split("T")[0];
            var hora = doc.startDate.split("T")[1].split(":")[0];
            var minutos = doc.startDate.split("T")[1].split(":")[1];

            var alarmeTimeStampText = "";
            var hoje = new Date();
            var dataAlarme = new Date(data);

            if(dataAlarme < hoje){
                alarmeTimeStampText = data + " | " + hora + "h" + minutos;
            }
            else {
                alarmeTimeStampText = hora + "h" + minutos;
            }


            var linha = `<li class="left clearfix"><span class="pull-left" style="text-align: center">
								<i class="${classIcon}"></i>
								</span>
									<div class="chat-body clearfix">
										<div class="header"><strong class="primary-font">${alarmeTimeStampText}</strong></div>
										<p>${nomeParametroUpper} ${typeText} de ${value} ${unit}</p>
									</div>
								</li>`

            listaAlarmesHTML = listaAlarmesHTML.concat(linha);

        }

        $("#listaAlarmes").html(listaAlarmesHTML);


    });


    await sleep(61000);

    if(espacoAtual.name === localName) getAllAlarmsByLocal(localName);
    else return ;

}




$(document).ready(function(){

    getAllAlarmsByLocal(espacoAtual.name);
    $(".alert-success").alert("close");


    $("#espacosDropdown").click(function () {
        $(".alert-success").alert("close");
        getAllAlarmsByLocal(espacoAtual.name);
    })

});