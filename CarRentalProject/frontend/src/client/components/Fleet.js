import React, {useState} from 'react';
import styled from "styled-components";
import {IoPeopleCircleOutline} from "react-icons/io5";
import {GiAutoRepair, GiGymBag} from "react-icons/gi";
import CarModal from "./CarModal";

import {notification} from "antd";

const Fleet = ({ img, brand, model, description , carId,reserveStatus}) => {
    const [isModalVisible, setIsModalVisible] = useState(false);

    const showNotification = (type, title, message, placement) => {
        notification[type]({
            message: title,
            description: message,
            placement: placement
        });
    }

    const showModal = () => {
        if(localStorage.getItem("token") && localStorage.getItem("role") === "CUSTOMER" ) {
            setIsModalVisible(true);
        } else {
            showNotification("warning", "Warning", "Sorry you need to login as customer to make reservation.", "bottomRight");
        }
    };

    const handleOk = () => {
        setIsModalVisible(false);
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    return (
        <FleetCard>
            <div className="card">
                <div className="car">
                    <img src={img} alt={ brand+" "+model }/>
                </div>
                <div className="content">
                    <h3 className="title">{brand+" "+model}</h3>
                    <p className="description">{description}</p>
                    <ul className="specification">
                        <li> <IoPeopleCircleOutline/> 4 people </li>
                        <li> <GiGymBag/> 3 bag </li>
                        {/*<li> <GiAutoRepair/> automatic </li>*/}
                    </ul>
                    { (reserveStatus) ? <button  className="reserved-button"  disabled={reserveStatus}>
                         Booked
                    </button>: <button className="select-button" style={{backgroundColor: 'red'}} onClick={showModal} disabled={reserveStatus}>
                        Book Now
                    </button> }
                    
                </div>
            </div>
            <CarModal description={description} handleCancel={handleCancel} carId={carId} handleOk={handleOk} title={brand+" "+model} img={img} isModalVisible={isModalVisible}/>
        </FleetCard>
    );
};

const FleetCard = styled.div`
    position: relative;
    flex: 0 0 30.333333%;
    margin-bottom: 7.2rem;
    margin-top: 1rem;
  
  .card{
    background-color: #F2F2F2;
    padding: 1px;
    position: relative;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    .car{
      img{
        position: absolute;
        width: 100%;
        top: -8rem;
      }
      
    }
    
    .content{
      display: flex;
      flex-direction: column;
      gap: 1rem;
      padding: 2rem;
      
      .title{
        color: #444444;
        font-size: 2rem;
        font-weight: 500;
        text-align: center;
        
      }
      
      .select-button{
        border: 0;
        outline: none;
        padding: 0.4rem;
        color:#fff;
        background-color: #032a62;
      }

      .reserved-button{
        border: 0;
        outline: none;
        padding: 0.4rem;
        background-color: #747474;
      }
      
      .specification{
        display: flex;
        justify-content: space-between;
        list-style-type: none;
        
      }
      
    }
    
    
  }
`;
export default Fleet;
