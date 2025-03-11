import { useEffect, useState } from "react";
import axios from "axios";

function Librarians() {
  const [Librarians, setLibrarians] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch data from API
    axios.get("http://localhost:8080/librarians")
      .then(response => {
        setLibrarians(response.data);
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
//       <h1>Librarians</h1>
//       <ul style={{ listStyle: "none", padding: 0 }}>
//         {Librarians.map(book => (
//           <li key={book.bookId}  style={{ padding: "10px", borderBottom: "1px solid #ccc" }}> {book.isbn} <strong>{book.title}</strong> {book.author} {book.genre} {book.availability ? "Yes" : "No"}</li>
//         ))}
//       </ul>
//     </div>
//   );
// }
  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Librarians List</h1>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>Name</th>
            <th style={tableHeaderStyle}>Contact</th>
            <th style={tableHeaderStyle}>Role</th>
          </tr>
        </thead>
        <tbody>
          {Librarians.map(librarian => (
            <tr key={librarian.librarianId}>
              <td style={tableCellStyle}>{librarian.name}</td>
              <td style={tableCellStyle}>{librarian.contact}</td>
              <td style={tableCellStyle}>{librarian.role}</td>
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

export default Librarians;
