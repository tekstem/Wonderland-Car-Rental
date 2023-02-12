
import React, { Fragment,useState,useEffect } from 'react';
import {Breadcrumb, Button, Table, Tag,Badge} from "antd";
import { getApi } from '../../api/clientApi';
import ReservationDetail from './ReservationDetail';
const Reservations = () => {

    const [reservations,setReservations] = useState([]);
    const [fetching,setFetching] = useState(true);
    const [selected,setSelected] = useState({});

    const [open,setOpen] = useState(false);
    const setOpenDrawer = (reservation) => {
        setSelected(reservation);
      setOpen(true);
    }

    const setCloseDrawer = () => {
      setOpen(false);
    }

    useEffect(  () => {
        loadReservations();
    },[]);


    const updateReservations = (reservation) => {
        let newReservations = reservations.filter( r  => r.id !== reservation.id );
        console.log(newReservations);
        newReservations.push(reservation);
        setReservations(newReservations);
    }


    const loadReservations = () =>{
        getApi("/reservations")
            .then(response => {
                setReservations(response.data.map ( (data, index) => {
                    data.key = index+1;
                    data.index = index+1;
                    return data;
                }));
                console.log(response.data)
            })
            .catch(error => console.log(error.message))
            .finally( () => setFetching(false));
    }

    const columns = [
    
        {
            title: '#',
            dataIndex: 'index',
            key: 'index'

        },
        {
            title: 'Pickup date',
            dataIndex: 'pickupDate',
            key: 'pickupDate',
        },
        {
            title: 'Return date',
            dataIndex: 'returnDate',
            key: 'returnDate',
        },
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status'
        },
        {
            title: 'Action',
            dataIndex: 'action',
            key: 'action',
            render:(text,reservation) => <Button onClick={
                () => setOpenDrawer(reservation)}>View Request</Button>
        }
    ];
    // debugger
  return (
      <Fragment>
          <Breadcrumb style={{ margin: '16px 0' }}>
            <Breadcrumb.Item>Dashboard</Breadcrumb.Item>
            <Breadcrumb.Item>Reservations</Breadcrumb.Item>
        </Breadcrumb>
      <Table
            dataSource={reservations}
            columns={columns}
            bordered
            title = {() =>{
                return  <>
                    <Tag>Number of Reservations</Tag>
                    <Badge count={reservations.length} className="site-badge-count" />
                </>
            }}
            pagination={{ pageSize:50 }}
            rowKey={ (reservation) => reservation.id }
        />
       {Object.keys(selected).length > 0 && <ReservationDetail
           updateReservations={updateReservations} closeDrawer={setCloseDrawer}
           openDrawer={setOpenDrawer} open={open}  reservation={selected}/>}
  </Fragment>
  )
  


};
export default Reservations;
