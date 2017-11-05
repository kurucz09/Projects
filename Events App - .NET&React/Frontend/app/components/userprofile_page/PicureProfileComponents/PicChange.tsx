import * as React from 'react';
import {UserServices} from "../../../Services/UserServices/UserServices";
import { Validator } from '../../../Services/Validation/Validator';

interface IPicChangeProps {
    user : any;
    changePicture: any;
}

interface IPicChangeState {
    url : string;
    showPop : boolean;
}

export class PicChange extends React.Component < IPicChangeProps,
IPicChangeState > {
    constructor() {
        super();
        this.state = {
            url: "",
            showPop: false
        } 
    }

    onTextChange(e : React.FormEvent < HTMLInputElement >) {
        this.setState({url: e.currentTarget.value})
    }

    onSave() {
        this.props.changePicture(this.state.url);
       
    }

    render() {
        return (
            <div className="">
                <div className="input-group margin-top-picChange">
                    <input
                        type="text"
                        className="form-control input-green"
                        aria-describedby="sizing-addon2"
                        placeholder="Insert picture url"
                        onChange={this
                        .onTextChange
                        .bind(this)}/>
                    <div className="input-group-btn no-margin ">
                        <button
                            type="button"
                            className="btn btn-default button-profile no-margins"
                            onClick={this
                            .onSave
                            .bind(this)}><span className="glyphicon glyphicon-floppy-saved"/></button>
                    </div>
                </div>
            </div>
        );
    }
}