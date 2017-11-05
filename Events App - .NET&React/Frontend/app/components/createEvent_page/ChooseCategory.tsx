import * as React from 'react';
import '../../less/crtevent.less'
import * as Select from 'react-select';
import {CategoryServices} from "../../Services/EventsServices/Categories";

interface ICategsState {
    loaded : boolean;
    options : any;
    selectedValue : any;
    categs : {
        Id: any,
        Name: any,
        PictureURL: any
    }[];
}

interface ICategsProps {
    data : any;
}

export class ChooseCategory extends React.Component < ICategsProps,
ICategsState > {

    chooseCateg(selectedOption : any) {
        this
            .props
            .data(selectedOption.value);
        this.setState({selectedValue: selectedOption.value});
    }

    constructor() {
        super();
        this.state = {
            loaded: false,
            options: [],
            selectedValue: null,
            categs: []
        }
    }

    componentDidMount() {
        CategoryServices.getCategoriesForDropDown(this, "categs");
            
        
    }

    addCategories(){
        if (this.state.options.length === 0) {
            this
                .state
                .categs
                .map((category, index) => ({id: index, value: category}));
            let opt : any = [];
            this
                .state
                .categs
                .map((category) => opt.push({label: category.Name, value: category.Id}));

            this.setState({options: opt});
        }
        else
            this.setState({loaded: true});
    }

   /* componentWillUpdate() {
        if (this.state.loaded === false)
            this.addCategories();
    }*/ 

    render() {
        return (
            <div className="create-event-input-margin">
                <p className="placeholder-font-nomargin">Choose category</p>
                <div className="linie">
                    <label
                        htmlFor="program"
                        className="control-label input-group-addon input-personal add-ons"><span className="glyphicon glyphicon-filter" aria-hidden="true"/></label>

                    <Select
                        className="opt-select"
                        name="form-field-name"
                        value={this.state.selectedValue}
                        options={this.state.options}
                        onChange={this
                        .chooseCateg
                        .bind(this)}
                        clearable={false}/>
                </div>
            </div>
        );
    }
}