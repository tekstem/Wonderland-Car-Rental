import React, {Fragment, useEffect, useState} from 'react';
import {Content} from "antd/es/layout/layout";
import {Breadcrumb, Button, Empty, Popconfirm, Spin, Table, Tag,Badge} from "antd";
import {getApi} from "../../api/clientApi";
import {
    LoadingOutlined, PlusCircleOutlined, UsergroupAddOutlined, ProfileTwoTone,
} from '@ant-design/icons';
import Avatar from "antd/es/avatar/avatar";


const Cars = () => {
    const [cars,setCars] = useState();
    const [fetching,setFetching] = useState(true);
    const loader = <LoadingOutlined style={{fontSize:30}} spin/>;

    useEffect(  () => {
        loadCars();
    },[]);


    const loadCars = () =>{
        getApi("/cars")
            .then(response => {
                setCars(response.data.map ( (data, index) => {
                    data.key = index+1;
                    data.index = index+1;
                    return data;
                }));
            })
            .catch(error => console.log(error.message))
            .finally( () => setFetching(false));
    }


    const TheAvatar = ({name}) => {
        if (name.length === 0){
            return <Avatar/>
        }
        return <Avatar>{`${name.charAt(0)} ${name.charAt(name.length - 1)}`}</Avatar>
    }
    function deleteCar(id) {
        return undefined;
    }



    const columns = [
        {
            title: '',
            dataIndex: 'avatar',
            key: 'avatar',
            render:() => <Avatar/>
        },
        {
            title: '#',
            dataIndex: 'index',
            key: 'index'

        },
        {
            title: 'Reg Number',
            dataIndex: 'regNo',
            key: 'regNo',
        },
        {
            title: 'Brand',
            dataIndex: 'name',
            key: 'name',
            sorter:true
        },
        {
            title: 'Model',
            dataIndex: 'model',
            key: 'model',
        },
        {
            title: 'Year',
            dataIndex: 'year',
            key: 'year',
        },
        {
            title: 'Availability',
            dataIndex: 'isReserved',
            key: 'isReserved',
            render:(text,car) => <>{!car.status ? "YES" : "NO"}</>
        }
    ];


    const renderCars = () => {
        if (fetching){
            return <Spin indicator={loader}/>
        }

        // if (cars.length <= 0){
        //     return <Empty>
        //         <Button type="primary">Add First Car</Button>
        //     </Empty>
        // }

        return <Table
            dataSource={cars}
            columns={columns}
            bordered
            title = {() =>{
                return  <>
                    <Button type="primary" htmlType="button" >
                    </Button><br/><br/>
                    <Tag>Number of Cars</Tag>
                    <Badge count={cars.length} className="site-badge-count" />
                </>
            }}
            pagination={{ pageSize:50 }}
            rowKey={ (car) => car.id }
        />
    }

    return (
        <Fragment>
            <Content style={{ padding: '0 50px' }}>
                <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item>Dashboard</Breadcrumb.Item>
                    <Breadcrumb.Item>Cars</Breadcrumb.Item>
                </Breadcrumb>
                <div className="site-layout-content">
                    {renderCars()}
                </div>
            </Content>
        </Fragment>
    );
};

export default Cars;