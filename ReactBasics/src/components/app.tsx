import * as React from 'react';
import * as ReactDOM from 'react-dom';

import './App.less';

interface IStateInterface {

}

interface IPropsInterface{

}

export class App extends React.Component<IPropsInterface,IStateInterface> {

constructor(props: any) {
    super(props);
    this.state = {
     
    }
};
  
render() {
    return (
        <div className="app">
            <h1>Welcome! This is your first react page :)</h1>
        </div>
    );
  }
}
