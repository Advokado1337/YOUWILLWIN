import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import QuestionListPage from './pages/QuestionListPage';
import QuestionDetailPage from './pages/QuestionDetailPage';
import StatsPage from './pages/StatsPage';
import AdminPage from './pages/AdminPage';
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <nav className="navbar">
        <Link to="/" className="nav-brand">YOU WILL WIN</Link>
        <div className="nav-links">
          <Link to="/" className="nav-link">Questions</Link>
          <Link to="/stats" className="nav-link">Stats</Link>
          <Link to="/admin" className="nav-link">Admin</Link>
        </div>
      </nav>
      <Routes>
        <Route path="/" element={<QuestionListPage />} />
        <Route path="/questions/:id" element={<QuestionDetailPage />} />
        <Route path="/stats" element={<StatsPage />} />
        <Route path="/admin" element={<AdminPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
