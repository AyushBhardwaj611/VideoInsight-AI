// UploadPage: Accepts a video URL, simulates summary generation, and displays the result.
import React, { useState } from 'react';

const UploadPage = ({ setVideos }) => {
	const [url, setUrl] = useState('');
	const [title, setTitle] = useState('');
	const [loading, setLoading] = useState(false);
	const [error, setError] = useState('');
	const [summary, setSummary] = useState('');

	function extractVideoId(youtubeUrl) {
		const match = youtubeUrl.match(/[?&]v=([^&#]+)/);
		return match ? match[1] : '';
	}

	const handleSubmit = async (e) => {
		e.preventDefault();
		setError('');
		setSummary('');
		if (!url || !title) {
			setError('Both title and URL are required!');
			return;
		}
		const videoId = extractVideoId(url);
		if (!videoId) {
			setError('Invalid YouTube URL');
			return;
		}
		setLoading(true);
		try {
			// 1. Fetch transcript (expect JSON response)
			const transcriptRes = await fetch(`http://localhost:8080/api/transcript/${videoId}`);
			if (!transcriptRes.ok) throw new Error('Could not fetch transcript');
			const transcriptData = await transcriptRes.json();
			const transcript = transcriptData.transcript;

			// 2. Send transcript to backend for summary
			const summaryRes = await fetch('http://localhost:8080/api/summarizeVideo', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify({ url, title, transcript })
			});
			if (!summaryRes.ok) throw new Error('Could not generate summary');
			const data = await summaryRes.json();
			setSummary(data.summary);
			setVideos(prev => [
				{ title: data.title, url: data.url, transcript: data.transcript, summary: data.summary },
				...prev
			]);
			setUrl('');
			setTitle('');
		} catch (err) {
			setError('Could not generate summary');
		} finally {
			setLoading(false);
		}
	};

	return (
		<div>
			<h2>Upload Video & Generate Summary</h2>
			<form onSubmit={handleSubmit}>
				<div>
					<label>Video Title: </label>
					<input
						type="text"
						value={title}
						onChange={(e) => setTitle(e.target.value)}
					/>
				</div>
				<div>
					<label>Video URL: </label>
					<input
						type="text"
						value={url}
						onChange={(e) => setUrl(e.target.value)}
					/>
				</div>
				<button type="submit" disabled={loading}>Generate Summary</button>
			</form>

			{loading && (
				<div style={{ margin: '10px 0' }}>
					<span style={{
						display: 'inline-block',
						width: 18,
						height: 18,
						border: '2px solid #ccc',
						borderTop: '2px solid #333',
						borderRadius: '50%',
						animation: 'spin 1s linear infinite',
						marginRight: 8
					}} />
					<span>Loading summary...</span>
					<style>{`@keyframes spin { 0% { transform: rotate(0deg); } 100% { transform: rotate(360deg); } }`}</style>
				</div>
			)}

			{error && <div style={{ color: 'red', margin: '10px 0' }}>{error}</div>}

			{summary && (
				<div style={{ margin: '10px 0', color: '#222' }}>
					<b>Summary:</b> <span>{summary}</span>
				</div>
			)}

		</div>
	);
};

export default UploadPage;
