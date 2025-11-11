import React, { useState } from "react";
import axios from "axios";

export default function ResumeUpload() {
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState("");

  const upload = async () => {
    if (!file) {
      setMessage("Select a file first");
      return;
    }

    const fd = new FormData();
    fd.append("file", file);

    try {
      const res = await axios.post("http://localhost:8080/api/resumes/upload", fd, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      setMessage(`Uploaded: ${res.data.name} (${res.data.email})`);
    } catch (e) {
      setMessage(`Upload failed: ${e.response?.data?.error || e.message}`);
    }
  };

  return (
    <div className="container">
      <h3>Upload Resume (PDF/DOCX)</h3>

      <input
        type="file"
        onChange={(e) => setFile(e.target.files[0])}
        style={{ marginRight: 10 }}
      />

      <button onClick={upload} style={{ marginLeft: 10 }}>
        Upload
      </button>

      <p>{message}</p>
    </div>
  );
}
