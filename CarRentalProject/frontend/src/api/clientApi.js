import axios from 'axios';
axios.interceptors.request.use(function (config) {
    config.headers = {
        'Access-Control-Allow-Origin': '*',
        'Content-Type':'application/json'
    }
    if(!config.url.includes("/auth") && !config.url.includes("/brands")  && !config.url.includes("/cars") ){
        config.headers['Authorization'] = `Bearer ${localStorage.getItem("token")}`;
    }
    return config;
}, function (error) {
    return Promise.reject(error);
});




export const getApi = endpoint => {
    return  axios.get(endpoint)
        .then( response => {return  response})
        .catch(error => {return error});
}
export const postApi = (endpoint, data) => {
    return  axios.post(endpoint,data)
        .then(response => {return response })
        .catch(error => {return error});
}

export const deleteApi = endpoint => {
    return  axios.delete(endpoint)
        .then(response => { return response})
        .catch(error => { return error });
}

export  const putApi = (endpoint) => {
    return  axios.put(endpoint)
        .then(response => { return response})
        .catch(error => { return error })
}