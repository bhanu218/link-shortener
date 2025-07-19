import React from 'react';
import { Link } from 'react-router-dom';

const ExpiredPage = () => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-yellow-50 text-yellow-800 px-4">
      <h1 className="text-4xl font-bold mb-4">⚠️ Link Expired</h1>
      <p className="text-lg mb-6 text-center">
        This short link has expired. Please create a new one.
      </p>
      <Link
        to="/"
        className="bg-yellow-600 text-white px-5 py-2 rounded-lg hover:bg-yellow-700 transition"
      >
        Go Back Home
      </Link>
    </div>
  );
};

export default ExpiredPage;
