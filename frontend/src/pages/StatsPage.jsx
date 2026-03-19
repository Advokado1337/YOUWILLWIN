import { useState, useEffect } from 'react';
import { fetchOverview, fetchStatsByTag, fetchActivity } from '../api/stats';

export default function StatsPage() {
  const [overview, setOverview] = useState(null);
  const [tagStats, setTagStats] = useState([]);
  const [activity, setActivity] = useState([]);

  useEffect(() => {
    fetchOverview().then(setOverview);
    fetchStatsByTag().then(setTagStats);
    fetchActivity().then(setActivity);
  }, []);

  if (!overview) return <div className="page">Loading...</div>;

  return (
    <div className="page">
      <h1>Statistics</h1>

      <div className="stats-grid">
        <div className="stat-card">
          <div className="stat-value">{overview.totalQuestions}</div>
          <div className="stat-label">Total Questions</div>
        </div>
        <div className="stat-card">
          <div className="stat-value">{overview.totalSolved}</div>
          <div className="stat-label">Solved</div>
        </div>
        <div className="stat-card">
          <div className="stat-value">{overview.totalAttempts}</div>
          <div className="stat-label">Total Attempts</div>
        </div>
        <div className="stat-card">
          <div className="stat-value">{overview.solveRate}%</div>
          <div className="stat-label">Solve Rate</div>
        </div>
      </div>

      {tagStats.length > 0 && (
        <>
          <h2>Performance by Tag</h2>
          <div className="tag-stats-table">
            <div className="tag-stats-header">
              <span>Tag</span>
              <span>Attempts</span>
              <span>Passed</span>
              <span>Pass Rate</span>
            </div>
            {tagStats.map((ts) => (
              <div key={ts.tagName} className="tag-stats-row">
                <span className="tag">{ts.tagName}</span>
                <span>{ts.totalAttempts}</span>
                <span>{ts.passCount}</span>
                <span>
                  <div className="pass-rate-bar">
                    <div
                      className="pass-rate-fill"
                      style={{ width: `${ts.passRate}%` }}
                    />
                    <span className="pass-rate-text">{ts.passRate}%</span>
                  </div>
                </span>
              </div>
            ))}
          </div>
        </>
      )}

      {activity.length > 0 && (
        <>
          <h2>Recent Activity</h2>
          <div className="activity-list">
            {activity.map((a) => (
              <div key={a.date} className="activity-row">
                <span className="activity-date">{a.date}</span>
                <span>{a.attempts} attempts</span>
                <span className="activity-passed">{a.passed} passed</span>
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
}
