import React from 'react';
import {Route, Routes} from "react-router";
import Dashboard from "../dashboard/Dashboard";
import Home from "../client";
import Cars from "../dashboard/components/Cars";
import Reservations from '../dashboard/components/Reservations';
import AboutUs from "../client/components/aboutUs";

const RouteController = () => {
    return (
        <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="admin">
                <Route path="dashboard"  element={<Dashboard/>}>
                    <Route path="cars" element={<Cars/>}/>
                    <Route path="reservations" element={<Reservations/>}/>
                </Route>
            </Route>
            <Route path="/aboutUs" element={<AboutUs/>}/>
        </Routes>
    );
};

export default RouteController;