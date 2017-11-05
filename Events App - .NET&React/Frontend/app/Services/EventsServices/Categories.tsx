import 'whatwg-fetch';

import {LoginObject} from '../../components/login_page/Objects/LoginObject';
import EventsPage from "../../components/main_page/Events/EventsPage";
import EventPg from "../../components/main_page/EventPage/EventPg";
import { MainPage } from '../../components/main_page/Home/MainPage';
import {ChooseCategory} from "../../components/createEvent_page/ChooseCategory";

export class CategoryServices {

    static API = "http://localhost:60158/api/";

    static getAllCategs(page : any, param : any) {

        var arr : {
                Id : number,
                PictureURL : any,
                Name : string
            }[];
        const API = "http://localhost:60158/api/";
        var myheader = new Headers({
            'Access-Control-Allow-Origin': '*', 
            'Content-Type': "application/json", 
            'content-Type': "application/json"});
        
        var token = ""
        if(localStorage.getItem("auth")){
            token = String(localStorage.getItem("auth"));
            myheader.set("auth_token", token);
        } else if(localStorage.getItem("fbauth")){
            token = String(localStorage.getItem("fbauth"));
            myheader.set("fb_auth", token);
        }

        var request = new Request(API + "category/getall", {
                method: "GET",
                headers: myheader
            });

        return fetch(request).then((response) => (response.json()))
                .then(function (data) {
                    arr = data;
                })
                .then(() => page.setState({[param]: arr}))
               

    }
   

    static getCategoriesForDropDown(page : ChooseCategory, param : any) {

        var arr : {
                Id : number,
                PictureURL : any,
                Name : string
            }[];
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

        var request = new Request(API + "category/getall", {
                method: "GET",
                headers: myheader
            });

        return fetch(request).then((response) => (response.json()))
                .then(function (data) {
                    arr = data;
                })
                .then(() => page.setState({[param]: arr}))
                .then(() => page.addCategories())
               

    }

}
