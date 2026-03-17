export default function TestResultPanel({ result }) {
  if (!result) return null;

  if (result.compileError) {
    return (
      <div className="test-result-panel">
        <div className="result-header error">Compilation Error</div>
        <pre className="compile-error">{result.compileError}</pre>
      </div>
    );
  }

  return (
    <div className="test-result-panel">
      <div className={`result-header ${result.status === 'PASS' ? 'pass' : 'fail'}`}>
        {result.status === 'PASS' ? 'All Tests Passed' : 'Some Tests Failed'}
      </div>
      <div className="test-cases">
        {result.testCases.map((tc) => (
          <div key={tc.index} className={`test-case ${tc.passed ? 'passed' : 'failed'}`}>
            <div className="test-case-header">
              <span>{tc.passed ? 'PASS' : 'FAIL'} - Test Case {tc.index + 1}</span>
            </div>
            <div className="test-case-details">
              <div><strong>Input:</strong> <pre>{tc.displayInput}</pre></div>
              <div><strong>Expected:</strong> <pre>{tc.displayOutput}</pre></div>
              <div><strong>Actual:</strong> <pre>{tc.actualOutput}</pre></div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
