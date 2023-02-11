import React, {useEffect, useState} from 'react';
import Layout from "./template/Layout";
import Hero from "./components/Hero";
import Fleets from "./components/Fleets";
import {getApi} from "../api/clientApi";

const Home = () => {
  const [cars, setCars] = useState([]);
  const [fetching, setFetching] = useState(true);
  const [filteredCars, setFilteredCars] = useState([]);

  const filterCarHandler = (modelName, brandName) => {
    let carsFiltered = cars.filter(
      car => car.brand.name.includes(brandName) && car.model.includes(modelName)
    );
    setFilteredCars(carsFiltered);
    var scrollDiv = document.getElementById("fleetSection").offsetTop;
    window.scrollTo({ top: scrollDiv, behavior: 'smooth'});
  }

  useEffect(() => {
    getApi("/cars")
      .then(response => {
        setCars(response.data);
        setFilteredCars(response.data);
      })
      .catch(err => console.log(err.message))
      .finally(() => {
        setFetching(false);
      })
  }, []);

  return (
    <Layout>
      <Hero onSearchCars={filterCarHandler}/>
      <Fleets cars={filteredCars} fetching={fetching}/>
    </Layout>
  );
};
export default Home;
