import * as React from 'react';
import '../../less/crtevent.less'
import {Date_picker} from "../login_page/Register/Date_picker";
import {TimePicker} from "./TimePicker";
import {EventServices} from "../../Services/EventsServices/Events";
import {CategoryServices} from "../../Services/EventsServices/Categories";
import {ChooseCategory} from "./ChooseCategory";
import {DescriptionEvent} from "./DescriptionEvent";
import {Input_place} from "../login_page/Components/Input_place";
import {ValidatorEvents} from "./ValidatorEvents";
import {EventFbServices} from "../../Services/FbServices/EventFbServices";
import { connect } from 'react-redux';
import { showAlert } from '../../Store/Actions';

interface ICrtEventState {

    Name : {
        value: string,
        isValid: boolean
    };
    Description : {
        value: string,
        isValid: boolean
    };
    Place : {
        value: string,
        isValid: boolean
    };
    Hour : {
        value: string,
        isValid: boolean
    };
    BeginDate : {
        value: string,
        isValid: boolean
    };
    EndDate : {
        value: string,
        isValid: boolean
    };
    MinDateBirth : {
        value: string,
        isValid: boolean
    };
    NumberOfTickets : {
        value: number,
        isValid: boolean
    };
    Status : {
        value: string,
        isValid: boolean
    };
    IdU : {
        value: number,
        isValid: boolean
    };
    CategoryId : {
        value: number,
        isValid: boolean
    };
    PictureURL : {
        value: string,
        isValid: boolean
    };
    categs : {
        CategoryId: any,
        NameC: any,
        PictureURL: any
    }[];
}


class CreateEvent extends React.Component < any,
ICrtEventState > {

    constructor() {
        super();
        this.state = {

            Name: {
                value: "",
                isValid: true
            },
            Description: {
                value: "",
                isValid: true
            },
            Place: {
                value: "",
                isValid: true
            },
            Hour: {
                value: "",
                isValid: true
            },
            BeginDate: {
                value: "",
                isValid: true
            },
            EndDate: {
                value: "",
                isValid: true
            },
            MinDateBirth: {
                value: "",
                isValid: true
            },
            NumberOfTickets: {
                value: 0,
                isValid: true
            },
            Status: {
                value: "",
                isValid: true
            },
            IdU: {
                value: 0,
                isValid: true
            },
            CategoryId: {
                value: 0,
                isValid: true
            },
            PictureURL: {
                value: "",
                isValid: true
            },
            categs: []
        };
    }

   dataBind = (param : any, text : React.FormEvent < HTMLInputElement >) => {
            var content = {
            value: text,
            isValid: true
        };
        this.setState({
            [param]: content   
            })
    }

    
    onButtonClick() {

        this.setState({Name: ValidatorEvents.isNotNull(this.state.Name)});
        this.setState({Description: ValidatorEvents.isDescriptionValid(this.state.Description)});
        this.setState({Place: ValidatorEvents.isNotNull(this.state.Place)});
        this.setState({BeginDate: ValidatorEvents.isNotNull(this.state.BeginDate)});
        this.setState({EndDate: ValidatorEvents.isNotNull(this.state.EndDate)});
        this.setState({Hour: ValidatorEvents.isNotNull(this.state.Hour)});
        this.setState({MinDateBirth: ValidatorEvents.isAgeValid(this.state.MinDateBirth)});
        this.setState({NumberOfTickets: ValidatorEvents.isNumberOfTicketsValid(this.state.NumberOfTickets)});
        this.setState({CategoryId: ValidatorEvents.isCategoryValid(this.state.CategoryId)});
        this.setState({PictureURL: ValidatorEvents.isPictureURLValid(this.state.PictureURL)});

        if(!this.state.Name.isValid){
            this.props.dispatch(showAlert(true, "Name is invalid", "red-alert"))
        } else if(!this.state.Description.isValid) {
            this.props.dispatch(showAlert(true, "The Description must be at least 50 characters long", "red-alert"))
        } else if(!this.state.Place.isValid)
            this.props.dispatch(showAlert(true, "Place is invalid", "red-alert"))
        else if(!this.state.BeginDate.isValid)
            this.props.dispatch(showAlert(true, "Begin date is invalid", "red-alert"))
        else if(!this.state.EndDate.isValid)
            this.props.dispatch(showAlert(true, "End date is invalid", "red-alert"))
        else if(!this.state.Hour.isValid)
            this.props.dispatch(showAlert(true, "Starting hour is invalid", "red-alert"))
        else if(!this.state.MinDateBirth.isValid)
            this.props.dispatch(showAlert(true, "Age is invalid", "red-alert"))
        else if(!this.state.NumberOfTickets.isValid)
            this.props.dispatch(showAlert(true, "Number of tickets is invalid", "red-alert"))
        else if(!this.state.CategoryId.isValid)
            this.props.dispatch(showAlert(true, "Category is invalid", "red-alert"))
        else if(!this.state.PictureURL.isValid)
            this.props.dispatch(showAlert(true, "Picture url is invalid", "red-alert"))
        else
        {
            EventServices.createAnEvent(this);
            this.props.dispatch(showAlert(true, "Event was succesfully created", "green-alert"))
        }  
    }

    onFacebookImport(){
        if(localStorage.getItem("fbauth")){
            // var fbtoken = localStorage.getItem("fbauth");
            // EventFbServices.GetEvent(this,"342484026125736",String(fbtoken));
            this.props.dispatch(showAlert(true,"This feature is not yet available.","green-alert"))
        } else {
              this.props.dispatch(showAlert(true,"In order to import from facebook, please log in with your facebook account. This feature is still in progress","green-alert"))
        }
    }

    render() {
        
        return (
            <div className="bord">
                <div className="full">
                    <p className="scris">Create an event</p>
                </div>
                <div className="col-md-6 pads">
                    <div className="create-event-input-margin">
                        <Input_place
                            placeholder="Name Event"
                            type="text"
                            error={this.state.Name.isValid ? "" : "login_error"}
                            glyph="glyphicon-pencil"
                            data={this
                            .dataBind
                            .bind(this, "Name")}/>
                    </div>
                    <DescriptionEvent
                        error={this.state.Description.isValid ? "" : "login_error"}
                        data={this
                        .dataBind
                        .bind(this, "Description")}/>
                    <div className="create-event-input-margin">
                        <Input_place
                            placeholder="Place event"
                            type="text"
                            error={this.state.Place.isValid ? "" : "login_error"}
                            glyph="glyphicon-map-marker"
                            data={this
                            .dataBind
                            .bind(this, "Place")}/>
                    </div>
                    <div className="create-event-input-margin">
                        <p className="placeholder-font-nomargin">Begin Date</p>
                        <Date_picker
                            placeholder="BeginDate"
                            type="text"
                            error={this.state.BeginDate.isValid ? "" : "login_error"}
                            glyph="glyphicon-calendar"
                            data={this
                            .dataBind
                            .bind(this, "BeginDate")}/>
                    </div>
                    <div className="create-event-input-margin">
                        <p className="placeholder-font-nomargin">End Date</p>
                        <Date_picker
                            placeholder="EndDate"
                            type="text"
                            error={this.state.EndDate.isValid ? "" : "login_error"}
                            glyph="glyphicon-calendar"
                            data={this
                            .dataBind
                            .bind(this, "EndDate")}/>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="create-event-input-margin">
                        <p className="placeholder-font-nomargin">Choose starting hour</p>
                        <TimePicker
                            data={this
                            .dataBind
                            .bind(this, "Hour")}/>
                    </div>
                    <div className="create-event-input-margin input-place-event-create-width">
                        <Input_place
                            placeholder="Minimum age"
                            type="text"
                            error={this.state.MinDateBirth.isValid ? "" : "login_error"}
                            glyph="glyphicon-hourglass"
                            data={this
                            .dataBind
                            .bind(this, "MinDateBirth")}/>
                    </div>
                    <div className="create-event-input-margin input-place-event-create-width">
                        <Input_place
                            placeholder="Number of tickets"
                            type="text"
                            error={this.state.NumberOfTickets.isValid ? "" : "login_error"}
                            glyph="glyphicon-film"
                            data={this
                            .dataBind
                            .bind(this, "NumberOfTickets")}/>
                    </div>
                    <ChooseCategory
                        data={this
                        .dataBind
                        .bind(this, "CategoryId")}/>

                    <div className="create-event-input-margin input-place-event-create-width">
                        <Input_place
                            placeholder="Picture url"
                            type="text"
                            error={this.state.PictureURL.isValid ? "" : "login_error"}
                            glyph="glyphicon-picture"
                            data={this
                            .dataBind
                            .bind(this, "PictureURL")}/>
                    </div>

                    <div className="create-event-input-margin">
                        <span
                            className="button-save"
                            onClick={this
                            .onButtonClick
                            .bind(this)}>
                            Save
                        </span>
                        <span className="button-fb pull-right" onClick={this.onFacebookImport.bind(this)}>
                            <img className="fb-image" src="http://www.successacademies.org/app/themes/success-academy/assets/img/icon-facebook-white.png"/>
                            Import from facebook
                        </span>
                    </div>

                </div>
            </div>
        );
    }
}

function mapStateToProps(state : any){
    return {
        alert: state.alert
    };
}
export default connect(mapStateToProps)(CreateEvent as any);