import * as React from 'react';
import {ExternalPlaceholder} from "../login_page/Components/ExternalPlaceholder";

interface IDescrProps {
    data: any;
    error : string;
}

interface IDescrState{
    isVisible: boolean;
    placeholder : string;
        
}

export class DescriptionEvent extends React.Component<IDescrProps,IDescrState>
{
    constructor(){
        super();
        this.state = {
            isVisible: false,
            placeholder : "Description"
        }
    }

    setDescr(e : React.FormEvent<HTMLInputElement>){
        this.props.data(e.currentTarget.value)
    }

    focusInput(){
        this.setState({isVisible: true, placeholder: ""})
    }

    unfocusInput(){
        this.setState({isVisible: false, placeholder: "Description"})
    }

    render (){
        return(
             <div className="input-group create-event-input-margin">
                        {this.state.isVisible && <ExternalPlaceholder placeholder="Description"/>}
                        <span className="input-group-addon input-personal" id="basic-addon1"><span className="glyphicon glyphicon-info-sign" aria-hidden="true"/></span>
                        <textarea
                            className={`form-control form-styled login_input ${this.props.error} description-width myownerror`}
                            placeholder={this.state.placeholder}
                            onFocus={this.focusInput.bind(this)}
                            onBlur={this.unfocusInput.bind(this)}
                            onChange={this
                            .setDescr
                            .bind(this)}/>
                    </div>
        );
    }
}