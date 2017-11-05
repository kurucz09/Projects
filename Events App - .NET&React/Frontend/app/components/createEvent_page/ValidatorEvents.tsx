export class ValidatorEvents {

    static isNotNull(data : any){
        if(data.value === "")
            data.isValid = false;
        return data;
    }

    static isCategoryValid(data : any){
        if(data.value == 0)
        data.isValid = false;
        return data;
    }

    static isDescriptionValid(data : any) {
        if (data.value.length < 50) 
            data.isValid = false;
        return data;
    }

    static isAgeValid(data : any) {
        var reg = /^\d+$/;
        data.isValid = reg.test(data.value);
        return data;
    }

    static isNumberOfTicketsValid(data : any) {
        if(data.value == 0)
            data.isValid = false;
        else{var reg = /^[0-9]+$/;
        data.isValid = reg.test(data.value);
        }return data;
    }

    static isPictureURLValid(data : any) {
        var reg = /(http(s?):)|([/|.|\w|\s])*\.(?:jpg|gif|png)$/
        data.isValid = reg.test(data.value);
        return data;
    }
}