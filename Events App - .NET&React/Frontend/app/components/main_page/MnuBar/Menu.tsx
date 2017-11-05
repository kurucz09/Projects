import * as React from "react";
import { Link } from 'react-router-dom';

import {DropDown} from "./MenuItems";
import {Item} from "./Item";
import {DropDownToggle} from "./DropDownToggle";


import "../Components/Main.less"

interface IPropsMenubar{
    isLogged : boolean;
    userLogged : string;
}

export class MenuBar extends React.Component < IPropsMenubar, {} > {
    render() {
        return (
            <div className="menubar ">
                <nav className="navbar navbar-default menu-style">
                    <div className="container-fluid">
                        <div className="navbar-header menu-brand">
                            <img className="brandName brand" src="http://dezbateri.ro/pic/logo.png"/>
                        </div>
                        <ul className="nav navbar-nav">
                            <li className="Home">
                                <Link to="/home">
                                    <img
                                    className="imagine"
                                        id="img"
                                        src="https://image.flaticon.com/icons/svg/25/25694.svg"
                                        width="25%"
                                        alt="."/>
                                    Home
                                </Link>
                            </li>
                            <li className="dropdown ">
                                <DropDownToggle name="Events" refD="*"></DropDownToggle>
                                <DropDown
                                    items={[
                                    new Item("/allevents", "All events"),
                                    new Item("/createanevent", "Create an event")
                                ]}></DropDown>
                            </li>
                        </ul>

                        <ul className="nav navbar-nav navbar-right">
                            <li className="shoppingCart">
                                <Link to="/shopping">
                                    <img
                                    className="imagine"
                                        id="img"
                                        src="https://image.flaticon.com/icons/svg/25/25619.svg"
                                        width="14%"
                                        alt="."/>
                                    Shopping cart
                                </Link>
                            </li>
                            {this.props.isLogged
                            ?<li className="dropdown">
                                <DropDownToggle name={this.props.userLogged} refD="*"></DropDownToggle>
                                <DropDown
                                    items={[
                                    new Item("/profile", "Your profile"),
                                    new Item("/logout", "Log out")
                                ]}></DropDown>
                            </li>
                            :<li className="Home">
                                <Link to="/">
                                    Log in
                                </Link>
                            </li>}
                        </ul>

                    </div>
                </nav>
            </div>
        )
    }
}