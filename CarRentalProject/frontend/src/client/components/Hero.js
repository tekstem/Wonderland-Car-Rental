import React from 'react';
import styled from "styled-components";
import car from "../assets/car1.gif";
import Form from "./Form";

const Hero = ({onSearchCars}) => {
    return (
        <HeroSection>
            <Form onSearchCars={onSearchCars}/>
            <img src={car} alt="Car"/>
        </HeroSection>
    );
};


const  HeroSection = styled.div`
  background-image: url();
  background-size: cover;
  border-radius: 1rem;
  height: 75vh;
  position: relative;

  &::before{
    position: absolute;
    content: "";
    width: 50%;
    height: 100%;
    background: rgba(0,0,0,0);
    top: 0;
    left: 0;
    border-radius: 1rem;
  }

  img{
    width: 100%;
    height: 150%;
    position: center;
    border-radius: 0.5rem;
  }
`;

export default Hero;
