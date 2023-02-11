import React, { Fragment,useState } from 'react';
import SwipeableDrawer from "@mui/material/SwipeableDrawer";
import {Box, Button, Card, CardContent, CardMedia, IconButton, Toolbar, Typography} from "@mui/material";
import CardActions from '@mui/material/CardActions';
import { putApi } from '../../api/clientApi';
import {Alert} from "react-bootstrap";
import styled from 'styled-components';
import {notification} from "antd";
const ReservationDetail = ({updateReservations,closeDrawer, openDrawer, open,reservation}) => {


    const showNotification = (type, title, message, placement) => {
        notification[type]({
            message: title,
            description: message,
            placement: placement
        });
    }
    const confirm = () =>{
        putApi(`/reservations/${reservation.id}/confirm`)
        .then(response => {
            closeDrawer();
            updateReservations(response.data)
            showNotification("success","Reservation confirmed", "This reservation have been confirmed", "bottomRight")
        })
        .catch(error => console.log(error.message));
    }

    const cancel = () =>{
        putApi(`/reservations/${reservation.id}/cancel`)
        .then(response => {
            closeDrawer()
            updateReservations(response.data)
            showNotification("success","Reservation Cancel", "This reservation have been cancelled", "bottomRight")
        })
        .catch(error => console.log(error.message));
    }

    const returned = () =>{
        putApi(`/reservations/${reservation.id}/carReturned`)
        .then(response => {
            closeDrawer();
            updateReservations(response.data)
            showNotification("success","Reservation completed", "This reservation has been closed", "bottomRight")
        })
        .catch(error => console.log(error.message));
    }

    const picked = () =>{
        putApi(`/reservations/${reservation.id}/carPicked`)
        .then(response => {
            closeDrawer()
            updateReservations(response.data)
            showNotification("success","Car picked up", "Car has been issued to customer", "bottomRight")
        })
        .catch(error => console.log(error.message));
    }



const drawerBleeding = 50;
  return (
  <Fragment>
      <SwipeableDrawer
            container={null}
            anchor="left"
            open={open}
            onClose={closeDrawer}
            onOpen={openDrawer}
            swipeAreaWidth={drawerBleeding}
            disableSwipeToOpen={false}
            ModalProps={{
                keepMounted: true,
            }}
        >
            <Box sx={
                {padding:"2rem"}}>

                    <CardDetailed>

                        <div className='customer'>
                            <h5>
                                {reservation.user.firstName +" "+reservation.user.lastName}
                            </h5>
                            <p>Made a reservation for:</p>
                        </div>

                        <div className='car-detailed'>
                            <p>{reservation.car.name}</p>
                            <small>{reservation.car.model}</small>
                            <p>{reservation.car.year}</p>

                            <div className='date'>
                                <p>Pickup date</p>
                                <strong>{reservation.pickupDate}</strong>
                                <p>Return date</p>
                                <strong>{reservation.returnDate}</strong>
                            </div>

                        </div>
                        <div className='actions'>

                        </div>

                    </CardDetailed>


                <Card sx={{ display: 'flex' }}>
                {  (reservation.status === "CANCELLED") && <Alert variant="danger">This reservation was cancelled </Alert> }
                    
                    {  (reservation.status === "STARTED") && <CardActions>
                        <Button size="small" onClick={ confirm }>Confirm Request</Button>
                        <Button size="small" onClick={ cancel }>Cancel Request</Button>
                    </CardActions>}

                    {  (reservation.status === "CONFIRMED") && <CardActions>
                        <Button size="small" onClick={ picked }>Car Picked up</Button>
                    </CardActions>}

                    {  (reservation.status === "INPROGRESS") && <CardActions>
                        <Button size="small" onClick={ returned }>Car returned</Button>
                    </CardActions>}
                </Card>
            </Box>
        </SwipeableDrawer>
  </Fragment>
  );
};

const CardDetailed = styled.div`

`;

export default ReservationDetail;
