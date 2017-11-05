import * as React from "react"
import { Link } from 'react-router-dom';


interface InputProps{
    name:string;
    holder:string;
    descr:string;
}


export class Input extends React.Component < InputProps, {} > {

    constructor() {
        super();

    }

    render() {

        

        return (
            <div className="input-group">
             <span className="input-group-addon" id="basic-addon1">{this.props.name}</span>
             <input type="text" className="form-control" placeholder={this.props.holder} value={this.props.descr} aria-describedby="basic-addon1"></input>
            </div>
        )}}
