export class RegisterObject {
    public username : {value: string, isValid: boolean};
    public password : {value: string, isValid: boolean};
    public passwordRepeat : {value: string, isValid: boolean};
    public email : {value: string, isValid: boolean};
    public name : {value: string, isValid: boolean};
    public dateBirth : {value: string, isValid: boolean};
    public imageURL : {value: string, isValid: boolean};
    public verified : boolean;
    public passwordMatch : boolean;
}