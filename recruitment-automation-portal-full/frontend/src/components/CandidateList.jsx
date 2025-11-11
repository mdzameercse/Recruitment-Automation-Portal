import React, { useEffect, useState } from "react";
import axios from "axios";

export default function CandidateList() {
  const [candidates, setCandidates] = useState([]);

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/candidates");
      setCandidates(res.data || []);
    } catch (e) {
      console.error("Error fetching candidates:", e);
    }
  };

  const schedule = async (id) => {
    const date = prompt(
      "Enter interview datetime in ISO format (e.g. 2025-11-12T10:00:00)"
    );
    if (!date) return;
    try {
      const res = await axios.post("http://localhost:8080/api/interviews", {
        candidateId: id,
        interviewDate: date,
      });
      alert("Interview scheduled successfully!");
    } catch (e) {
      alert("Failed: " + (e.response?.data?.error || e.message));
    }
  };

  return (
    <div className="container">
      <h3>Candidates</h3>
      <table style={{ width: "100%", borderCollapse: "collapse" }}>
        <thead>
          <tr style={{ textAlign: "left" }}>
            <th>Name</th>
            <th>Email</th>
            <th>Skills</th>
            <th>Score</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {candidates.length === 0 ? (
            <tr>
              <td colSpan="5" style={{ textAlign: "center", padding: "10px" }}>
                No candidates found. Upload a resume first.
              </td>
            </tr>
          ) : (
            candidates.map((c) => (
              <tr key={c.id} style={{ borderTop: "1px solid #eee" }}>
                <td>{c.name}</td>
                <td>{c.email}</td>
                <td>{c.skills}</td>
                <td>{c.score}</td>
                <td>
                  <button onClick={() => schedule(c.id)}>Schedule</button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>

      <button onClick={fetchList} style={{ marginTop: 10 }}>
        Refresh
      </button>
    </div>
  );
}
