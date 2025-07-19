import axios from 'axios';

const BASE_URL = process.env.REACT_APP_BACKEND_URL || 'http://localhost:9090/api/url';

export const shortenUrl = (originalUrl) => {
  return axios.post(`${BASE_URL}/shorten`, { originalUrl });
};

export const resolveUrl = (shortCode) => {
  return axios.get(`${BASE_URL}/${shortCode}`);
};
