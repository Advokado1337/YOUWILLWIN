import { useState, useRef, useCallback } from 'react';

export function useTimer() {
  const [elapsed, setElapsed] = useState(0);
  const startTime = useRef(null);
  const intervalRef = useRef(null);

  const start = useCallback(() => {
    if (intervalRef.current) return;
    startTime.current = Date.now() - elapsed;
    intervalRef.current = setInterval(() => {
      setElapsed(Date.now() - startTime.current);
    }, 1000);
  }, [elapsed]);

  const stop = useCallback(() => {
    clearInterval(intervalRef.current);
    intervalRef.current = null;
  }, []);

  const reset = useCallback(() => {
    stop();
    setElapsed(0);
    startTime.current = null;
  }, [stop]);

  const formatTime = (ms) => {
    const totalSec = Math.floor(ms / 1000);
    const min = Math.floor(totalSec / 60);
    const sec = totalSec % 60;
    return `${min.toString().padStart(2, '0')}:${sec.toString().padStart(2, '0')}`;
  };

  return { elapsed, formatted: formatTime(elapsed), start, stop, reset };
}
