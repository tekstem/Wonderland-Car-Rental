import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import store from "./features/store";
import {Provider} from "react-redux";
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8081";
ReactDOM.render(
  <React.StrictMode>
      <Provider store={store}>
          <App />
      </Provider>

  </React.StrictMode>,
  document.getElementById('root')
);


