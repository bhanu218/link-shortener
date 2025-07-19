import React, { useState } from 'react';
import { shortenUrl } from '../services/urlService';
import { toast } from 'react-toastify';

const HomePage = () => {
  const [originalUrl, setOriginalUrl] = useState('');
  const [shortUrl, setShortUrl] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!originalUrl.trim()) {
      toast.error('Please enter a valid URL.');
      return;
    }

    try {
      const response = await shortenUrl(originalUrl);
      setShortUrl(response.data.shortUrl);
      toast.success('Short URL created!');
    } catch (error) {
      const msg = error?.response?.data?.message || 'Failed to shorten URL';
      toast.error(msg);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-200 px-4">
      <div className="bg-white shadow-xl rounded-2xl p-8 w-full max-w-xl transform -translate-y-16">
        <h1 className="text-3xl font-bold mb-6 text-center text-blue-700">🔗 URL Shortener</h1>
        
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            placeholder="Enter your long URL"
            value={originalUrl}
            onChange={(e) => setOriginalUrl(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-gray-500"
          />
            <div className="flex justify-center">
                <button
                    type="submit"
                    className="w-40 bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-800 transition"
                    >
                    Shorten
                </button>
            </div>
          
        </form>

        {shortUrl && (
          <div className="mt-6 text-center">
            <p className="text-md text-gray-600 mb-1 font-">Your short URL:</p>
            <a
              href={shortUrl}
              className="text-blue-600 underline text-lg font-medium break-words"
              target="_blank"
              rel="noopener noreferrer"
            >
              {shortUrl}
            </a>
          </div>
        )}
      </div>
    </div>
  );
};

export default HomePage;
