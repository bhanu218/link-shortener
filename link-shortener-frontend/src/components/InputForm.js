import React, { useState } from 'react';
import { toast } from 'react-toastify';
import { shortenUrl } from '../services/urlService';

export default function InputForm({ onShortened }) {
  const [url, setUrl] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!url.trim()) {
      toast.error("URL cannot be empty");
      return;
    }

    try {
      const response = await shortenUrl(url);
      onShortened(response.data.shortUrl);
    } catch (error) {
      toast.error("Failed to shorten URL");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="w-full max-w-md mx-auto p-4 flex flex-col gap-4">
      <input
        type="text"
        placeholder="Enter your URL here"
        className="border rounded-xl p-3 shadow"
        value={url}
        onChange={(e) => setUrl(e.target.value)}
      />
      <button className="bg-blue-600 text-white px-4 py-2 rounded-xl hover:bg-blue-700 transition">
        Shorten
      </button>
    </form>
  );
}
