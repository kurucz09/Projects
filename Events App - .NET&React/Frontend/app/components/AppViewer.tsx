import * as React from "react"
import * as ReactDOM from 'react-dom';

import { LoginRegister } from './login_page/LoginRegister';

import {BrowserRouter as Router, Link, Route, Redirect} from 'react-router-dom';

import {Danger_Alert} from './login_page/Components/Danger_Alert';
import EventPg from "./main_page/EventPage/EventPg";
import {MainPage} from './main_page/Home/MainPage';
import EventsPage from './main_page/Events/EventsPage';
import CreateEvent from "./createEvent_page/CreateEvent";
import {UserProfile} from './userprofile_page/UserProfile';
import {connect} from 'react-redux';
import {withRouter} from "react-router";
import {hideAlert, showAlert} from '../Store/Actions';

interface IPropsInterface {
    isLoggedIn : any;
}

interface IStateInterface {
    isLogged : boolean;
    idE : any;
    categs: string[]
}

class AppViewer extends React.Component < any,
IStateInterface > {
    constructor(props : any) {
        super(props);
        this.state = {
            isLogged: false,
            idE: 0,
            categs: []
        };

    }

    componentDidMount() {

        if (localStorage.getItem("auth")) {
            this.setState({isLogged: true});
            this
                .props
                .isLoggedIn(true);
        } else if (localStorage.getItem("fbauth")) {
            this.setState({isLogged: true});
            this
                .props
                .isLoggedIn(true);
        }
        this
            .props
            .isLoggedIn(false);
    }

    isLoged(b : boolean) {
        this.setState({isLogged: b})
        this
            .props
            .isLoggedIn(b);
    }

    notDanger() {
        this
            .props
            .dispatch(hideAlert());
    }

    render() {
        const {isLogged} = this.state;
        return (
            <div className="full_height ">

                <Route
                    path='/'
                    exact
                    render={(props) => (isLogged
                    ? <Redirect to={"/home"}/>
                    : <LoginRegister
                        {...props}
                        isLoged={this
                        .isLoged
                        .bind(this)}/>)}/>
                <Route
                    path="/home"
                    render={(props) => (isLogged ?<MainPage {...props} type={"category"}/> : <Redirect to={"/"}/> )}/>
                <Route path='/profile' render={(props) => (isLogged ? <UserProfile {...props}/> : <Redirect to={"/"}/> )}/>
                <Route
                    path='/logout'
                    render={(props) => {
                    return <LoginRegister
                        {...props}
                        isLoged={this
                        .isLoged
                        .bind(this)}/>
                }}/>
                <div className="row alert-boxed">
                    <Danger_Alert
                        message={this.props.alert.message}
                        visible={this.props.alert.show}
                        dismised={this
                        .notDanger
                        .bind(this)}
                        type={this.props.alert.type}/>
                </div>
                <Route
                    path='/fb-login'
                    render={(props) =>  ( <MainPage {...props} type="login"/>)}/>
                <Route
                    path='/events/:idE'
                    render={(props) =>  (isLogged ? <EventsPage {...this.props} type="event" id={props.match.params.idE}/> : <Redirect to={"/"}/>)}/>
                <Route
                    path='/allevents'
                    render={(props) => (isLogged ? <EventsPage {...this.props} type="allevent" id="1"/> : <Redirect to={"/"}/>)}/>
                <Route
                    path='/getbyid/:idE'
                    render={(props) =>  (isLogged ? <EventPg {...this.props} type="getbyid" id={props.match.params.idE}/> : <Redirect to={"/"}/>)}/>

                <Route path='/createanevent' render={(props) => (isLogged ? <CreateEvent {...this.props}/> : <Redirect to={"/"}/>)}/>

                <Route
                    path='/shopping'
                    render={(props) =>  (isLogged ? [this.props.history.push("/"), this.props.history.go(-2), 
                        this.props.dispatch(showAlert(true, "This feature is not available yet. Thank you!", "red-alert"))] : <Redirect to={"/"}/>)}/>

            </div>

        );

    }

}

function mapStateToProps(state : any, history : any) {
    return {
        alert: {
            message: state.message,
            show: state.show,
            type: state.type
        }
    }
}

var connectedAppViewer = connect(mapStateToProps)(AppViewer as any);

export default withRouter(connectedAppViewer);