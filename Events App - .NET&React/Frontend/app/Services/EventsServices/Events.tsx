import 'whatwg-fetch';

import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import {showAlert} from '../../Store/Actions';
import {PicServicies} from "./Pics";
import * as jwt_decode from "jwt-decode";

export class EventServices {

    static API = "http://localhost:60158/api/";

    static getAllById(events : any, id : number) {

        var eventsList : {
            Id : number,
            Descriptioniption : string,
            Name : string,
            Place : string,
            Hour : any,
            BeginDate : any,
            EndDate : any,
            MinDateBirth : any,
            NumberOfTickets : number,
            Status : string,
            UserId : number,
            CategoryId : number
        }[];
        const API = "http://localhost:60158/api/";
        var myheader = new Headers({'Access-Control-Allow-Origin': '*', 'Content-Type': "application/json", 'content-Type': "application/json"});
        var token = ""
        if (localStorage.getItem("auth")) {
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if (localStorage.getItem("fbauth")) {
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(API + "event/GetEventByCategory/" + id, {
            method: "GET",
            headers: myheader
        });

        return fetch(request).then(function (response) {
            if (response.ok) {
                return response.json()
            }
            throw new Error("001")
        })
            .then(function (data) {
                if (data.errorCode === 25) {
                    events
                        .props
                        .history
                        .push("/");
                    events
                        .props
                        .dispatch(showAlert(true, "No events found in this category", "red-alert"));
                } else {
                    events.setState({events: data});
                }
            })
            .catch(function (error) {
                if (error == "001") {
                    events
                        .props
                        .history
                        .push("/");
                    events
                        .props
                        .dispatch(showAlert(true, "Oops! Something has happened. We are sorry :(", "red-alert"));
                }
            })
    }
    static getAll(events : any) {

        var eventsList : {
            Id : number,
            Descriptioniption : string,
            Name : string,
            Place : string,
            Hour : any,
            BeginDate : any,
            EndDate : any,
            MinDateBirth : any,
            NumberOfTickets : number,
            Status : string,
            UserId : number,
            CategoryId : number
        }[];
        const API = "http://localhost:60158/api/";
        var myheader = new Headers({'Access-Control-Allow-Origin': '*', 'Content-Type': "application/json", 'content-Type': "application/json"});
        var token = ""
        if (localStorage.getItem("auth")) {
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if (localStorage.getItem("fbauth")) {
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(API + "event/getall/", {
            method: "GET",
            headers: myheader
        });

        return fetch(request).then((response) => (response.json()))
            .then(function (data) {
                events.setState({events: data})
            })

    }
    static getAllEvId(evenimente : any, id : number) {

        var event : {
            Id : number,
            Descriptioniption : string,
            Name : string,
            Place : string,
            Hour : any,
            BeginDate : any,
            EndDate : any,
            MinDateBirth : any,
            NumberOfTickets : number,
            Status : string,
            UserId : number,
            CategoryId : number
        };
        const API = "http://localhost:60158/api/";
        var myheader = new Headers({'Access-Control-Allow-Origin': '*', 'Content-Type': "application/json", 'content-Type': "application/json"});
        var token = ""
        if (localStorage.getItem("auth")) {
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if (localStorage.getItem("fbauth")) {
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }
        var request = new Request(API + "event/FindById/" + id, {
            method: "GET",
            headers: myheader
        });

        return fetch(request).then((response) => (response.json()))
            .then(function (data) {

                evenimente.setState({even: data})

                evenimente.setState({mounted: true});

            })

    }

    static createAnEvent(crEvent : any) {
        var myheader = new Headers();
        myheader.set('content-type', 'application/json');
        myheader.set('Access-Control-Allow-Origin', '*')
        var token = "";
        var userId = "";

        if (localStorage.getItem("auth")) {
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
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
            var request = new Request(this.API + "user/findByUsername/" + obj.sub, {
                method: "GET",
                headers: myheader
            });

            fetch(request).then(function (response) {
                return (response.json());
            })
                .then(function (data) {
                    userId = data.Id;
                    var request = new Request("http://localhost:60158/api/event/add", {
                        method: "POST",
                        headers: myheader,
                        body: JSON.stringify({
                            "Name": crEvent.state.Name.value,
                            "Description": crEvent.state.Description.value,
                            "Place": crEvent.state.Place.value,
                            "Hour": crEvent.state.Hour.value,
                            "EndDate": crEvent.state.EndDate.value,
                            "BeginDate": crEvent.state.BeginDate.value,
                            "MinDateBirth": crEvent.state.MinDateBirth.value,
                            "NumberOfTickets": crEvent.state.NumberOfTickets.value,
                            "Status": crEvent.state.Status.value,
                            "UserId": userId,
                            "CategoryId": crEvent.state.CategoryId.value
                        })
                    });

                    let response = fetch(request).then((response) => (response.json()))
                        .then(function (data) {
                            PicServicies.addPicture(crEvent.state.PictureURL.value, parseInt(data));
                        })
                })

        } else if (localStorage.getItem("fbauth")) {
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
            var request = new Request("http://localhost:60158/api/event/add", {
                method: "POST",
                headers: myheader,
                body: JSON.stringify({
                    "Name": crEvent.state.Name.value,
                    "Description": crEvent.state.Description.value,
                    "Place": crEvent.state.Place.value,
                    "Hour": crEvent.state.Hour.value,
                    "EndDate": crEvent.state.EndDate.value,
                    "BeginDate": crEvent.state.BeginDate.value,
                    "MinDateBirth": crEvent.state.MinDateBirth.value,
                    "NumberOfTickets": crEvent.state.NumberOfTickets.value,
                    "Status": crEvent.state.Status.value,
                    "UserId": "",
                    "CategoryId": crEvent.state.CategoryId.value
                })
            });

            let response = fetch(request).then((response) => (response.json()))
                .then(function (data) {
                    PicServicies.addPicture(crEvent.state.PictureURL.value, parseInt(data));
                })
        }

    }

}
