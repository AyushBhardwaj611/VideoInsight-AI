// Purpose: DashboardPage displays all videos, allows searching, updating (views/likes), and deleting videos.
// State: Receives the videos array and setVideos function from App.js (no local video state is kept).
// Data Flow: All changes (add, update, delete) use setVideos, which updates App.js state and triggers a sync to localStorage.
// Interactions: When a user adds a view/like or deletes a video, setVideos updates the state in App.js, which is then synced to localStorage via useEffect in App.js. The UI always reflects the latest state.


// DashboardPage: Displays all videos and allows searching, updating (views/likes), and deleting.
// State: Receives the videos array and setVideos function from App.js (no local video state).
// Data Flow: All changes (add, update, delete) use setVideos, which updates App.js state and triggers a sync to localStorage.
// Interactions: When a user adds a view/like or deletes a video, setVideos updates the state in App.js, which is then synced to localStorage via useEffect in App.js. The UI always reflects the latest state.
import React, { useState } from 'react';

const DashboardPage = ({ videos, setVideos, loading, error }) => {
	const [search, setSearch] = useState('');

	// Increment views for a video by id
	const handleAddView = (id) => {
		setVideos(prev => prev.map(v => v.id === id ? { ...v, views: (v.views || 0) + 1 } : v));
	};

	// Increment likes for a video by id
	const handleAddLike = (id) => {
		setVideos(prev => prev.map(v => v.id === id ? { ...v, likes: (v.likes || 0) + 1 } : v));
	};

	// Delete a video by id
	const handleDelete = (id) => {
		setVideos(prev => prev.filter(v => v.id !== id));
	};

	// Filter videos by search string (local state)
	const filteredVideos = videos.filter(video =>
		video.title.toLowerCase().includes(search.toLowerCase())
	);

	if (loading) return <div>Loading...</div>;
	if (error) return <div>{error}</div>;

		return (
			<div>
				<h2>Videos</h2>
				<div>
					<input
						type="text"
						placeholder="Search by title..."
						value={search}
						onChange={e => setSearch(e.target.value)}
					/>
				</div>
				{filteredVideos.length === 0 ? (
					<div>No videos uploaded yet</div>
				) : (
					<ul>
						{filteredVideos.map((video) => (
							<li key={video.id}>
								<strong>{video.title}</strong>
								<div>URL: {video.url}</div>
								<div><b>Transcript:</b> <pre style={{whiteSpace: 'pre-wrap'}}>{video.transcript || 'No transcript available.'}</pre></div>
								<div><b>Summary:</b> <em>{video.summary || 'No summary available.'}</em></div>
								<div>Views: {video.views}</div>
								<button onClick={() => handleAddView(video.id)}>Add View</button>
								<div>Likes: {video.likes}</div>
								<button onClick={() => handleAddLike(video.id)}>Add Like</button>
								<button onClick={() => handleDelete(video.id)}>Delete</button>
							</li>
						))}
					</ul>
				)}
			</div>
		);
};

export default DashboardPage;
