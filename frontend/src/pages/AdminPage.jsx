import { useState, useEffect } from 'react';
import { fetchQuestions, fetchTags } from '../api/questions';
import { createQuestion, updateQuestion, deleteQuestion } from '../api/admin';
import { fetchQuestion } from '../api/questions';
import TEMPLATES from '../data/driverTemplates';

export default function AdminPage() {
  const [questions, setQuestions] = useState([]);
  const [tags, setTags] = useState([]);
  const [selectedTemplate, setSelectedTemplate] = useState('');
  const [editing, setEditing] = useState(null);
  const [form, setForm] = useState(emptyForm());

  function emptyForm() {
    return {
      title: '',
      description: '',
      difficulty: 'EASY',
      methodSignature: '',
      driverCode: '',
      tags: [],
      testCases: [{ input: '', expectedOutput: '', displayInput: '', displayOutput: '', sample: true, orderIndex: 0 }],
    };
  }

  useEffect(() => {
    loadQuestions();
    fetchTags().then(setTags);
  }, []);

  const loadQuestions = () => fetchQuestions().then(setQuestions);

  const handleEdit = async (id) => {
    const q = await fetchQuestion(id);
    setEditing(id);
    setForm({
      title: q.title,
      description: q.description,
      difficulty: q.difficulty,
      methodSignature: q.methodSignature || '',
      driverCode: q.driverCode || '',
      tags: q.tags || [],
      testCases: q.testCases.length > 0
        ? q.testCases.map((tc) => ({
            input: tc.input || '',
            expectedOutput: tc.expectedOutput || '',
            displayInput: tc.displayInput || '',
            displayOutput: tc.displayOutput || '',
            sample: tc.sample,
            orderIndex: tc.orderIndex,
          }))
        : [{ input: '', expectedOutput: '', displayInput: '', displayOutput: '', sample: true, orderIndex: 0 }],
    });
  };

  const handleDelete = async (id) => {
    if (!confirm('Delete this question?')) return;
    await deleteQuestion(id);
    loadQuestions();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = { ...form, tags: new Set(form.tags) };
    // Convert Set to array for JSON
    data.tags = [...form.tags];

    if (editing) {
      await updateQuestion(editing, data);
    } else {
      await createQuestion(data);
    }
    setForm(emptyForm());
    setEditing(null);
    setSelectedTemplate('');
    loadQuestions();
  };

  const handleCancel = () => {
    setForm(emptyForm());
    setEditing(null);
    setSelectedTemplate('');
  };

  const toggleTag = (tagName) => {
    setForm((prev) => ({
      ...prev,
      tags: prev.tags.includes(tagName)
        ? prev.tags.filter((t) => t !== tagName)
        : [...prev.tags, tagName],
    }));
  };

  const updateTestCase = (index, field, value) => {
    setForm((prev) => ({
      ...prev,
      testCases: prev.testCases.map((tc, i) =>
        i === index ? { ...tc, [field]: value } : tc
      ),
    }));
  };

  const addTestCase = () => {
    setForm((prev) => ({
      ...prev,
      testCases: [
        ...prev.testCases,
        { input: '', expectedOutput: '', displayInput: '', displayOutput: '', sample: true, orderIndex: prev.testCases.length },
      ],
    }));
  };

  const removeTestCase = (index) => {
    setForm((prev) => ({
      ...prev,
      testCases: prev.testCases.filter((_, i) => i !== index),
    }));
  };

  return (
    <div className="page admin-page">
      <h1>Admin</h1>

      <div className="admin-layout">
        <div className="admin-list">
          <h2>Questions</h2>
          {questions.map((q) => (
            <div key={q.id} className="admin-question-row">
              <span>{q.title}</span>
              <span className={`difficulty-badge ${q.difficulty.toLowerCase()}`}>{q.difficulty}</span>
              <div className="admin-actions">
                <button className="btn btn-small" onClick={() => handleEdit(q.id)}>Edit</button>
                <button className="btn btn-small btn-danger" onClick={() => handleDelete(q.id)}>Delete</button>
              </div>
            </div>
          ))}
        </div>

        <form className="admin-form" onSubmit={handleSubmit}>
          <h2>{editing ? 'Edit Question' : 'New Question'}</h2>

          <label>Title</label>
          <input
            type="text"
            value={form.title}
            onChange={(e) => setForm({ ...form, title: e.target.value })}
            required
          />

          <label>Description</label>
          <textarea
            rows={4}
            value={form.description}
            onChange={(e) => setForm({ ...form, description: e.target.value })}
            required
          />

          <label>Difficulty</label>
          <select
            value={form.difficulty}
            onChange={(e) => setForm({ ...form, difficulty: e.target.value })}
          >
            <option value="EASY">Easy</option>
            <option value="MEDIUM">Medium</option>
            <option value="HARD">Hard</option>
          </select>

          <label>I/O Template</label>
          <select
            value={selectedTemplate}
            onChange={(e) => {
              const idx = e.target.value;
              setSelectedTemplate(idx);
              if (idx !== '') {
                const t = TEMPLATES[idx];
                setForm((prev) => ({
                  ...prev,
                  methodSignature: t.methodSignature,
                  driverCode: t.driverCode,
                }));
              }
            }}
          >
            <option value="">Select a template...</option>
            {TEMPLATES.map((t, i) => (
              <option key={i} value={i}>
                {t.name}{t.example ? ` (e.g. ${t.example})` : ''}
              </option>
            ))}
          </select>

          <label>Method Signature (what the user sees in the editor)</label>
          <textarea
            rows={5}
            value={form.methodSignature}
            onChange={(e) => setForm({ ...form, methodSignature: e.target.value })}
            placeholder={'    public int[] twoSum(int[] nums, int target) {\n        // solution here\n        return new int[]{};\n    }'}
          />

          <label>Driver Code (main method — parses stdin, calls user method, prints output)</label>
          <textarea
            rows={8}
            value={form.driverCode}
            onChange={(e) => setForm({ ...form, driverCode: e.target.value })}
            placeholder={'    public static void main(String[] args) {\n        Scanner sc = new Scanner(System.in);\n        // parse input, call method, print result\n    }'}
          />

          <label>Tags</label>
          <div className="tag-picker">
            {tags.map((t) => (
              <button
                key={t.id}
                type="button"
                className={`tag-pick ${form.tags.includes(t.name) ? 'selected' : ''}`}
                onClick={() => toggleTag(t.name)}
              >
                {t.name}
              </button>
            ))}
          </div>

          <label>Test Cases</label>
          {form.testCases.map((tc, i) => (
            <div key={i} className="test-case-form">
              <div className="test-case-form-header">
                <strong>Test Case {i + 1}</strong>
                <label className="checkbox-label">
                  <input
                    type="checkbox"
                    checked={tc.sample}
                    onChange={(e) => updateTestCase(i, 'sample', e.target.checked)}
                  />
                  Sample (visible to user)
                </label>
                {form.testCases.length > 1 && (
                  <button type="button" className="btn btn-small btn-danger" onClick={() => removeTestCase(i)}>Remove</button>
                )}
              </div>
              <div className="test-case-form-grid">
                <div>
                  <label>Raw Input (stdin)</label>
                  <textarea rows={2} value={tc.input} onChange={(e) => updateTestCase(i, 'input', e.target.value)} />
                </div>
                <div>
                  <label>Expected Output (stdout)</label>
                  <textarea rows={2} value={tc.expectedOutput} onChange={(e) => updateTestCase(i, 'expectedOutput', e.target.value)} />
                </div>
                <div>
                  <label>Display Input (human-readable)</label>
                  <input type="text" value={tc.displayInput} onChange={(e) => updateTestCase(i, 'displayInput', e.target.value)} />
                </div>
                <div>
                  <label>Display Output (human-readable)</label>
                  <input type="text" value={tc.displayOutput} onChange={(e) => updateTestCase(i, 'displayOutput', e.target.value)} />
                </div>
              </div>
            </div>
          ))}
          <button type="button" className="btn btn-run" onClick={addTestCase}>+ Add Test Case</button>

          <div className="form-actions">
            <button type="submit" className="btn btn-submit">
              {editing ? 'Update Question' : 'Create Question'}
            </button>
            {editing && (
              <button type="button" className="btn btn-run" onClick={handleCancel}>Cancel</button>
            )}
          </div>
        </form>
      </div>
    </div>
  );
}
