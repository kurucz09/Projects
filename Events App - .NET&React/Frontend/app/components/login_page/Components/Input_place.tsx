import * as React from "react"

import "../login_page.less";

import {ExternalPlaceholder} from "./ExternalPlaceholder";

interface IPropsInterface {
    placeholder : string;
    type : string;
    glyph : string;
    data : any;
    error: string;
}

interface IStateInterface {
    placeholder : string;
    isVisible : boolean;

};

export class Input_place extends React.Component < IPropsInterface,
IStateInterface > {

    constructor() {
        super();
        this.focusInput = this
            .focusInput
            .bind(this);
        this.unfocusInput = this
            .unfocusInput
            .bind(this);
        this.triggerChange = this
            .triggerChange
            .bind(this);
        this.state = {
            isVisible: false,
            placeholder: ""
        };
    }

    componentDidMount() {
        this.setState({placeholder: this.props.placeholder});

    }

    focusInput() {
        this.setState({placeholder: ""});
        this.setState({isVisible: true});
    }

    unfocusInput() {
        this.setState({placeholder: this.props.placeholder});
        this.setState({isVisible: false});
    }

    triggerChange= (e : React.FormEvent < HTMLInputElement >) => {
        const newValue = e.currentTarget.value;
        this.props.data(newValue);
    }

    render() {
        return (

            <div className="login_cluster">
                {this.state.isVisible && <ExternalPlaceholder placeholder={this.props.placeholder}/>}
                <div className={`input-group login_group ${this.props.error}`}>
                    <span className={`input-group-addon input-personal glyphicon ${this.props.glyph}`}></span>
                    <input
                        defaultValue=""
                        className={"form-control form-styled login_input"}
                        type={this.props.type}
                        placeholder={this.state.placeholder}
                        onFocus={this.focusInput}
                        onChange={this.triggerChange}
                        onBlur={this.unfocusInput}></input>
                </div>
            </div>

        );
    }

}
