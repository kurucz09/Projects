import * as React from "react"

import "../login_page.less";

import {ExternalPlaceholder} from "../Components/ExternalPlaceholder"
import * as ReactDOM from 'react-dom';

interface IPropsInterface {
    placeholder : string;
    glyph : string;
    error : string;
    data : any
}

interface IStateInterface {
    placeholder : string;
    isVisible : boolean;
    link : string;
    img : any;
    file : any;
};

export class Image_input extends React.Component < IPropsInterface,
IStateInterface > {

    constructor() {
        super();
        this.focusInput = this
            .focusInput
            .bind(this);
        this.unfocusInput = this
            .unfocusInput
            .bind(this);
        this.onInputChange = this
            .onInputChange
            .bind(this);
        this.state = {
            isVisible: false,
            placeholder: "",
            link: "",
            img: null,
            file: null
        };

    }

    componentDidMount() {
        this.setState({placeholder: this.props.placeholder});

    }

    focusInput() {
        this.setState({placeholder: ""});
        this.setState({isVisible: true});
    }

    unfocusInput() {
        this.setState({placeholder: this.props.placeholder});
        this.setState({isVisible: false});
    }

    onInputChange(event : React.FormEvent < HTMLSelectElement >) {
        var safeSearchTypeValue : string = event.currentTarget.files[0].name;
        this.setState({link: safeSearchTypeValue});

        let reader = new FileReader();
        let file = event.currentTarget.files[0];
        reader.onloadend = () => {
            this.setState({file: file, img: reader.result});
            this.props.data(file.name);
        }
        
        reader.readAsDataURL(file);

        
    }

    render() {
       
        return (

            <div className="login_cluster">
                 
                {this.state.isVisible && <ExternalPlaceholder placeholder={this.props.placeholder}/>}
                <div className={`input-group login_group ${this.props.error}`}>
                    <span className={`input-group-addon input-personal glyphicon ${this.props.glyph}`}></span>
                    <label className="custom-file-upload">
                        <input
                            type="file"
                            accept="image/*"
                            onChange={this
                            .onInputChange
                            .bind(this)}/>
                        <i className="glyphicon glyphicon-import pull-right margin-left"></i>
                        Add
                    </label>
                     <img className="image_small" src={this.state.img}></img>
                    <input
                        className="form-control login_input picture_label pull-right"
                        placeholder={this.state.placeholder}
                        onFocus={this.focusInput}
                        accept='.jpg'
                        onBlur={this.unfocusInput}
                        value={this.state.link}
                        disabled></input>

                </div>
            </div>

        );
    }

}
