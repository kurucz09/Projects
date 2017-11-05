import * as React from "react"
import * as ReactDOM from 'react-dom';

import "../login_page.less";

import {Input_place} from "../Components/Input_place"
import {Login_button} from "./Login_button"
import {Danger_Alert} from '../Components/Danger_Alert';
import {Validator} from '../../../Services/Validation/Validator';
import {LoginServices} from '../../../Services/LoginServices/Login';

import {LoginObject} from '../Objects/LoginObject';
import {Link} from 'react-router-dom';
import { showAlert, hideAlert } from '../../../Store/Actions';
import { connect } from 'react-redux';


interface IPropsInterface {
    showRegister : any;
    isLoged : any;
}

interface IStateInterface {

    showRegister : boolean;
    username : {
        value: string,
        isValid: boolean
    };
    password : {
        value: string,
        isValid: boolean
    };
    isLoged : boolean;
}

class Login extends React.Component < any,
IStateInterface > {

    constructor() {
        super();
        this.state = {
            showRegister: false,
            username: {
                value: "",
                isValid: true
            },
            password: {
                value: "",
                isValid: true
            },
            isLoged: false
        };

        this.changeShow = this
            .changeShow
            .bind(this);
        this.tryToLog = this
            .tryToLog
            .bind(this);
    }

    changeShow() {
        this.setState({showRegister: true});
        this
            .props
            .showRegister();
    }

    tryToLog() {
        var loginObject = new LoginObject();
        loginObject.password = this.state.password;
        loginObject.username = this.state.username;
        loginObject.verified = true;

        loginObject = Validator.validateLogin(loginObject);
        this.setState({password: loginObject.password});
        this.setState({username: loginObject.username});
        if (loginObject.verified === false) {
            if (loginObject.username.isValid === false) {
                this.props.dispatch(showAlert(true,"Wrong username format","red-alert"));
            } else if (loginObject.password.isValid === false) {
                this.props.dispatch(showAlert(true,"Password too short","red-alert"));
            }
        } else {
            this.props.dispatch(hideAlert());
            var loginResult = LoginServices.tryToLog(this, loginObject);
        }

    }

    loginSucceeded() {
        this
            .props
            .isLoged(this.state.isLoged);
    }

    dataBind = (type : any, text : React.FormEvent < HTMLInputElement >) => {
        var content = {
            value: text,
            isValid: true
        };
        this.setState({[type]: content});
    }

    render() {
        let myvar;
        return (

            <div className="container col-md-12 repair">
                <div className="h2_margin title-centered">
                    <h2>Login now!</h2>
                </div>
                <div className="log-reg-margins">
                    <Input_place
                        placeholder="username"
                        type="text"
                        glyph="glyphicon-user"
                        error={this.state.username.isValid
                        ? ""
                        : "login_error"}
                        data={this
                        .dataBind
                        .bind(this, "username")}/>
                </div>

                <div className="log-reg-margins">
                    <Input_place
                        placeholder="password"
                        type="password"
                        glyph="glyphicon-lock"
                        error={this.state.password.isValid
                        ? ""
                        : "login_error"}
                        data={this
                        .dataBind
                        .bind(this, "password")}/>
                </div>
                <Login_button
                    text="Login"
                    changeShow={this
                    .changeShow
                    .bind(this)}
                    triggerLog={this
                    .tryToLog
                    .bind(this)}/>
                <div className="col-md-8 facebook-container">
                    <a
                        className="facebook-button"
                        href="https://www.facebook.com/v2.10/dialog/oauth?client_id=133801767137872&response_type=token&redirect_uri=http://localhost:8080/fb-login">
                        <img
                            className="facebook-icon"
                            src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/F_icon.svg/2000px-F_icon.svg.png"/>
                        Login with facebook
                    </a>
                </div>

            </div>
        );
    }
}

function mapStateToProps(state : any){
    return {
        alert: state.alert
    };
}
export default connect(mapStateToProps)(Login);