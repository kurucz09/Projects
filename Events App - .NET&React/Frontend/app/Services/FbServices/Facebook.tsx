import 'whatwg-fetch';

import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import Login from '../../components/login_page/Login/Login'
import { RegisterObject } from '../../components/login_page/Objects/RegisterObject';
import Register from '../../components/login_page/Register/Register';
import { Item } from '../../components/main_page/MnuBar/Item';
import { UserProfile } from '../../components/userprofile_page/UserProfile';
import { showAlert } from '../../Store/Actions';

export class FacebookServices {

    static API = "http://localhost:60158/api/";
     
    static GetUsername(app : any, token: any) {
        var myheader = new Headers();
        var userdata = {id: "", name: ""}
        var dat : any;
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
        var tokenHead = ""
        if(localStorage.getItem("auth")){
            tokenHead = String(localStorage.getItem("auth"));
            myheader.set("auth_token", tokenHead);
        } else if(localStorage.getItem("fbauth")){
            tokenHead = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", tokenHead);
        }
        var request = new Request(this.API + "fb/GetIdAndUserName/"+token, {
            method: "GET",
            headers: myheader
        });

        let response = fetch(request).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
            .then(function (data) {
                app.setState({userLogged: data.Name});
            })
            .catch(function (error) {
                app.props.dispatch(showAlert(true,"A problem has occurred.","red-alert"));
            })    
    }   

    static getUserInfo(userProfile : UserProfile, token : any){
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
        var tokenHead = ""
        if(localStorage.getItem("auth")){
            tokenHead = String(localStorage.getItem("auth"));
            myheader.set("auth_token", tokenHead);
        } else if(localStorage.getItem("fbauth")){
            tokenHead = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", tokenHead);
        }
        var data = {idU: "", idR : "", nameU: "", email: "", pass: "", username: "", dateB: "", pictureUrl: ""};

        var request = new Request(this.API + "fb/GetUserInfo/"+token, {
            method: "GET",
            headers: myheader
        });

        let response = fetch(request).then(function (response) {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Network response was not ok.');
        })
            .then(function (data) {
                var userFbDataShallowCopy:{id: any, name: any, email:any, picture: {data : {url : any}}, age_range: {min:any}, gender: any }
                userFbDataShallowCopy = data;
                var userFbDataMap = {
                    id: userFbDataShallowCopy.id, 
                    name: userFbDataShallowCopy.name, 
                    email: userFbDataShallowCopy.email, 
                    picture: userFbDataShallowCopy.picture.data.url, 
                    age: userFbDataShallowCopy.age_range.min, 
                    gender: userFbDataShallowCopy.gender 
                }
                userProfile.setState({userFbData: userFbDataMap});
            })
            .catch(function (error) {
                
            })    
    }
}
