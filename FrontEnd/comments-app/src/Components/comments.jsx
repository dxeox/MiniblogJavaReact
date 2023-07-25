import React, { useState} from 'react';
import UserForm from './UserFrom';
import BlogListView from './blog';

const BlogList = () => {
  const [userData, setUserData] = useState(null);
  const [showBlogList, setShowBlogList] = useState(false);


  const handleUserSubmit = (data) => {
    setUserData(data);
    setShowBlogList(true);
  };

  return (
    <div>
      {!showBlogList ? (
        <UserForm onSubmit={handleUserSubmit} />
      ) : (
        <BlogListView userData={userData} />
      )}
    </div>
  );
};

export default BlogList;
