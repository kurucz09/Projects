import * as React from 'react';
import {UserServices} from '../../../Services/UserServices/UserServices';
import {Danger_Alert} from "../../login_page/Components/Danger_Alert";
import {Validator} from '../../../Services/Validation/Validator';
import {LoginObject} from '../../login_page/Objects/LoginObject';
import {Input_place} from '../../login_page/Components/Input_place';
import {connect} from 'react-redux';

interface IPassProps {
    old: any,
    new: any,
    confirm: any;
}

interface IPassState {
}

export class PassChange extends React.Component < any,
IPassState > {
    constructor() {
        super();

    }


    setNewPass (text : React.FormEvent < HTMLInputElement >) {
        this.props.new(text.toString());
    }

    setOldPass (text : React.FormEvent < HTMLInputElement >) {
        this.props.old(text.toString());
    }

    setConfirmPass (text : React.FormEvent < HTMLInputElement >) {
        this.props.confirm(text.toString());
    }

    

    render() {
        return (
                <div className="col-md-12 input-group no-padding">
                    <div className="passchange-margins">
                        <Input_place
                            placeholder="Old password"
                            type="password"
                            glyph="glyphicon-lock"
                            error=""
                            data={this
                            .setOldPass
                            .bind(this)}/>
                    </div>

                    <div className="passchange-margins">
                        <Input_place
                            placeholder="New password"
                            type="password"
                            glyph="glyphicon-pencil"
                            error=""
                            data={this
                            .setNewPass
                            .bind(this)}/>
                    </div>

                    <div className="passchange-margins">
                        <Input_place
                            placeholder="Confirm new password"
                            type="password"
                            glyph="glyphicon-pencil"
                            error=""
                            data={this
                            .setConfirmPass
                            .bind(this)}/>
                    </div>

                </div>

        );
    }
}

