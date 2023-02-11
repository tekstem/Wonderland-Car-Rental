import React from 'react';
import TemplateLayout from "./template/TemplateLayout";
import {useLocation} from "react-router-dom";
import Cars from "./components/Cars";
import { CardTabs } from './components/CardTabs';import Reservations from './components/Reservations';


const Dashboard = () => {
    const location = useLocation();
    return (
        <TemplateLayout>
            { (location.pathname === '/admin/dashboard') && <CardTabs/>}
            { (location.pathname === '/admin/dashboard/cars') && <Cars/>}
            { (location.pathname === '/admin/dashboard/reservations') && <Reservations/>}
        </TemplateLayout>);
};

export default Dashboard;