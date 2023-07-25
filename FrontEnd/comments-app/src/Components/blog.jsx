import React, { useState, useEffect } from 'react';
import axios from 'axios';


const Comment = ({ nombre_usuario, imagen, mensaje, puntos, answers }) => {
  return (
    <div className="flex items-center justify-center w-full">
      <div className="container px-0 mx-auto sm:px-5">
        <div className="flex-col w-full py-4 mx-auto bg-white border-b-2 border-r-2 border-gray-200 sm:px-4 sm:py-4 md:px-4 sm:rounded-lg sm:shadow-sm md:w-2/3">
          <div className="flex flex-row">
            <img className="object-cover w-12 h-12 border-2 border-gray-300 rounded-full" alt="Noob master's avatar" src={imagen}/>
            <div className="flex-col mt-1">
              <div className="flex items-center flex-1 px-4 font-bold leading-tight">
                {nombre_usuario}
              </div>
              <div className="flex-1 px-2 ml-2 text-sm font-medium leading-loose text-gray-600">
                {mensaje}
              </div>
              <button className="inline-flex items-center px-1 pt-2 ml-1 flex-column">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 32 32"><path fill="currentColor" d="M16 3C8.832 3 3 8.832 3 16s5.832 13 13 13s13-5.832 13-13S23.168 3 16 3zm0 2c6.087 0 11 4.913 11 11s-4.913 11-11 11S5 22.087 5 16S9.913 5 16 5zm-1 5v5h-5v2h5v5h2v-5h5v-2h-5v-5h-2z"/></svg>
              </button>
              <p className="inline-flex ml-1 flex-column">{puntos}</p>
              <button className="inline-flex items-center px-1 ml-1 flex-column">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 32 32"><path fill="currentColor" d="M16 3C8.832 3 3 8.832 3 16s5.832 13 13 13s13-5.832 13-13S23.168 3 16 3zm0 2c6.087 0 11 4.913 11 11s-4.913 11-11 11S5 22.087 5 16S9.913 5 16 5zm-6 10v2h12v-2H10z"/></svg>
              </button>
              <button className="inline-flex items-center px-1 ml-1 flex-column">
                  Responder
              </button>
            </div>
          </div>
          <hr className="my-2 ml-16 border-gray-300"></hr>
          {answers && answers.length > 0 && (
            <div className="flex flex-row pt-1 md-10 md:ml-16">
              <h3>Respuestas:</h3>
              {answers.map((answer) => (
                <Comment key={answer.id_comentario} {...answer} />
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

const BlogListView = ({ userData}) => {
    const [blogs, setBlogs] = useState([]);
    const [message, setMessage] = useState('');
    useEffect(() => {
        axios.get('')
          fetchBlogs()
      }, []);
    const fetchBlogs = () => {
        axios.get('http://localhost:8080/blog')
          .then((response) => {
            setBlogs(response.data);
          })
          .catch((error) => {
            console.error('Error al obtener los blogs:', error);
          });
    };

    
    const handlePostSubmit = (e) => {
        e.preventDefault()
        const newBlog = {
            nombre_usuario: userData.username,
            imagen: userData.image,
            mensaje: message
        };
        console.log(newBlog)
        axios.post('http://localhost:8080/blog', newBlog)
        .then((response) => {
            fetchBlogs();
            console.log('Blog agregado:', response.data);
        })
        .catch((error) => {
            console.error('Error al agregar el blog:', error);
        });
        setMessage('');
    };
  return (
    <div className='block'>
      <div className="flex-col w-full py-4 mx-auto bg-slate-300 border-b-2 border-r-2 border-gray-200 sm:px-4 sm:py-4 md:px-4 sm:rounded-lg sm:shadow-sm md:w-2/3">
        <div className="flex flex-row pb-9">
          <img className="object-cover w-12 h-12 border-2 border-gray-300 rounded-full" alt="Imagen de usuario" src={userData.image}/>
          <div className="flex-col mt-1">
            <div className="flex items-center flex-1 px-4 font-bold leading-tight">
              {userData.username}
            </div>
            <form onSubmit={handlePostSubmit}>
              <textarea 
              placeholder="Agrega tu comentario" 
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              className="p-2 focus:outline-1 focus:outline-blue-500 font-bold border-[0.1px] resize-none h-[120px] border-[#9EA5B1] rounded-md w-[60vw]"></textarea>
              <div className="flex justify-end">
                <button 
                type="submit"
                className="text-sm font-semibold absolute bg-[#4F46E5] w-fit text-white py-2 rounded px-3">
                  Comentar
                </button>
              </div>
            </form>          
          </div>
        </div>
        {blogs.map((blog) => (
          <Comment key={blog.id_comentario} {...blog} />
        ))}
      </div>
    </div>
  );
};

export default BlogListView;
