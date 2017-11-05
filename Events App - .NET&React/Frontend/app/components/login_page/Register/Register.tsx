import * as React from "react";
import * as ReactDOM from 'react-dom';
import * as moment from 'moment';

import "../login_page.less";

import {Input_place} from "../Components/Input_place"
import {Login_button} from '../Login/Login_button';
import {Danger_Alert} from '../Components/Danger_Alert';
import {Register_button} from './Register_button';
import {Image_input} from './Image_input';
import {Date_picker} from './Date_picker';


import {Validator} from '../../../Services/Validation/Validator';
import {RegisterObject} from '../Objects/RegisterObject';
import { LoginServices } from '../../../Services/LoginServices/Login';
import { UserServices } from '../../../Services/UserServices/UserServices';
import { connect } from 'react-redux';
import { showAlert, hideAlert } from '../../../Store/Actions';


interface IPropsInterface {
    showRegister : any;
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
    passwordRepeat : {
        value: string,
        isValid: boolean
    };
    email : {
        value: string,
        isValid: boolean
    };
    name : {
        value: string,
        isValid: boolean
    };
    dateBirth : {
        value: string,
        isValid: boolean
    };
    imageURL : {
        value: string,
        isValid: boolean
    };
    isValid : boolean,

}

class Register extends React.Component < any,IStateInterface > {
    
    constructor() {
        super();
        this.state = {
            showRegister: true,
            username: {
                value: "",
                isValid: true
            },
            password: {
                value: "",
                isValid: true
            },
            passwordRepeat: {
                value: "",
                isValid: true
            },
            email: {
                value: "",
                isValid: true
            },
            name: {
                value: "",
                isValid: true
            },
            dateBirth: {
                value: "",
                isValid: true
            },
            imageURL: {
                value: "",
                isValid: true
            },
              isValid : true
        };
       
    }

    changeShow() {
        this.setState({showRegister: true});
        this
            .props
            .showRegister();
    }

    tryRegister() {
        var registerObject = new RegisterObject();
        registerObject.username = this.state.username;
        registerObject.password = this.state.password;
        registerObject.passwordRepeat = this.state.passwordRepeat;
        registerObject.dateBirth = this.state.dateBirth;
        registerObject.email = this.state.email;
        registerObject.name = this.state.name;
        registerObject.imageURL = this.state.imageURL;
        registerObject.verified = true;
        registerObject.passwordMatch = true;

        registerObject = Validator.validateRegister(registerObject);
   
        this.setState({username: registerObject.username});
        this.setState({password: registerObject.password});
        this.setState({passwordRepeat: registerObject.passwordRepeat});
        this.setState({email: registerObject.email});
        this.setState({name: registerObject.name});
        this.setState({dateBirth: registerObject.dateBirth});
        this.setState({imageURL: registerObject.imageURL});

          if (registerObject.verified === false) {
            if (registerObject.username.isValid === false) {
                this.props.dispatch(showAlert(true,"Wrong username format [ only use letters, numbers or {. _ -}]", "red-alert"));
            } else if (registerObject.password.isValid === false) {
                this.props.dispatch(showAlert(true,"Password too short", "red-alert"));
            }else if (registerObject.passwordMatch === false) {
                this.props.dispatch(showAlert(true,"Passwords should match", "red-alert"));
            }else if (registerObject.name.isValid === false) {
                this.props.dispatch(showAlert(true,"Invalid name. Capitalize Each Word", "red-alert"));
            }else if (registerObject.email.isValid === false) {
                this.props.dispatch(showAlert(true,"Invalid email. Please use your real E-mail address", "red-alert"));
            }else if (registerObject.dateBirth.isValid === false) {
                this.props.dispatch(showAlert(true,"Invalid date. Please select your real datebirth", "red-alert"));
            }else if (registerObject.imageURL.isValid === false) {
                 this.props.dispatch(showAlert(true,"Select a profile picture", "red-alert"));
            }
        } else {
            this.props.dispatch(hideAlert());
            UserServices.insertUser(this, registerObject);
        }
    }

    registerSuccess(){
       this.props.dispatch(showAlert(true,"Your account has been created. You can login now","green-alert"));
        this.changeShow();
    }

    dataBind = (param : any, text : React.FormEvent < HTMLInputElement >) => {
          var content = {
            value: text,
            isValid: true
        };
        this.setState({
            [param]: content
            })
    }


    render() {
        let myvar;
        return (
            <div className="container col-md-12 repair">
                <div className="h2_margin title-centered">
                    <h2>Register</h2>
                </div>
                 <div className="col-md-6">
                    <div className="log-reg-margins">
                        <Input_place
                        placeholder="username"
                        type="text"
                        error={this.state.username.isValid
                        ? ""
                        : "login_error"}
                        glyph="glyphicon-user"
                        data={this
                        .dataBind
                        .bind(this, "username")}/>
                    </div>
                    
                    <div className="log-reg-margins">
                        <Input_place
                            placeholder="password"
                            type="password"
                            error={this.state.password.isValid
                            ? ""
                            : "login_error"}
                            glyph="glyphicon-lock"
                            data={this
                            .dataBind
                            .bind(this, "password")}/>
                    </div>
                    <div className="log-reg-margins">
                        <Input_place
                            placeholder="repeat password"
                            type="password"
                            error={this.state.passwordRepeat.isValid
                            ? ""
                            : "login_error"}
                            glyph="glyphicon-lock"
                            data={this
                            .dataBind
                            .bind(this, "passwordRepeat")}/>
                    </div>
                    <div className="log-reg-margins">
                        <Input_place
                            placeholder="e-mail"
                            type="text"
                            error={this.state.email.isValid
                            ? ""
                            : "login_error"}
                            glyph="glyphicon-envelope"
                            data={this
                            .dataBind
                            .bind(this, "email")}/>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="log-reg-margins">
                        <Input_place
                            placeholder="name"
                            type="text"
                            error={this.state.name.isValid
                            ? ""
                            : "login_error"}
                            glyph="glyphicon-pencil"
                            data={this
                            .dataBind
                            .bind(this, "name")}/>
                    </div>
                    <div className="log-reg-margins">
                        <Date_picker
                            placeholder="date of birth"
                            type="text"
                            error={this.state.dateBirth.isValid
                            ? ""
                            : "login_error"}
                            glyph="glyphicon-th"
                            data={this
                            .dataBind
                            .bind(this, "dateBirth")}/>
                    </div>
                    <div className="log-reg-margins">
                        <Image_input
                            placeholder="picture"
                            glyph="glyphicon-picture"
                            error={this.state.imageURL.isValid
                            ? ""
                            : "login_error"}
                            data={this
                            .dataBind
                            .bind(this, "imageURL")}/>
                    </div>
                    <Register_button
                        text="Create account"
                        changeShow={this
                        .changeShow
                        .bind(this)}
                        triggerRegister={this
                        .tryRegister
                        .bind(this)}/>
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
export default connect(mapStateToProps)(Register as any);