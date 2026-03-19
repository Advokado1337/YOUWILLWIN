const API = 'http://localhost:8080/api';

export async function createQuestion(data) {
  const res = await fetch(`${API}/questions`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error('Failed to create question');
  return res.json();
}

export async function updateQuestion(id, data) {
  const res = await fetch(`${API}/questions/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error('Failed to update question');
  return res.json();
}

export async function deleteQuestion(id) {
  const res = await fetch(`${API}/questions/${id}`, { method: 'DELETE' });
  if (!res.ok) throw new Error('Failed to delete question');
}
