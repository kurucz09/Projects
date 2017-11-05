import 'whatwg-fetch';

import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import {EventsPics} from "../../components/main_page/Events/EventsPics";
   
export class PicServicies {

    static API = "http://localhost:60158/api/";

    static getAllPictures(picEvent : EventsPics, idEvent : number) {
        var ev : {
            Id : any,
            PictureURL : string
        };
        const API = "http://localhost:60158/api/";
        var myheader = new Headers({'Access-Control-Allow-Origin': '*', 'Content-Type': "application/json", 'content-Type': "application/json"});
        var token = ""
        if(localStorage.getItem("auth")){
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if(localStorage.getItem("fbauth")){
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(API + "picture/FindByEventId/" + idEvent, {
            method: "GET",
            headers: myheader
        }); 

        return fetch(request).then((response) => (response.json()))
            .then(function (data) {
                ev = data;
            })
            .then(() => picEvent.setState({picEvent: ev}))

    }

    static addPicture(PictureURL: string, EventId: number)
    {
       
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


        var request = new Request(this.API + "picture/add", {
            method: "POST",
            headers: myheader,
            body: JSON.stringify({
                "EventId": EventId,
                "PictureURL": PictureURL
            })
        });

        return fetch(request).then(function(response){
           
        })
    }

}
