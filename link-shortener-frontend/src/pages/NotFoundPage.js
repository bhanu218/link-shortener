import React from 'react';
import { Link } from 'react-router-dom';

const NotFoundPage = () => {
  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-red-50 text-red-700 px-4">
      <h1 className="text-4xl font-bold mb-4">🚫 404 Not Found</h1>
      <p className="text-lg mb-6 text-center">
        This short URL does not exist. It might have been deleted or never created.
      </p>
      <Link
        to="/"
        className="bg-red-600 text-white px-5 py-2 rounded-lg hover:bg-red-700 transition"
      >
        Create New Link
      </Link>
    </div>
  );
};

export default NotFoundPage;
