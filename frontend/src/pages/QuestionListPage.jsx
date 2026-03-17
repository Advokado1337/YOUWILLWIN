import { useState, useEffect } from 'react';
import { fetchQuestions } from '../api/questions';
import FilterBar from '../components/FilterBar';
import QuestionCard from '../components/QuestionCard';

export default function QuestionListPage() {
  const [questions, setQuestions] = useState([]);
  const [filters, setFilters] = useState({});

  useEffect(() => {
    fetchQuestions(filters).then(setQuestions);
  }, [filters]);

  return (
    <div className="page">
      <h1>Questions</h1>
      <FilterBar onFilterChange={setFilters} />
      <div className="question-list">
        {questions.length === 0 && <p className="empty">No questions found.</p>}
        {questions.map((q) => (
          <QuestionCard key={q.id} question={q} />
        ))}
      </div>
    </div>
  );
}
