import React, {useRef, useState,useEffect} from 'react';
import Container from "@mui/material/Container";
import {Nav, Navbar} from "react-bootstrap";
import styled from "styled-components";
import RegisterAndLoginDrawer from "./RegisterAndLoginDrawer";
import {FaCarAlt} from "react-icons/fa";
import {useSelector} from "react-redux";
import {AiOutlineUser} from "react-icons/ai";
import {Link} from "react-router-dom";

const Header = () => {
    const NavbarRef = useRef(null);
    const [open,setOpen] = useState(false);
    const [isLoggedOut,setIsLoggedOut] = useState(false);
    const {isAuth} = useSelector(state => state.login);

    const userId = localStorage.getItem('userId');
    const firstname = localStorage.getItem('firstName');
    const role = localStorage.getItem('role');

    useEffect(() => {
        if(isAuth){
            setOpen(false);
            setIsLoggedOut(false);
        }
    },[isAuth])

    const setOpenDrawer = () => {
      setOpen(true);
    }

    const setCloseDrawer = () => {
      setOpen(false);
    }

    const logout = () =>{
      localStorage.clear();
      setIsLoggedOut(true);
    }


    return (
        <HeaderContainer >
            <Navbar className="fixed-top navbar-0 bg-dark navbar-expand-lg" ref={NavbarRef}>
                    <Container>
                        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="me-auto">
                                <Navbar.Brand href="/" className="d-flex justify-content-center align-items-center gap-2">

                                    <span>
                                      <span style={{color: 'yellowgreen', fontStyle: 'italic', fontSize: '25px', fontWeight: 'bold'}}>
                                          WONDERLAND CAR RENTAL SYSTEM</span>
                                    </span>

                                </Navbar.Brand>
                            </Nav>
                            <Nav>
                                {(role === "EMPLOYEE" && <Nav.Link href='/admin/dashboard' style={{paddingLeft: '10px', paddingRight: '10px'}}>Dashboard</Nav.Link> )}
                            </Nav>
                            <Nav>
                                <Nav.Link href="#fleetSection" icon={<FaCarAlt/>}>
                                    Explore</Nav.Link>
                                {
                                (userId && !isLoggedOut)?  <Nav.Link icon={<FaCarAlt/>}
                                                                     eventKey={2}>
                                        {firstname}  | <AiOutlineUser title='logout'
                                                                      style={{ fontWeight:"bold"}}
                                                                      onClick={ ()=> logout() }/>
                            </Nav.Link>:
                                <Nav.Link icon={<FaCarAlt/>} eventKey={2} onClick={setOpen}>
                                Login
                                </Nav.Link>
                              }
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                <RegisterAndLoginDrawer NavbarRef={NavbarRef.current} openDrawer={setOpenDrawer}
                                        closeDrawer={setCloseDrawer} open={open}/>
                </Navbar>
        </HeaderContainer>
    );
};


const HeaderContainer = styled.div`
   .navbar-0{     
     a.navbar-brand{
       color: #fff;
       font-size: 2rem;
       opacity: .8;
       text-transform: uppercase;
     }
     
     .navbar-nav a{
       color: #fff;
       opacity: .8;
       text-transform: uppercase;       
       &:hover, &:focus, &:active{
         color: #f1f1f1;
       }
       
       &:before{
         content: ${props => props.icon};
       }
       
       &.active::before{
         content: ${props => props.icon};
       }
       
     }
   }
`;

export default Header;