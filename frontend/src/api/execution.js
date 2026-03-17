const API = 'http://localhost:8080/api';

export async function runCode(questionId, code) {
  const res = await fetch(`${API}/execute/run`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ questionId, code }),
  });
  return res.json();
}

export async function submitCode(questionId, code, timeSpentMs) {
  const res = await fetch(`${API}/execute/submit`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ questionId, code, timeSpentMs }),
  });
  return res.json();
}
