export function showAlert(show:boolean, message:string, type:string) {
    return {
        type: "showAlert",
        payload: {
            show: show,
            message: message,
            type: type
        }
    };
}

export function hideAlert() {
    return {
        type: "showAlert",
        payload: {
            show: false,
            message: "",
            type: ""
        }
    };
}

