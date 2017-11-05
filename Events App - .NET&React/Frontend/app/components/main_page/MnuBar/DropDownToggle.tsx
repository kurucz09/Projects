import * as React from "react"

interface DropDownToggleProps {
    name : string;
    refD : string;
}
export class DropDownToggle extends React.Component < DropDownToggleProps, {} > {

    constructor() {
        super();
    }
    render() {
        return (
            <a
                id="list-items"
                href={this.props.refD}
                className="dropdown-toggle"
                data-toggle="dropdown"
                role="button"
                aria-haspopup="true"
                aria-expanded="false">{this.props.name}
                <span className="caret"></span>
            </a>
        );
    }

}