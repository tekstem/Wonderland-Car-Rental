import './App.css';
import {BrowserRouter as Routers} from "react-router-dom";
import RouteController from "./router/RouteController";

function App() {
  return (
      <Routers>
        <RouteController/>
      </Routers>
  );
}

export default App;
