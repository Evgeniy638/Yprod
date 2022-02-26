import {applyMiddleware, combineReducers, createStore} from "redux";
import thunk from "redux-thunk";
import initReducer from "./reducers/reducer";

const reducer = combineReducers({
    initReducer
});

const store = createStore(reducer, applyMiddleware(thunk));

export default store;