import * as React from "react";
import "../Components/Main.less";
import {PicServicies} from "../../../Services/EventsServices/Pics";
import {Photo} from "../Components/Photo";

class IPropsPagePics {
    eventName : string;
    idEvent : number;
    type: string;
}
interface IStateEventPics {
    picEvent : {
    Id: any,
    PictureURL: string
    };
}
export class EventsPics extends React.Component < IPropsPagePics,
IStateEventPics > {
    constructor()
    {
        super();
        this.state = {
            picEvent: {
                Id: null,
                PictureURL: ""
            }
        }
    }

    componentDidMount() {
        if (this.props.idEvent != null) {
            PicServicies.getAllPictures(this, this.props.idEvent);
        }
    }

    render() {
        return (
            <div>
                {this.props.type === "onlyEvent"
                ?<div className="margin-event-pics">
                    <Photo srcP={this.state.picEvent.PictureURL} catName={this.props.eventName}/>
                </div>
                :<div >
                    <Photo srcP={this.state.picEvent.PictureURL} catName={this.props.eventName}/>
                </div>
                }
                
            </div>

        )
    }
}
