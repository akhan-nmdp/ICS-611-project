import { useEffect, useState } from "react";
import axios from "axios";

function Members() {
  const [Members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

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

  //display in simple manner
//   return (
//     <div style={{ maxWidth: "600px", margin: "auto", textAlign: "left" }}>
//       <h1>Members</h1>
//       <ul style={{ listStyle: "none", padding: 0 }}>
//         {Members.map(book => (
//           <li key={book.bookId}  style={{ padding: "10px", borderBottom: "1px solid #ccc" }}> {book.isbn} <strong>{book.title}</strong> {book.author} {book.genre} {book.availability ? "Yes" : "No"}</li>
//         ))}
//       </ul>
//     </div>
//   );
// }
  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Members List</h1>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>Name</th>
            <th style={tableHeaderStyle}>Email</th>
            <th style={tableHeaderStyle}>MembershipDate</th>
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
