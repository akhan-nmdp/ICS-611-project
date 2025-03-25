import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Fines() {
  const [fines, setFines] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch data from API
    axios.get("http://localhost:8080/fines")
      .then(response => {
        setFines(response.data);
        setLoading(false);
      })
      .catch(error => {
        setError(error);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Fines List</h1>
      <button 
        onClick={() => navigate("/")} 
        style={{ marginBottom: "20px", padding: "8px 15px", cursor: "pointer" }}
      >
                Go Back
                </button>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>MemberId</th>
            <th style={tableHeaderStyle}>BorrowId</th>
            <th style={tableHeaderStyle}>FineAmount</th>
            <th style={tableHeaderStyle}>IssueDate</th>
            <th style={tableHeaderStyle}>Paid</th>
          </tr>
        </thead>
        <tbody>
          {fines.map(fine => (
            <tr key={fine.fineId}>
              <td style={tableCellStyle}>{fine.memberId}</td>
              <td style={tableCellStyle}>{fine.borrowId}</td>
              <td style={tableCellStyle}>{fine.fineAmount}</td>
              <td style={tableCellStyle}>{fine.issueDate}</td>
              <td style={tableCellStyle}>
                {fine.paid ? "✅ Yes" : "❌ No"}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const tableHeaderStyle = {
  border: "1px solid black",
  padding: "10px",
  textAlign: "left",
};

const tableCellStyle = {
  border: "1px solid black",
  padding: "10px",
};

export default Fines;
