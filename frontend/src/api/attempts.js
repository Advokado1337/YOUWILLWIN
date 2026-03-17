const API = 'http://localhost:8080/api';

export async function fetchAttempts(questionId) {
  const url = questionId
    ? `${API}/attempts?questionId=${questionId}`
    : `${API}/attempts`;
  const res = await fetch(url);
  return res.json();
}
