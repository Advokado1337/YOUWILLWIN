import { useState, useEffect } from 'react';
import { fetchTags } from '../api/questions';

export default function FilterBar({ onFilterChange }) {
  const [tags, setTags] = useState([]);
  const [difficulty, setDifficulty] = useState('');
  const [tag, setTag] = useState('');

  useEffect(() => {
    fetchTags().then(setTags);
  }, []);

  const handleDifficultyChange = (e) => {
    setDifficulty(e.target.value);
    onFilterChange({ difficulty: e.target.value, tag });
  };

  const handleTagChange = (e) => {
    setTag(e.target.value);
    onFilterChange({ difficulty, tag: e.target.value });
  };

  return (
    <div className="filter-bar">
      <select value={difficulty} onChange={handleDifficultyChange}>
        <option value="">All Difficulties</option>
        <option value="EASY">Easy</option>
        <option value="MEDIUM">Medium</option>
        <option value="HARD">Hard</option>
      </select>

      <select value={tag} onChange={handleTagChange}>
        <option value="">All Tags</option>
        {tags.map((t) => (
          <option key={t.id} value={t.name}>
            {t.name}
          </option>
        ))}
      </select>
    </div>
  );
}
