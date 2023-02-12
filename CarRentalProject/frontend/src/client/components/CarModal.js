import React, { useEffect } from 'react';
import Modal from "antd/es/modal/Modal";
import {IoPeopleCircleOutline} from "react-icons/io5";
import {GiGymBag} from "react-icons/gi";
import {DatePicker, Form, Input, notification} from "antd";
import {postApi} from "../../api/clientApi";
const { RangePicker } = DatePicker;


const CarModal = ({isModalVisible,handleCancel, title,img, description,carId  }) => {
    const [form] = Form.useForm();

    const customerId = localStorage.getItem("userId");

    useEffect(()=>{
        form.setFieldsValue({
            "carId":carId,
        })
        form.setFieldsValue({
            "customerId":customerId,
        })
    },[])

    const showNotification = (type,title,message, placement) => {
        notification[type]({
            message:title,
            description:message,
            placement: placement
        });
    }

    const onFinish = async (values) => {

        const pickupDate = values.reserve_date[0];
        const returnDate = values.reserve_date[1];
        const costumerId = localStorage.getItem("userId");
        const data = {
            "pickupDate": pickupDate,
            "returnDate": returnDate,
            "customerId": costumerId,
            "carId":values.carId
        }
        try {
            postApi("/reservations",data).then(response => {
                
                if(response?.response?.status === 400){
                    showNotification("warning","Attention!",response?.response?.data?.error?.message, "bottomRight");
                }else{
                    showNotification("success","your reservation was successful","The vehicle will be available for pickup", "bottomRight");

                }
               
            }).catch( error => console.log(error))

        } catch (e) {
            console.log(e);
        }

    }

    const onFinishFailed = (errorInfo) => {
        console.log('Failed:', errorInfo);
    };

    return (
            <Modal
                className="car-modal" title={title}
                visible={isModalVisible}
                okText="Book"
                onOk={form.submit}
                onCancel={handleCancel}
            >

                <div className="carDetails">
                    <div className="car">
                        <img src={img} alt="car" style={{ width:"100%" }}/>
                    </div>
                    <div className="content">
                        <ul className="specification">
                            <li> <IoPeopleCircleOutline/> 5 people </li>
                            <li> <GiGymBag/> 2 bag </li>
                        </ul>
                        <p className="description">{description}</p>
                    </div>
                </div>
                <div className="request_date" style={{ padding:"1rem",display:"flex" , flexDirection:"column", justifyContent:"center",
                                                       backgroundColor:"rgba(123,179,234,0.22)"}}>
                    <h6>Please select pickup and return dates for this vehicle</h6>
                    <Form layout="vertical"
                          hideRequiredMark
                          onFinish={onFinish}
                          onFinishFailed={onFinishFailed}
                          form={form}
                    >
                        <Form.Item name="customerId" hidden={true}>
                            <Input value="1"/>
                        </Form.Item>
                        <Form.Item name="carId" hidden={true}>
                            <Input value="1"/>
                        </Form.Item>
                        <Form.Item name="reserve_date">
                            <RangePicker style={{ width: "100%" }}  placeholder={["Pickup date","Return date"]} format="YYYY-MM-DD"/>
                        </Form.Item>

                    </Form>

                </div>
            </Modal>

    );
};



export default CarModal;
