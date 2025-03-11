import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function UsersTable() {
  const [users, setUsers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get("http://localhost:5000/users")  // Replace with your API URL
      .then(response => setUsers(response.data))
      .catch(error => console.error(error));
  }, []);

  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Users List</h1>
      <button 
        onClick={() => navigate("/")} 
        style={{ marginBottom: "20px", padding: "8px 15px", cursor: "pointer" }}
      >
        Go Back
      </button>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>ID</th>
            <th style={tableHeaderStyle}>Name</th>
            <th style={tableHeaderStyle}>Age</th>
            <th style={tableHeaderStyle}>Status</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td style={tableCellStyle}>{user.id}</td>
              <td style={tableCellStyle}>{user.name}</td>
              <td style={tableCellStyle}>{user.age}</td>
              <td style={tableCellStyle}>
                {user.active ? "✅ Active" : "❌ Inactive"}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

const tableHeaderStyle = { border: "1px solid black", padding: "10px", textAlign: "left" };
const tableCellStyle = { border: "1px solid black", padding: "10px" };

export default UsersTable;
