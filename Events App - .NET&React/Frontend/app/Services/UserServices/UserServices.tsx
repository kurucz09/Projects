import * as React from 'react';
import {UserProfile} from "../../components/userprofile_page/UserProfile";
import Register from '../../components/login_page/Register/Register';
import {RegisterObject} from '../../components/login_page/Objects/RegisterObject';
import * as jwt_decode from "jwt-decode";
import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import {Validator} from '../Validation/Validator';
import {showAlert, hideAlert} from '../../Store/Actions';

export class UserServices {
    static API = "http://localhost:60158/api/";

    static tryToChangeAddress(userData : any, email : string) {
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
        var token = ""
        if(localStorage.getItem("auth")){
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if(localStorage.getItem("fbauth")){
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(this.API + "user/update", {
            method: "POST",
            headers: myheader,
            body: JSON.stringify({
                "Id": userData.props.userData.Id,
                "Name": userData.props.userData.Name,
                "Email": email,
                "Password": userData.props.userData.Password,
                "Username": userData.props.userData.Username,
                "RoleId": userData.props.userData.RoleId,
                "DateBirth": userData.props.userData.DateBirth,
                "PictureURL": userData.props.userData.PictureURL
            })
        });

        let response = fetch(request)

    }

    static tryToChangePassword(passChange : any, loginObject : LoginObject) {
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
        var token = ""
        if(localStorage.getItem("auth")){
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if(localStorage.getItem("fbauth")){
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(this.API + "login/login", {
            method: "POST",
            headers: myheader,
            body: JSON.stringify({"username": loginObject.username.value, "password": loginObject.password.value})
        });

        let response = fetch(request).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
            .then(function (data) {
                if(data.errorCode === 10){
                     passChange
                    .props
                    .dispatch(showAlert(true, "Wrong password. Enter your valid password", "red-alert"));
                } else {
                    passChange
                        .props
                        .dispatch(hideAlert());
                    if (passChange.state.confirmPassword != passChange.state.newPassword) {
                        passChange
                            .props
                            .dispatch(showAlert(true, "Password do not match", "red-alert"));
                    } else if (!Validator.validatePasswordLength(passChange.state.newPassword)) {
                        passChange
                            .props
                            .dispatch(showAlert(true, "The new password is too short", "red-alert"));
                    } else {
                        var user = passChange.props.userData;
                        var requestchange = new Request("http://localhost:60158/api/user/update", {
                            method: "POST",
                            headers: myheader,
                            body: JSON.stringify({
                                "Id": user.Id,
                                "Name": user.Name,
                                "Email": user.Email,
                                "Password": passChange.state.newPassword,
                                "Username": user.Username,
                                "DateBirth": user.DateBirth,
                                "RoleId": user.RoleId,
                                "PictureURL": user.PictureURL
                            })
                        });
                        let response = fetch(requestchange).then(function (response) {
                            if (response.ok) {
                                return response;
                            }
                            throw new Error('mesajul de aici');
                        })
                            .then(function (data) {
                                passChange
                                    .props
                                    .dispatch(hideAlert());
                                passChange
                                    .props
                                    .dispatch(showAlert(true, "Password changed. Please log again with your new password", "green-alert"));
                                passChange
                                    .props
                                    .history
                                    .push('/logout');
                            })
                            .catch(function (error) {
                                passChange
                                    .props
                                    .dispatch(showAlert(true, "The password couldn't be changed. We are sorry", "red-alert"));
                            })
                        }
                }
            })
            .catch(function (error) {
                passChange
                    .props
                    .dispatch(showAlert(true, "Wrong password. Enter your valid password", "red-alert"));
            })
    }

    static tryToChangePicture(profilepic : any, link : string) {
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
         var token = ""
        if(localStorage.getItem("auth")){
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if(localStorage.getItem("fbauth")){
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        profilepic.props.userData.pictureUrl = link;
        var request = new Request(this.API + "user/update", {
            method: "POST",
            headers: myheader,
            body: JSON.stringify({
                "Id": profilepic.props.userData.Id,
                "Name": profilepic.props.userData.Name,
                "Email": profilepic.props.userData.Email,
                "Username": profilepic.props.userData.Username,
                "DateBirth": profilepic.props.userData.DateBirth,
                "RoleId": profilepic.props.userData.RoleId,
                "PictureURL": link
            })
        });

        let response = fetch(request).then(function (response) {
            if (response.ok) {

                window
                    .location
                    .reload()
                    profilepic.props.dispatch(showAlert(true, "Profile picture changed", "green-alert"))
                return;
            }
            throw new Error("Could not change the picture");
        })
        .catch(function (error) {
             profilepic.props.dispatch(showAlert(true, "Could not change the profile picture.", "red-alert"))
        })

    }

    static getUserByUsername(userProfile : UserProfile) {
        var token = localStorage.getItem('auth')
        var obj = {
            iss: "",
            sub: "",
            iat: "",
            exp: "",
            role: ""
        }
        if (token != null) {
            obj = jwt_decode(token)
        }

        const API = "http://localhost:60158/api/";
        var myheader = new Headers(
            {'Access-Control-Allow-Origin': '*', 
            'Content-Type': "application/json", 
            'content-Type': "application/json"});
        var tokenHead = ""
        if(localStorage.getItem("auth")){
            tokenHead = String(localStorage.getItem("auth"));
            myheader.set("auth_token", tokenHead);
        } else if(localStorage.getItem("fbauth")){
            tokenHead = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", tokenHead);
        }
        var request = new Request(API + "user/findByUsername/" + obj.sub, {
            method: "GET",
            headers: myheader
        });

        fetch(request).then(function (response) {
            console.log(response);
            return (response.json());
        })
            .then(function (data) {
                userProfile.setState({userDatas: data})
            })
    }

    static insertUser(registerPage : any, regObj : RegisterObject) {
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
         var token = ""
        if(localStorage.getItem("auth")){
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if(localStorage.getItem("fbauth")){
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(this.API + "user/insert", {
            method: "POST",
            headers: myheader,
            body: JSON.stringify({
                "Name": regObj.name.value,
                "Username": regObj.username.value,
                "Password": regObj.password.value,
                "Email": regObj.email.value,
                "DateBirth": regObj.dateBirth.value,
                "RoleId": "3",
                "PictureURL": regObj.imageURL.value
            })
        });
        let response = fetch(request).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error();
        })
            .then(function (data) {
                registerPage.registerSuccess();
            })
            .catch(function (error) {
                registerPage
                    .props
                    .dispatch(showAlert(true, "Username already exists", "red-alert"));
            })
    }
}