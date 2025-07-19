import React, { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { resolveUrl } from '../services/urlService';
import { toast } from 'react-toastify';

const RedirectPage = () => {
  const { shortCode } = useParams();
  const navigate = useNavigate();

//   useEffect(() => {
//     const handleRedirect = async () => {
//       try {
//         console.log("entered into the resolve redirect method");
//         const response = await resolveUrl(shortCode);
//         const { originalUrl } = response.data;
//         console.log("Hi raa ggg",response.data);

//         if (originalUrl) {
//           // ✅ Redirect to the original URL
//           window.location.href = originalUrl;
//         }
//       } catch (error) {
//         const status = error.response?.status;

//         if (status === 404) {
//           toast.error('URL not found. Redirecting to 404...');
//           navigate('/404');
//         } else if (status === 410) {
//           toast.warning('URL has expired. Redirecting to expired page...');
//           navigate('/expired');
//         } else {
//           toast.error('Unexpected error occurred.');
//           navigate('/404');
//         }
//       }
//     };

//     handleRedirect();
//   }, [shortCode, navigate]);

useEffect(() => {
  const handleRedirect = async () => {
    try {
      const response = await resolveUrl(shortCode);
      const { originalUrl } = response.data;

      if (originalUrl) {
        window.location.href = originalUrl;
      }
    } catch (error) {
      const status = error.response?.status;

      if (status === 404) {
        toast.error('URL not found. Redirecting...');
        navigate('/404'); // 🔁 will load NotFoundPage.jsx
      } else if (status === 410) {
        toast.warning('This short link has expired.');
        navigate('/expired'); // 🔁 will load ExpiredPage.jsx
      } else {
        toast.error('Unexpected error. Redirecting...');
        navigate('/404');
      }
    }
  };

  handleRedirect();
}, [shortCode, navigate]);

  return (
    <div className="text-center mt-20 text-gray-500">
      Redirecting...
    </div>
  );
};

export default RedirectPage;
