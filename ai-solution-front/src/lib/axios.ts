import Axios from "axios";

// const URL = process.env.REACT_APP_API_HOST;
const URL = "http://localhost:8080";

const axios = Axios.create({
  baseURL: URL,
});

axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    console.log("error", error);
    return Promise.reject(error.response.data);
  }
);

export default axios;
