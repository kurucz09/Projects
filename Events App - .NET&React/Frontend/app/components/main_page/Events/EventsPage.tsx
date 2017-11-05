import * as React from "react";

import "../Components/Main.less";

import {EventsPics} from "./EventsPics";
import {Link} from "react-router-dom";
import {EventServices} from "../../../Services/EventsServices/Events";
import { connect } from 'react-redux';

class IEventPageProps {
    type : string;
    id : any;
}
interface IStateEventPage {
    events : {
         Id : number;
    Description : string;
    Name : string;
    Place : string;
    Hour : any;
    BeginDate : any;
    EndDate : any;
    MinDateBirth : any;
    NumberOfTickets : number;
    Status : string;
    UserId : number;
    CategoryId : number;
    }[];

}
class EventsPage extends React.Component < any,IStateEventPage > {
    constructor()
    {
        super();
        this.state = {
            events: []
        }
    }

    componentDidMount() {
        if (this.props.type === "event") {
            EventServices.getAllById(this, this.props.id);
        }
        if (this.props.type === "allevent") {
            EventServices.getAll(this);
        }
    }

    render() {
        return (
            <div className="row">

                <div className="col-md-2"></div>
                <div className="col-md-3 no-padding">
                    {this
                        .state
                        .events
                        .map((events, i) => {
                            return (<EventsPics key={i} idEvent={events.Id} eventName={events.Name} type={this.props.type}/>);
                        })
}
                </div>
                 <div className="col-md-5 no-padding">
                    {this
                        .state
                        .events
                        .map((event, i) => {
                            return (
                                <div key={i} className="right_side">
                                    <Link  id="text" to={"/getbyid/" + event.Id}>{event.Name}
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

function mapStateToProps(state : any){
    return {
        alert: state.alert
    };
}
export default connect(mapStateToProps)(EventsPage);