import { applyMiddleware, createStore } from 'redux'
import logger from 'redux-logger'
import thunk from 'redux-thunk'

const alertReducer = (state = {}, action : any) => {
    if (action.type === "showAlert") {
        state = action.payload;
    }
    return state;
}

 const middleware = applyMiddleware(thunk, logger);

export default  createStore(alertReducer, middleware);