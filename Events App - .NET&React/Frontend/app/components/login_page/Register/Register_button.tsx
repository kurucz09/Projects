import * as React from "react"

import "../login_page.less";

interface IPropsInterface {
    text : string;
    changeShow: any;
    triggerRegister: any;
}

interface IStateInterface {};

export class Register_button extends React.Component < IPropsInterface,
IStateInterface > {

   
    constructor() {
        super();
    } 

    showRegister(){
        this.props.changeShow();
    }

    triggered(){
        this.props.triggerRegister();
    }

    render() {
        return (
            <div>
                <a className="a-styled-login" onClick={this.showRegister.bind(this)}>Back to Login page</a>
                <button className="btn btn-primary login_button pull-right" onClick={this.triggered.bind(this)}>{this.props.text}</button>
            </div>

        );
    }

}
