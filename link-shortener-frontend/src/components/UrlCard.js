import React from 'react';

const UrlCard = ({ urlData }) => {
  return (
    <div className="mt-6 p-4 bg-green-100 rounded-xl text-center">
      <p className="text-sm">Original URL:</p>
      <p className="text-blue-600 underline">{urlData.originalUrl}</p>
      <p className="mt-2 text-sm">Shortened:</p>
      <a
        href={urlData.shortUrl}
        target="_blank"
        rel="noopener noreferrer"
        className="text-green-700 font-bold"
      >
        {urlData.shortUrl}
      </a>
    </div>
  );
};

export default UrlCard;
