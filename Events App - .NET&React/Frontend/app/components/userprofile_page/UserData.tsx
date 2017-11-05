import * as React from 'react';
import '../../less/user.less'
import {UserServices} from "../../Services/UserServices/UserServices";
import {PassChange} from "./UserDataComponents/PassChange";
import { Validator } from '../../Services/Validation/Validator';
import { EmailChange } from './UserDataComponents/EmailChange';
import { LoginObject } from '../login_page/Objects/LoginObject';
import { connect } from 'react-redux';

interface IUserState {
    show : boolean;
    email : string;
    visiblePassword: boolean,
    oldPassword : string, 
    newPassword: string,
    confirmPassword: string
}

interface IUserProps {
    userData : any,
    updateUser: any,
    fbLogged: boolean
}

class UserData extends React.Component < any,
IUserState > {
    constructor() {
        super();

        this.state = {   
            show: false,
            email: "",
            visiblePassword: false,
            oldPassword:"", 
            newPassword:"",
            confirmPassword:""
            
        }
    }
   
    showPassChange(){
        this.setState({ show: !this.state.show })
    }

    onButtonClick(){
        this.setState({show: !this.state.show})
    }

    capitalize(s : string) {
        //$('#s').css('textTransform', 'capitalize');
        return s
            .charAt(0)
            .toUpperCase() + s.slice(1);
    }
    
    updateUsr(email: any){
        
        UserServices.tryToChangeAddress(this, email);
        this.props.updateUser();
    }

    checkIfCompleted(){
        if(this.state.newPassword !=="" && this.state.confirmPassword!=="" && this.state.oldPassword!==""){
            this.setState({visiblePassword: true});
        }
    }

    updateOldPass(old : string){
        this.setState({oldPassword : old})
        this.checkIfCompleted();
    }

    updateNewPass(newP : string){
        this.setState({newPassword : newP})
        this.checkIfCompleted();
    }

    updateConfirmPass(confirm : string){
        this.setState({confirmPassword : confirm})
        this.checkIfCompleted();
    }

    onSave() {
        var loginObject = new LoginObject();
        loginObject.password = {
            value: this.state.oldPassword,
            isValid: true
        };
        loginObject.username = {
            value: this.props.userData.Username,
            isValid: true
        };
        loginObject.verified = true;

        UserServices.tryToChangePassword(this, loginObject);
    }

    render() {  
        return (
            <div className="col-md-12">
                {this.props.fbLogged 
                ?
                    <div>
                        <div className="col-md-12 facebook-margin-bottom">
                            <div className="col-md-1 glyphicon glyphicon-user input-personal facebook-icons no-margins"/>
                            <div className="col-md-9">
                                <p className="facebook-profile-text-description">
                                    Name    
                                </p>    
                                <p className="facebook-profile-text">
                                     {this.props.userData.name}
                                </p>
                                
                            </div> 
                        </div>
                        <div className="col-md-12 facebook-margin-bottom">
                            <div className="col-md-1 glyphicon glyphicon-user input-personal facebook-icons no-margins"/>
                            <div className="col-md-9">
                                <p className="facebook-profile-text-description">
                                    Email    
                                </p>    
                                <p className="facebook-profile-text">
                                     {this.props.userData.email}
                                </p>
                                
                            </div> 
                        </div>
                        <div className="col-md-12 facebook-margin-bottom">
                            <div className="col-md-1 glyphicon glyphicon-user input-personal facebook-icons no-margins"/>
                            <div className="col-md-9">
                                <p className="facebook-profile-text-description">
                                    Age    
                                </p>    
                                <p className="facebook-profile-text">
                                     {this.props.userData.age} years old
                                </p>
                                
                            </div> 
                        </div>
                        <div className="col-md-12 facebook-margin-bottom">
                            <div className="col-md-1 glyphicon glyphicon-user input-personal facebook-icons no-margins"/>
                            <div className="col-md-9">
                                <p className="facebook-profile-text-description">
                                    Gender    
                                </p>    
                                <p className="facebook-profile-text">
                                     {this.props.userData.gender}
                                </p>
                                
                            </div> 
                        </div>
                    </div>
                :
                    <div>
                        <p className="title-profile">Name: {this.capitalize(this.props.userData.Name)} </p>
                        <div className="input-group margin-down">
                            <span className="input-group-addon input-personal"><span className="glyphicon glyphicon-user "/></span>
                            <p className="form-control"> {this.props.userData.Username}</p>
                        </div>
                        <EmailChange userEmail={this.props.userData.Email} updateUser={this.updateUsr.bind(this)}/>
                        <div className="input-group margin-down">
                            <span className="input-group-addon input-personal" id="sizing-addon2"><span className="glyphicon glyphicon-gift"/></span>
                            <p className="form-control"> {this.props.userData.DateBirth}</p>
                        </div>
                        {this.state.show  
                        ?<div className="col-md-12 no-margins no-padding">
                            <div className="col-md-2 no-margins no-padding">
                                <div className="button-profile noselect" onClick={this.onButtonClick.bind(this)} >
                                    {this.state.show ? "Hide" : "Change password"}
                                </div>
                            </div>
                            <div className="col-md-7 no-margins no-padding">
                                {this.state.show ? <PassChange {...this.props} 
                                        old={this.updateOldPass.bind(this)}
                                        new={this.updateNewPass.bind(this)}
                                        confirm={this.updateConfirmPass.bind(this)}/> : null}
                            </div>
                            <div className="col-md-2 no-margins no-padding pull-right">
                                {this.state.visiblePassword
                                ? <span
                                    className="btn btn-danger button-profile save-password"
                                            onClick={this
                                            .onSave
                                            .bind(this)}>Save
                                        <span className="glyphicon glyphicon-floppy-save margin-left"/>
                                    </span>
                                : null}
                            </div>
                        </div>    
                        :<div className="col-md-12 no-margins no-padding">
                            <div className="col-md-4 no-margins no-padding">
                                <div className="button-profile noselect" onClick={this.onButtonClick.bind(this)} >
                                    {this.state.show ? "Hide" : "Change password"}
                                </div>
                            </div>
                        </div>
                        }
                    </div>
                }
                            
            </div>

        );
    }
}

function mapStateToProps(state : any) {
    return {alert: state.alert};
}
export default connect(mapStateToProps)(UserData);