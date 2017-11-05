import * as React from "react";

import "../Components/Main.less"
import {Link} from "react-router-dom";
import {Photo} from "../Components/Photo";
import { CategoryServices } from '../../../Services/EventsServices/Categories';

class IMainPageProps {
    type : string;
}
interface IStateMainPage {
    picture : {
        Id : number,
        PictureURL : any,
        Name : string
    }[];
}
export class MainPage extends React.Component < any,
IStateMainPage > {
    constructor( )
    {
        super();
        this.state = {
            picture: []
        }
       
    }

    componentDidMount() {
        if(this.props.location.pathname === "/fb-login"){
             var string = this.props.location.hash;
             var token = string.substring(string.indexOf("=")+1,string.indexOf("&"));
             localStorage.setItem("fbauth",token);
             this.props.history.push('/home');
        } else 
        if (this.props.type === "category") {
            if(this.refs.myRef)
                CategoryServices.getAllCategs(this,"picture");
        } 
    }

    render() {
        return (
            <div className="row" ref="myRef">
                <div className="col-md-2"></div>

                <div className="col-md-3 no-padding">
                    {this
                        .state
                        .picture
                        .map((picture, i) => {
                            return (<Photo key={i} srcP={picture.PictureURL} catName={picture.Name}/>);

                        })
            }
                </div>
                <div className="col-md-5 no-padding">
                    {this
                        .state
                        .picture
                        .map((picture, i) => {
                            return (
                                <div key={i} className="right_side">
                                    <Link id="text" to={"/events/"+picture.Id} >{picture.Name} 
                                    </Link>
                                </div>
                            )
                        })
            }
                </div>
                <div className="col-md-2"></div>
            </div>

        )
    }
}