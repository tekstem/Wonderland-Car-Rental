import {configureStore} from "@reduxjs/toolkit";
import loginReducer from "./reducers/LoginSlice";
const store = configureStore({
    reducer:{
        login:loginReducer
    }
})

export default store;