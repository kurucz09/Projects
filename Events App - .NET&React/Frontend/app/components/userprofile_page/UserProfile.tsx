import * as React from 'react';
import 'whatwg-fetch';
import '../../less/user.less'
import UserData from "../../components/userprofile_page/UserData";
import {UserServices} from '../../Services/UserServices/UserServices';
import * as jwt_decode from "jwt-decode";
import { FacebookServices } from '../../Services/FbServices/Facebook';
import ProfilePicture from './ProfilePicture';

interface IUserState {
    userDatas : {Id: any, RoleId : any, Name: any, Email: any, Password: any, Username: any, DateBirth: any, PictureURL: any};
    userFbData: {id: any, name: any, email:any, age: any, gender: any, picture: any}
    facebookLogged : boolean;
}

export class UserProfile extends React.Component < any,
IUserState > {

    constructor() {
        super();
        this.state = {
            userDatas: {Id: "1", RoleId : "1", Name: "empty", Email: "empty", Password: "not visible", Username: "empty", DateBirth: "empty", PictureURL: "empty"},
            userFbData:{id: "1", name: "empty", email:"empty", age: "1", gender: "empty",picture: "empty" },
            facebookLogged : false
        };
    }

    componentWillMount() {
        if(localStorage.getItem("auth")){
            UserServices.getUserByUsername(this);   
            this.setState({facebookLogged: false});        
        }else if(localStorage.getItem("fbauth")){
            FacebookServices.getUserInfo(this, localStorage.getItem("fbauth")); 
            this.setState({facebookLogged: true});
        }
    }

    updateOwnUser(){
        UserServices.getUserByUsername(this);
    }


    render() {
        return (
            <div className="bord">
                <div className="full">
                    <p className="scris">My profile</p>
                </div>
                <div className="col-md-12 margin">
                    <div className="col-md-4">
                        {this.state.facebookLogged
                        ?  <ProfilePicture {...this.props} fbLogged={true} userData={this.state.userFbData}/>
                        :  <ProfilePicture {...this.props} fbLogged={false} userData={this.state.userDatas}/>
                        } 
                       
                    </div>
                    <div className="col-md-8 padding-left">
                         {this.state.facebookLogged
                         ? <UserData {...this.props} fbLogged={true} userData={this.state.userFbData}/>
                         : <UserData {...this.props} fbLogged={false} userData={this.state.userDatas} updateUser={this.updateOwnUser.bind(this)} />
                         }
                        
                    </div>
                    <div className="col-md-12 ">
                        {this.state.facebookLogged
                        ? <div className="bordered-top">
                                <h5 className="logged-with-title">Logged in with</h5>
                                <img src="https://cdn.worldvectorlogo.com/logos/facebook-1.svg" className="facebook-logo"/>
                            </div>
                        : null
                        }
                    </div>
                </div>
            </div>
        )
    }
        
    
}
