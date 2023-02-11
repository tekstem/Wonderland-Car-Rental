import React, {Fragment} from 'react';
import Layout , {Content, Header} from "antd/es/layout/layout";
import DashboardHeader from "../components/DashboardHeader";
import Footer from "../../client/components/Footer";

const TemplateLayout = ({children}) => {


    const role = localStorage.getItem('role');


    return (

        <Fragment>
            {(role == "EMPLOYEE" &&
            <Layout className="layout" style={{ minHeight: '100vh' }}>
                <DashboardHeader />
                <Content style={{ padding: '0 50px' }}>
                    {children}
                </Content>
                <Footer />
            </Layout>  )}
        </Fragment>
    );
};

export default TemplateLayout;