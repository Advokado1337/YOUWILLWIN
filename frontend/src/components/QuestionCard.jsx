import { Link } from 'react-router-dom';

const difficultyColor = {
  EASY: '#00b8a3',
  MEDIUM: '#ffc01e',
  HARD: '#ff375f',
};

export default function QuestionCard({ question }) {
  return (
    <Link to={`/questions/${question.id}`} className="question-card">
      <div className="question-card-header">
        <span className="question-title">{question.title}</span>
        <span
          className="difficulty-badge"
          style={{ backgroundColor: difficultyColor[question.difficulty] }}
        >
          {question.difficulty}
        </span>
      </div>
      <div className="question-tags">
        {question.tags.map((tag) => (
          <span key={tag} className="tag">{tag}</span>
        ))}
      </div>
    </Link>
  );
}
