import * as React from "react"
import "./Main.less"

class PhotoProps {
    srcP : any;
    catName : string;
}
export class Photo extends React.Component < PhotoProps, {} > {

    constructor() {
        super();

    }

    render() {

        return (
            <div className="pictures">
              
                <div className="pic">
                    <img id="img" src={this.props.srcP} alt="."/>
                </div>
            </div>
        );
    }

}