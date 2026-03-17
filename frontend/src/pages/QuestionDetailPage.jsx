import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import Editor from '@monaco-editor/react';
import { fetchQuestion } from '../api/questions';
import { runCode, submitCode } from '../api/execution';
import { fetchAttempts } from '../api/attempts';
import { useTimer } from '../hooks/useTimer';
import TestResultPanel from '../components/TestResultPanel';

const FALLBACK_CODE = `public int[] solve(int[] input) {
    // your solution here
    return new int[]{};
}`;

export default function QuestionDetailPage() {
  const { id } = useParams();
  const [question, setQuestion] = useState(null);
  const [code, setCode] = useState('');
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [attempts, setAttempts] = useState([]);
  const timer = useTimer();

  useEffect(() => {
    fetchQuestion(id).then((q) => {
      setQuestion(q);
      setCode(q.methodSignature || FALLBACK_CODE);
      timer.start();
    });
    fetchAttempts(id).then(setAttempts);
  }, [id]);

  const handleRun = async () => {
    setLoading(true);
    setResult(null);
    try {
      const res = await runCode(question.id, code);
      setResult(res);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async () => {
    setLoading(true);
    setResult(null);
    try {
      const res = await submitCode(question.id, code, timer.elapsed);
      setResult(res.execution);
      timer.stop();
      fetchAttempts(id).then(setAttempts);
    } finally {
      setLoading(false);
    }
  };

  if (!question) return <div className="page">Loading...</div>;

  const sampleCases = question.testCases.filter((tc) => tc.sample);

  return (
    <div className="question-detail">
      <div className="question-panel">
        <h1>{question.title}</h1>
        <span className={`difficulty-badge ${question.difficulty.toLowerCase()}`}>
          {question.difficulty}
        </span>
        <div className="question-tags">
          {question.tags.map((tag) => (
            <span key={tag} className="tag">{tag}</span>
          ))}
        </div>
        <div className="description">{question.description}</div>

        <h3>Examples</h3>
        {sampleCases.map((tc) => (
          <div key={tc.id} className="sample-case">
            <div><strong>Input:</strong><pre>{tc.displayInput}</pre></div>
            <div><strong>Output:</strong><pre>{tc.displayOutput}</pre></div>
          </div>
        ))}

        {attempts.length > 0 && (
          <>
            <h3>Previous Attempts</h3>
            <div className="attempts-list">
              {attempts.slice(0, 5).map((a) => (
                <div key={a.id} className={`attempt ${a.status === 'PASS' ? 'pass' : 'fail'}`}>
                  <span>{a.status}</span>
                  <span>{a.passedCount}/{a.totalCount}</span>
                  <span>{new Date(a.executedAt).toLocaleString()}</span>
                </div>
              ))}
            </div>
          </>
        )}
      </div>

      <div className="editor-panel">
        <div className="editor-toolbar">
          <span className="timer">{timer.formatted}</span>
          <div className="editor-actions">
            <button onClick={handleRun} disabled={loading} className="btn btn-run">
              {loading ? 'Running...' : 'Run'}
            </button>
            <button onClick={handleSubmit} disabled={loading} className="btn btn-submit">
              {loading ? 'Submitting...' : 'Submit'}
            </button>
          </div>
        </div>
        <Editor
          height="60vh"
          language="java"
          theme="vs-dark"
          value={code}
          onChange={(val) => setCode(val || '')}
          options={{
            fontSize: 14,
            minimap: { enabled: false },
            scrollBeyondLastLine: false,
          }}
        />
        <TestResultPanel result={result} />
      </div>
    </div>
  );
}
