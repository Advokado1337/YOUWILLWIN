const API = 'http://localhost:8080/api';

export async function fetchQuestions(filters = {}) {
  const params = new URLSearchParams();
  if (filters.difficulty) params.set('difficulty', filters.difficulty);
  if (filters.tag) params.set('tag', filters.tag);
  const query = params.toString();
  const res = await fetch(`${API}/questions${query ? '?' + query : ''}`);
  return res.json();
}

export async function fetchQuestion(id) {
  const res = await fetch(`${API}/questions/${id}`);
  return res.json();
}

export async function fetchTags() {
  const res = await fetch(`${API}/tags`);
  return res.json();
}
