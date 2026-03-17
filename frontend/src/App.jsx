import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import QuestionListPage from './pages/QuestionListPage';
import QuestionDetailPage from './pages/QuestionDetailPage';
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <nav className="navbar">
        <Link to="/" className="nav-brand">YOU WILL WIN</Link>
      </nav>
      <Routes>
        <Route path="/" element={<QuestionListPage />} />
        <Route path="/questions/:id" element={<QuestionDetailPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
