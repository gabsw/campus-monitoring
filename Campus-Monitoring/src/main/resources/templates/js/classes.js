class Espaco {

    constructor(name, max_temp_limit, min_temp_limit, max_hum_limit, min_hum_limit, max_co2_limit) {
        this._name = name;
        this._max_temp_limit = max_temp_limit;
        this._min_temp_limit = min_temp_limit;
        this._max_hum_limit = max_hum_limit;
        this._min_hum_limit = min_hum_limit;
        this._max_co2_limit = max_co2_limit;
    }

    equals(other){
        return this.name() === other.name();
    }

    get name() {
        return this._name;
    }

    get max_temp_limit() {
        return this._max_temp_limit;
    }

    get min_temp_limit() {
        return this._min_temp_limit;
    }

    get max_hum_limit() {
        return this._max_hum_limit;
    }

    get min_hum_limit() {
        return this._min_hum_limit;
    }

    get max_co2_limit() {
        return this._max_co2_limit;
    }
}


class Utilizador {
    constructor(username, name, email, admin) {
        this._espacos = [];
        this._username = username;
        this._name = name;
        this._email = email;
        this._admin = admin;
        this._password = "password";
    }


    get password() {
        return this._password;
    }

    equals(other){
        return this.username() === other.username();
    }

    getEspacoPorNome(nome){

        for (var i = 0; i < this.espacos.length; i++){
            var e = this.espacos[i];
            if(e.name === nome){
                return e;
            }
        }

        return ;
    }

    set espacos(listaEspacos) {
        this._espacos = listaEspacos;
    }

    get username() {
        return this._username;
    }

    get name() {
        return this._name;
    }

    get email() {
        return this._email;
    }

    get admin() {
        return this._admin;
    }

    get espacos() {
        return this._espacos;
    }

}