import React, {useRef} from 'react';
import Container from "@mui/material/Container";
import {Nav, Navbar} from "react-bootstrap";
import {Link} from "react-router-dom";

import styled from "styled-components";


const DashboardHeader = () => {
    const NavbarRef = useRef(null);
    return (

        <HeaderContainer >
            <Navbar className="fixed-top navbar-0 bg-dark navbar-expand-lg" ref={NavbarRef}>
                    <Container>
                        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="me-auto">
                                <Navbar.Brand className="d-flex justify-content-center align-items-center gap-2">
                                    <Link to="/" style={{textDecoration: 'none'}}>
                                    <span>
                                      <span style={{color: 'yellow', fontStyle: 'italic', fontSize: '25px', fontWeight: 'bold'}}>
                                          WONDERLAND CAR RENTAL SYSTEM </span>&nbsp;&nbsp;&nbsp;&nbsp;<span style={{fontSize: '18px', color: 'white', fontWeight: 'bold'}}>System</span>
                                    </span>
                                    </Link>
                                </Navbar.Brand>
                            </Nav>
                            <Nav style={{paddingRight: '20px'}}>
                                <Link to="/admin/dashboard/cars" style={{textDecoration: 'none'}}>
                                Cars
                                </Link>
                            </Nav>
                            <Nav>
                                <Link to="/admin/dashboard/reservations" style={{textDecoration: 'none'}}>
                                    Reservations
                                </Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
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
     }
   }
`;




export default DashboardHeader;