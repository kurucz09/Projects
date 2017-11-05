import * as React from "react"

import "../login_page.less";

interface IPropsInterface {
    text : string;
    changeShow : any;
    triggerLog : any;
}

interface IStateInterface {};

export class Login_button extends React.Component < IPropsInterface,
IStateInterface > {

    constructor() {
        super();
    }

    showRegister() {
        this
            .props
            .changeShow();
    }

    buttonPressed() {
        this
            .props
            .triggerLog();
    }

    render() {
        return (
            <div>
                <a className="a-styled-login"
                    onClick={this
                    .showRegister
                    .bind(this)}>Don't have an account? <strong> Register now </strong>or</a>
                <button
                        role="button"
                    className="btn btn-primary login_button pull-right"
                    onClick={this
                    .buttonPressed
                    .bind(this)}>{this.props.text}</button>
            </div>

        );
    }

}
