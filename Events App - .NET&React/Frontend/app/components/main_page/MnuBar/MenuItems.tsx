import * as React from "react"
import { Link } from 'react-router-dom';
import {Item} from './Item'


interface ItemProps {
    items : Array < Item >;
}

export class DropDown extends React.Component < ItemProps, {} > {

    constructor() {
        super();

    }

    render() {

        const listItems2 = this
            .props
            .items
            .map((Item, i) => {
                return (
                    <li key={i} className="list-items dropdown-item">
                        <Link to={Item.reference} className="dropdown-a">
                            {Item.name}
                        </Link>
                    </li>
                );
            })

        return (
            <ul className="dropdown-menu dropdown-style">
                {listItems2}
            </ul>

        );
    }

}
