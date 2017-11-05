import * as React from "react"
import * as ReactDOM from 'react-dom';

import Login from "./Login/Login"
import Register from "./Register/Register"
import {BrowserRouter as  Router, Route } from 'react-router-dom';
import {PropTypes} from "react";
import { Danger_Alert } from './Components/Danger_Alert';
import {withRouter} from "react-router";

interface IPropsInterface {
    isLoged : any
}

interface IStateInterface {
    showLogin : boolean;
    isLoggedIn : boolean;
}

export class LoginRegister extends React.Component < any,
IStateInterface > {
    


    constructor(props :any) {
        super(props);
        this.state = {
            showLogin: true,
            isLoggedIn : false
        };
       
    }

    componentDidMount(){
        if(this.props.location.pathname === "/logout"){
           
            this.isLoged(false);
            if(localStorage.getItem("auth")){
                localStorage.removeItem("auth");
            }
            if(localStorage.getItem("fbauth")){
                localStorage.removeItem("fbauth");
            }
            this.props.history.push('/');
        }
    }

    changeShow() {
        this.setState({
            showLogin: !this.state.showLogin
        });
    }

    isLoged(b : boolean){
        this.setState({isLoggedIn : b});
        this.props.isLoged(this.state.isLoggedIn);
     }

    render() {
        return (
            <div className="full_height login_background">
                <div className="content_centered ">
                    {this.state.showLogin
                        ? <Login {...this.props}
                                isLoged={this
                                .isLoged
                                .bind(this)}
                                showRegister={this
                                .changeShow
                                .bind(this)}/>
                        : <Register {...this.props}
                            showRegister={this
                            .changeShow
                            .bind(this)}/>
                    }
                    
                </div>
            </div>    
        );
    }
}