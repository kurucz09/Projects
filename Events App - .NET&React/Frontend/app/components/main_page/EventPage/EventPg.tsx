import * as React from "react";

import "../Components/Main.less";
import {Link} from "react-router-dom";
import {EventServices} from "../../../Services/EventsServices/Events";
import {EventsPics} from "../Events/EventsPics";
import {Input} from "./Input";
import { connect } from 'react-redux';
import { showAlert } from '../../../Store/Actions';

class IEventPgProps {
    type : string;
    id : any;

}

interface IStateEventPg {
    even : {
    Id : any;
    Description : string;
    Name : string;
    Place : string;
    Hour : any;
    BeginDate : any;
    EndDate : any;
    MinDateBirth : any;
    NumberOfTickets : any;
    Status : string;
    UserId : any;
    CategoryId : any;
    };
    mounted : boolean;
}
class EventPg extends React.Component < any,
IStateEventPg > {
    constructor()
    {
        super();
        this.state = {
            even: {
                Id: null,
                Description: "",
                Name: "",
                Place: "",
                Hour: null,
                BeginDate: null,
                EndDate: null,
                MinDateBirth: null,
                NumberOfTickets: null,
                Status: "",
                UserId: null,
                CategoryId: null
            },
            mounted: false
        }
    }

    componentWillMount() {
        if (this.props.type === "getbyid") {
            EventServices.getAllEvId(this, this.props.id);
        }
    }

    buyTickets(){
        this.props.dispatch(showAlert(true,"This feature is not available yet. Thank you!", "green-alert"));
    }

    render() {
        const {mounted} = this.state;
        return (
            <div className="row">

                <div className="col-md-2"></div>
                <div className="col-md-8">
                    <div className="container margin_container">
                    <div className="col-md-4 no-padding">
                        <div>
                            {mounted
                                ? <EventsPics idEvent={this.state.even.Id} eventName={this.state.even.Name} type="onlyEvent"/>
                                : <span></span>}

                        </div>

                    </div>
                    <div className="col-md-8 no-padding">

                        <div className="right_s">
                            <a id="text">{this.state.even.Name}</a>
                        </div>

                    </div>
                    
                        <div className="col-md-12 reduce-margin no-padding">
                            <div className="col-md-12 no-padding">
                                <div className="col-md-1 descript-icon-box ">
                                    <div className="no-padding"><img src="http://dezbateri.ro/pic/place.png" className="descript-icon"/></div>
                                </div>
                                <div className="col-md-3 no-padding title-width">
                                    <div className="no-padding"><p className="descript-half-title">{this.state.even.Place}</p></div>
                                </div>
                                <div className="col-md-1 descript-icon-box">
                                    <img src="http://dezbateri.ro/pic/calendar.png" className="descript-icon"/>
                                </div>
                                <div className="col-md-1 no-padding left-descript">
                                    <p className="descript_title">Start date</p>
                                    <p className="descript_title">End date</p>
                                </div>
                                <div className="col-md-2 no-padding">
                                    <p className="descript-half-title">{this.state.even.BeginDate}, {this.state.even.Hour}</p>
                                    <p className="descript-half-title">{this.state.even.EndDate}</p>
                                </div>
                                    <div className="col-md-1 descript-icon-box margin-left-buy">
                                    <img src="http://dezbateri.ro/pic/ticket.png" className="descript-icon"/>
                                </div>
                                 <div className="col-md-2 no-padding">
                                    <a className="descript_title" onClick={this.buyTickets.bind(this)}>Buy tickets!</a>
                                    <p>Tickets available: {this.state.even.NumberOfTickets}</p>
                                </div>

                            </div>
                          
                            <div className="col-md-12 no-padding margin-20">
                                <div className="col-md-1 descript-icon-box">
                                    <img src="http://dezbateri.ro/pic/info_clip.png" className="descript-icon"/>
                                </div>
                                <div>
                                    <p className="descript_title">Description</p>
                                </div>
                            </div>
                            <div className="col-md-12 no-padding">
                                <div className="col-md-1"></div>
                                <div className="col-md-10 no-padding">
                                    <p className="descript-text">{this.state.even.Description}</p>
                                </div>

                            </div>

                        </div>

                    </div>
                    <div className="col-md-2"></div>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state : any){
    return {
        alert: state.alert
    };
}
export default connect(mapStateToProps)(EventPg as any);