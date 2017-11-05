import * as React from "react"

import "../login_page.less";

import {ExternalPlaceholder} from "../Components/ExternalPlaceholder"
import * as moment from 'moment';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

interface IPropsInterface {
    placeholder : string;
    type : string;
    error : string;
    glyph : string;
    data : any
}

interface IStateInterface {
    startDate : any;
    isVisible : boolean;
};

export class Date_picker extends React.Component < IPropsInterface,
IStateInterface > {

    constructor() {
        super();
        this.state = {
            startDate: moment(),
            isVisible: false
        };
        this.handleChange = this
            .handleChange
            .bind(this);
    }

    handleChange(date : moment.Moment) {
        this.setState({startDate: date});
        const formatedDate = date.format("DD/MM/YYYY");
        this.props.data(formatedDate);
    }

    render() {
        return (
            <div className="login_cluster">
                {this.state.isVisible && <ExternalPlaceholder placeholder={this.props.placeholder}/>}
                <div className={`input-group login_group  ${this.props.error}`}>
                    <span className={`input-group-addon input-personal glyphicon ${this.props.glyph}`}></span>
                    <DatePicker
                        className={`form-control form-styled login_input `}
                        selected={this.state.startDate}
                        onChange={this.handleChange}
                        fixedHeight
                        disabledKeyboardNavigation
                        isClearable={true}
                        showMonthDropdown
                        showYearDropdown
                        dropdownMode="select"
                        withPortal/>
                </div>
            </div>

        );
    }

}
