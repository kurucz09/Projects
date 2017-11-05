import * as React from 'react';
import { Validator } from '../../../Services/Validation/Validator';
import { UserServices } from '../../../Services/UserServices/UserServices';

interface IUserProps {
    userEmail : any,
    updateUser : any,
}

interface IUserState {
    shower : string,
    input : string,
     einter : string,
     emailLocal : string
}

export class EmailChange extends React.Component<IUserProps, IUserState>{
    constructor() {
        super();
        this.state = {
             input: "disabled-input",
            shower: "enabled-input",
             einter: "",
             emailLocal : ""
        }
    }

    onButtonClickEmail(e : React.FormEvent < HTMLInputElement >) {
            if (Validator.validateEmail(this.state.einter) == true) {
                //dangerService.chillDownAlert();
                this.setState({input: "disabled-input", shower:"enabled-input"});
                this.setState({emailLocal: this.state.einter});
                this.props.updateUser(this.state.einter);
            } else{ 
                //dangerService.setAlert(true, "Wrong e-mail format");
            }   
    }

    onEdit() {
        if(this.state.input === "enabled-input"){
            this.setState({input: "disabled-input", shower:"enabled-input"});  
        } else {
            this.setState({input: "enabled-input", shower:"disabled-input"});
        }
       
    }

    onValueChange(e : React.FormEvent < HTMLInputElement >) {
        this.setState({einter: e.currentTarget.value});
    }


    render() {
        var changed;
        this.state.emailLocal===""?changed=false : changed=true;
        return (
           <div className="input-group  margin-down">
                    <span className="input-group-addon input-personal" id="sizing-addon2">
                        <span className="glyphicon glyphicon-envelope"/>
                        </span>
                    <p className={`form-control ${this.state.shower}`}> 
                        {
                            changed?this.state.emailLocal
                            :this.props.userEmail
                        }
                    </p>
                    <input className={`form-control input-green ${this.state.input}`} onChange={this.onValueChange.bind(this)} placeholder="write your new e-mail"/>
                    <div className="input-group-btn">
                        <button
                            type="button"
                            className="btn btn-default button-gray"
                            onClick={this
                            .onEdit
                            .bind(this)}><span className="glyphicon glyphicon-edit"/></button>
                        <button
                            type="button"
                            className="btn btn-default button-gray"
                            onClick={this
                            .onButtonClickEmail
                            .bind(this)}><span className="glyphicon glyphicon-floppy-saved"/></button>
                    </div>
                </div>
        );
    }
}
