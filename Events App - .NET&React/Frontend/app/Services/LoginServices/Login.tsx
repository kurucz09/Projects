import 'whatwg-fetch';

import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import Login from '../../components/login_page/Login/Login'
import { RegisterObject } from '../../components/login_page/Objects/RegisterObject';
import * as jwt_decode from "jwt-decode";
import { FacebookServices } from '../FbServices/Facebook';
import { showAlert } from '../../Store/Actions';

export class LoginServices {

    static API = "http://localhost:60158/api/";

    static tryToLog(login : any,lg : LoginObject) {
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');

        var request = new Request(this.API + "login/login", {
            method: "POST",
            headers: myheader,
            body: JSON.stringify({"username": lg.username.value, "password": lg.password.value})
        });

        let response = fetch(request).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
            .then(function (data) {
                if (data.errorCode === 0) {
                    localStorage.setItem("auth", data.message);
                    login.setState({isLoged: true});
                    login.loginSucceeded();

                }
                else {
                    login.props.dispatch(showAlert(true, data.message, "red-alert"));
                }
            })
            .catch(function (error) {
                login.props.dispatch(showAlert(true,"Please log in with a valid account","red-alert"));
            })    
    }   

    static getUserLogged(){
        var decodedType = {
                sub: ""
            }
            var token : string;
            var value : any;
            var username :string

            if (localStorage.getItem("auth")) {
                value = localStorage.getItem("auth");
                token = value;
                decodedType = jwt_decode(token);
                return decodedType.sub;
            }
            return "";
    }
}
