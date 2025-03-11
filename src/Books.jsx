import { useEffect, useState } from "react";
import axios from "axios";

function Books() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch data from API
    axios.get("http://localhost:8080/books")
      .then(response => {
        setBooks(response.data);
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
//       <h1>Books</h1>
//       <ul style={{ listStyle: "none", padding: 0 }}>
//         {books.map(book => (
//           <li key={book.bookId}  style={{ padding: "10px", borderBottom: "1px solid #ccc" }}> {book.isbn} <strong>{book.title}</strong> {book.author} {book.genre} {book.availability ? "Yes" : "No"}</li>
//         ))}
//       </ul>
//     </div>
//   );
// }
  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Book List</h1>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>ISBN</th>
            <th style={tableHeaderStyle}>Title</th>
            <th style={tableHeaderStyle}>Author</th>
            <th style={tableHeaderStyle}>Genre</th>
            <th style={tableHeaderStyle}>Availability</th>
          </tr>
        </thead>
        <tbody>
          {books.map(book => (
            <tr key={book.bookId}>
              <td style={tableCellStyle}>{book.isbn}</td>
              <td style={tableCellStyle}>{book.title}</td>
              <td style={tableCellStyle}>{book.author}</td>
              <td style={tableCellStyle}>{book.genre}</td>
              <td style={tableCellStyle}>
                {book.availability ? "✅ Yes" : "❌ No"}
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

export default Books;
