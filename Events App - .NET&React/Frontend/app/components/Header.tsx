import * as React from "react"
import * as ReactDOM from 'react-dom';
import {MenuBar} from "./main_page/MnuBar/Menu";

interface IPropsInterface {
    isLogged : boolean;
    userLogged : string;
}

export class Header extends React.Component < IPropsInterface,
any > {

    render() {

        return (
            <div>
                <MenuBar isLogged={this.props.isLogged} userLogged={this.props.userLogged}/>
            </div>
        );

    }

}