const API = 'http://localhost:8080/api';

export async function fetchOverview() {
  const res = await fetch(`${API}/stats/overview`);
  return res.json();
}

export async function fetchStatsByTag() {
  const res = await fetch(`${API}/stats/by-tag`);
  return res.json();
}

export async function fetchActivity() {
  const res = await fetch(`${API}/stats/activity`);
  return res.json();
}
