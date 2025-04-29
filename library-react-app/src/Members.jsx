import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Members() {
  const [Members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch data from API
    axios.get("http://localhost:8080/members")
      .then(response => {
        setMembers(response.data);
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
      <h1>Members List</h1>
      <button 
        onClick={() => navigate("/")} 
        style={{ marginBottom: "20px", padding: "8px 15px", cursor: "pointer" }}
      >
        Go Back
      </button>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>Name</th>
            <th style={tableHeaderStyle}>Email</th>
            <th style={tableHeaderStyle}>Membership Start Date</th>
          </tr>
        </thead>
        <tbody>
          {Members.map(member => (
            <tr key={member.memberId}>
              <td style={tableCellStyle}>{member.name}</td>
              <td style={tableCellStyle}>{member.email}</td>
              <td style={tableCellStyle}>{member.membershipDate}</td>
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

export default Members;
