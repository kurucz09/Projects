import * as React from "react"

import "../login_page.less";

interface IPropsInterface {
    placeholder : string;

}

interface IStateInterface {
};

export class ExternalPlaceholder extends React.Component < IPropsInterface,
IStateInterface > {

    constructor() {
        super();

    }


    render() {
        return (
            <div className="fixed_place">
                <p>{this.props.placeholder}</p>
            </div>
        );
    }

}
