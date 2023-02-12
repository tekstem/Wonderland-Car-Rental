import React, {useEffect, useRef, useState} from 'react';
import SwipeableDrawer from '@mui/material/SwipeableDrawer';
import PropTypes from 'prop-types';
import {Box, IconButton, Toolbar, Typography} from "@mui/material";
import styled from "styled-components";
import {GrFormClose} from "react-icons/gr";
import LoginForm from "./LoginForm";
import {Radio} from "antd";
import RegisterForm from "./RegisterForm";

const RegisterAndLoginDrawer = ({NavbarRef, closeDrawer, openDrawer, open}) => {
    const [login,setLogin] = useState(true);
    const drawerBleeding = 20;
    

    return (
        <SwipeableDrawer
            container={NavbarRef}
            anchor="right"
            open={open}
            onClose={closeDrawer}
            onOpen={openDrawer}
            swipeAreaWidth={drawerBleeding}
            disableSwipeToOpen={false}
            ModalProps={{
                keepMounted: true,
            }}
        >
            <Toolbar variant="dense">
                <IconButton edge="start" color="primary" aria-label="menu" sx={{ mr: 2 }} onClick={closeDrawer}>
                    <GrFormClose />
                </IconButton>
                <Typography variant="h6" noWrap component="div" sx={{ flexGrow: 1 }}>
                    Create a new account
                </Typography>
            </Toolbar>
            <Box
            sx={{
                padding:10
            }}
            >
                <Box sx={{ display: "flex", justifyContent:"center", paddingTop:"1rem", paddingBottom:"1rem"}}>
                    <Radio.Group defaultValue="a" buttonStyle="solid" style={{display:"flex"}}>
                        <Radio.Button value="a"  onClick={() => setLogin(true)} style={{flex:1, width:"50%", display:"block"}}>Login</Radio.Button>
                        <Radio.Button value="b" onClick={() => setLogin(false)} style={{flex:1}}>Register</Radio.Button>
                    </Radio.Group>
                </Box>


                <Login>
                    {(login)?<LoginForm/>:<RegisterForm/>}
                </Login>

            </Box>


        </SwipeableDrawer>

    );
};


RegisterAndLoginDrawer.protoTypes = {
    NavbarRef: PropTypes.object.isRequired
}

const Login = styled.div`

`;

export default RegisterAndLoginDrawer;