import React, { useState } from 'react';

const UserForm = ({ onSubmit }) => {
  const [username, setUsername] = useState('');
  const [image, setImage] = useState('https://www.muylinux.com/wp-content/uploads/2009/06/tux.jpg');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ username, image });
    setUsername('');
    setImage('https://www.muylinux.com/wp-content/uploads/2009/06/tux.jpg');
  };

  return (
    <div className="w-full max-w-xs">
      <form onSubmit={handleSubmit} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
          <div className="mb-4">
          <label className="block text-gray-700 text-sm font-bold mb-2">
              Nombre de Usuario
          </label>
          <input
          className="shadow  appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          />
      </div>
      <div>
        <label>Imagen:</label>
        <input
          className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          type="text"
          value={image}
          onChange={(e) => setImage(e.target.value)}
        />
    </div>
    <button
      className="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800"
      type="submit">Enviar</button>
  </form>
  </div>
  );
};

export default UserForm;
