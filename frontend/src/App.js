

import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import HomePage from './pages/HomePage';
import DashboardPage from './pages/DashboardPage';
import UploadPage from './pages/UploadPage';


// App.js is the root component. It centralizes the videos state using useState.
// The videos array and setVideos function are passed as props to DashboardPage and UploadPage.
// Any add, update, or delete action in those pages uses setVideos to update the centralized state here.
function App() {
  // Centralized videos state and its setter
  // On app load, initialize videos state from localStorage if available.
  // This ensures videos persist across page reloads and browser sessions.
  const [videos, setVideos] = useState(() => {
    const saved = localStorage.getItem('videos');
    return saved ? JSON.parse(saved) : [];
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // On first load, if no videos in localStorage, fetch from API and initialize state.
  useEffect(() => {
    if (videos.length === 0) {
      setLoading(true);
      fetch('https://jsonplaceholder.typicode.com/posts')
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          return response.json();
        })
        .then((data) => {
          // Initialize with summary as empty string
          const initialVideos = data.map(item => ({
            id: item.id,
            title: item.title,
            url: '',
            summary: '',
            views: 0,
            likes: 0
          }));
          setVideos(initialVideos);
          localStorage.setItem('videos', JSON.stringify(initialVideos));
          setLoading(false);
        })
        .catch((err) => {
          setError('Failed to fetch videos.');
          setLoading(false);
        });
    }
  }, []);

  // Keep localStorage in sync with videos state.
  // Whenever videos state changes (add, delete, update), update localStorage.
  // This keeps the videos list persistent and in sync with the UI.
  useEffect(() => {
    localStorage.setItem('videos', JSON.stringify(videos));
  }, [videos]);

  // Pass videos and setVideos to child pages as props.
  return (
    <Router>
      <Navbar />
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          {/* DashboardPage and UploadPage receive videos and setVideos as props for full control */}
          <Route path="/dashboard" element={<DashboardPage videos={videos} setVideos={setVideos} loading={loading} error={error} />} />
          <Route path="/upload" element={<UploadPage setVideos={setVideos} />} />
        </Routes>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
