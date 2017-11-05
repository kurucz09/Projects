import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import {RegisterObject} from '../../components/login_page/Objects/RegisterObject';

export class Validator {

    static validateEmail(s : string) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(s);
    }

    static validateUsername(s : string) {
        if (s.length < 5) {
            return false;
        }
        var re = /^^([a-zA-Z0-9]|[_]|[-]|[.])+$/;
        return re.test(s);
    }

    static validatePasswordLength(s : string) {
        if (s.length < 6) {
            return false;
        }
        return true;
    }

    static validateName(s: string){
        var re = /^([A-Z][a-zA-Z]+|([\s]|[-]))+$/;
        return re.test(s);
    }

    static validateDate(s : string){
        var re = /^(0[1-9]|1[0-9]|2[0-9]|3[0-1])[\/](0[0-9]|1[0-2])[\/](((19[2-9])[0-9])|(20([0]|[1])[0-9]))$/;
        return re.test(s);
    }

    static validatePictureUrl(s: string){
         var re = /(http(s?):)|([/|.|\w|\s])*\.(?:jpg|gif|png)$/;
         return re.test(s);
    }

    static validateLogin(lg : LoginObject) {
        var temp = lg;
        if (!this.validateUsername(temp.username.value)) {
            temp.username.isValid = false;
            temp.verified = false;
        }
        
        if (!this.validatePasswordLength(temp.password.value)) {
            temp.password.isValid = false;
            temp.verified = false;
        }

        return temp;
    }

    static validateRegister(reg : RegisterObject ){
        var temp = reg;
        if(!this.validateEmail(temp.email.value)){
            temp.email.isValid = false;
            temp.verified = false;
        }
        if(!this.validateUsername(temp.username.value)){
            temp.username.isValid = false;
            temp.verified = false;
        }
        if(!this.validatePasswordLength(temp.password.value)){
            temp.password.isValid = false;
            temp.verified = false;
        }
        if(!this.validatePasswordLength(temp.passwordRepeat.value)){
            temp.passwordRepeat.isValid = false;
            temp.verified = false;
        }
        if(temp.password.value !== temp.passwordRepeat.value){
            temp.passwordMatch = false;
        }
        if(!this.validateName(temp.name.value)){
            temp.name.isValid = false;
            temp.verified = false;
        }
        if(!this.validateDate(temp.dateBirth.value)){
            temp.dateBirth.isValid = false;
            temp.verified = false;
        }
        if(temp.imageURL.value === ""){
            temp.imageURL.isValid = false;
            temp.verified = false;
        }
        return temp;
    }

}
