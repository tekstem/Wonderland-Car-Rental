import React, { Fragment } from 'react';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import {Box, Button,CardContent } from "@mui/material";

const Item = styled(Paper)(({ theme }) => ({
  ...theme.typography.body2,
  textAlign: 'center',
  color: theme.palette.text.secondary,
  height: 300,
  lineHeight: '60px',
  padding:'1rem'
}));

export const CardTabs = () => {



    return (
    <Fragment>
         <Grid container spacing={2} style={{ display:"flex", justifyContent:"center", alignItems:"center",height:"100vh" }}>
            <Grid item xs={6}>
                <Item  elevation={1}>
                    <Box sx={{ display: 'flex', flexDirection: 'column' }}>
                        <CardContent sx={{ flex: '1 0 auto' }}>
                            <Button href="/admin/dashboard/cars">View All cars</Button>
                            <Button href="/admin/dashboard/reservations">View All Reservations</Button>
                        </CardContent>
                    </Box>
                </Item>
            </Grid>
         </Grid>
    </Fragment>
    );
};
