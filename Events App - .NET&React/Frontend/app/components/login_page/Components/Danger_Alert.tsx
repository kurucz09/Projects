import * as React from "react"
import * as ReactDOM from 'react-dom';

interface IPropsInterface {
    message : string;
    visible : boolean;
    dismised: any;
    type : string;
}

interface IStateInterface {
    isVisible : boolean;
}

export class Danger_Alert extends React.Component < IPropsInterface,
any > {

    constructor(){
        super();
        this.state = {
            isVisible: false
        }
    }

    hide(){
        this.props.dismised();
    }

    render() {
        var disabled;
        {this.props.visible? disabled=null : disabled="danger_disabled"}
        return (
          
                <div className={`alert alert-danger ${disabled} ${this.props.type}`}>
                    <a onClick={this.hide.bind(this)} className="close_icon pull-right"  aria-label="close">&times;</a>
                    <strong>{this.props.message}</strong>
                </div>
        );

    }

}
