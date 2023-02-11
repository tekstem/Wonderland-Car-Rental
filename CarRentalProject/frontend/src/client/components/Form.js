import React, {useEffect, useState} from 'react';
import styled from "styled-components";
import {getApi} from "../../api/clientApi";

const Form = ({onSearchCars}) => {
    const [open, setOpen] = useState(false);
    const [brands, setBrands] = useState([]);
    const [selectedBrand, setSelectedBrand] = useState('');
    const [selectedModel, setSelectedModel] = useState('');

    useEffect( () => {
        loadBrandsAndModel();
    },[])

    const loadBrandsAndModel = () => {
        getApi("/brands").then( response => {
            setBrands(response.data)
        }).catch( error => {
            console.log(error);
        });
    }

    const searchCars = () => {
        onSearchCars(selectedModel, selectedBrand);
    }

    return (
        <FormContainer>

            <div style={{position:'absolute', top: '111%', left:'5%'}}>
                <h2 className="text-uppercase text-center" style={{fontSize: '25px', color:'white'}}>Have Your Fantacy Come True
                   <p><span style={{color: 'white', fontStyle: 'italic', fontSize: '50px'}}>
                       WELCOME TO WONDERLAND  CAR RENTAL SYSTEM SYSTEM</span></p> </h2>
            </div>
        </FormContainer>
    );
};


const FormContainer = styled.div`
    
      
  
    .search{
      position: absolute;
      top: 8rem;
      left: 25%;
      width: 400px;
      display: flex;
      background-color: #ffffffce;
      padding: 0.5rem;
      border-radius:0.5rem;
      gap: 1rem;
      
      .search-form{
        display: flex;
        gap: 0.7rem;
        justify-content: space-between;
        .form-fields{
          display: flex;
          flex-direction: column;
          gap: 0.5rem;
          
          input,select{
            border: 0.5px solid #ddd;
            background-color: #fff;
            padding: 0.5rem;
            width: 100%;
            
            &:focus{
              outline: none;
            }
          }
          
          .brand-models{
            display: flex;
            gap: 0.5rem;
          }
        }
      }
      
      .submit-button{
        button{
          padding: .5rem;
          border: 0;
          outline: none;
          background-color: #032a62;;
          color: #fff;
          font-size: 1rem;
          font-weight: 400;
        }
        
      }
    }
`;

export default Form;
