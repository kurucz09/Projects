import * as React from "react"
import * as ReactDOM from 'react-dom';
import * as jwt_decode from 'jwt-decode';

import {Header} from "./Header";
import AppViewer from "./AppViewer";
import {Footer} from "./Footer";
import {BrowserRouter as Router, Link, Route, Redirect} from 'react-router-dom';
import { FacebookServices } from '../Services/FbServices/Facebook';
import { LoginServices } from '../Services/LoginServices/Login';
import { connect } from 'react-redux';

import { showAlert } from '../Store/Actions';
import {bindActionCreators} from "redux";

interface IPropsInterface {}


interface IStateInterface {
    isLoggedIn : boolean;
    userLogged : string;
}

export class App extends React.Component < any,
IStateInterface > {

    constructor(props: any) {
        super(props);
        this.state = {
            isLoggedIn: false,
            userLogged: ""
        }
    }

    componentDidMount() {
        
        if (localStorage.getItem("auth")) {
            this.setState({isLoggedIn: true});
            this.setState({userLogged : LoginServices.getUserLogged()}); 
        } else if(localStorage.getItem("fbauth")){
            this.setState({isLoggedIn: true});
            FacebookServices.GetUsername(this, localStorage.getItem("fbauth"));  
        }
    }

    setLog(status : boolean) {
        this.setState({isLoggedIn: status});
        if(status === true){
            if (localStorage.getItem("auth")) {
                this.setState({userLogged : LoginServices.getUserLogged()}); 
            } else if(localStorage.getItem("fbauth")){
                var token = "";
                FacebookServices.GetUsername(this,localStorage.getItem("fbauth"));  
            }
        }
    }

    render() {

        return (
            <Router>
                <div className="full_height fill " id="app-background">
               
                    <div>
                        <Header isLogged={this.state.isLoggedIn} userLogged={this.state.userLogged}/>
                    </div>
                    < div className="app ">
                        <AppViewer {...this.props}                            
                            isLoggedIn={this
                            .setLog
                            .bind(this)} />
                    </div>
                    <div>
                        < Footer/>
                    </div>
                  </div>                  
            </Router>
        );

    }
    

}
