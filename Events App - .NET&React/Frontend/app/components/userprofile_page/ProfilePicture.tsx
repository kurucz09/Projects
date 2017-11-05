import * as React from 'react';
import {PicChange} from "./PicureProfileComponents/PicChange";
import {UserServices} from "../../Services/UserServices/UserServices";
import { Validator } from '../../Services/Validation/Validator';
import { connect } from 'react-redux';
import { showAlert, hideAlert } from '../../Store/Actions';

interface IUserProps {
   userData : any;
   fbLogged: boolean;
}

interface IUserState {
    img: any;
    showPic: boolean;
    change: boolean;
}

class ProfilePicture extends React.Component<any, IUserState>{
    constructor() {
        super();
        this.state = {
            img: "",
            showPic: false,
            change: false
        }
    }

   
    onChangePic(){
        {this.setState({showPic: !this.state.showPic})}
    }

    onChangePicture(url : any){
        if (Validator.validatePictureUrl(url) == true) {
            this.props.dispatch(hideAlert());
            UserServices.tryToChangePicture(this, url);
        } else {
            this.props.dispatch(showAlert(true, "Wrong picture format. Please upload a valid picture.", "red-alert"));
        }
    }

    render() {
        return (
            <div className="col-md-12" style={{ textAlign: "center" }}>
                
                {this.props.fbLogged
                ? 
                    <div>
                        <img src={this.props.userData.picture} className="profile_image" />
                        
                    </div>
                :
                    <div>
                        <img src={this.state.img ? this.state.img : this.props.userData.PictureURL} className="profile_image" />
                        <div className="picture-button-border">
                            <span className="button-profile" onClick={this.onChangePic.bind(this)}>
                                Change picture 
                            </span>
                        </div>
                        {this.state.showPic ? <PicChange user={this.props.userData} changePicture={this.onChangePicture.bind(this)}/> : null}  
                    </div>
                }
                          
            </div>
        );
    }
}


function mapStateToProps(state : any) {
    return {alert: state.alert};
}
export default connect(mapStateToProps)(ProfilePicture as any);