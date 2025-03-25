import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function BorrowedBooks() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const [borrowedBooks, setBorrowedBooks] = useState([]);


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

  const handleBorrowBook = (book) => {
    if (borrowedBooks.includes(book.bookId)) {
      setBorrowedBooks(prevState => prevState.filter(id => id !== book.bookId));
    } else {
      setBorrowedBooks(prevState => [...prevState, book.bookId]);
    }
  };
  

  const isBookBorrowed = (bookId) => {
    return borrowedBooks.includes(bookId);
  };

  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Borrewed Books List</h1>
      <button 
        onClick={() => navigate("/")} 
        style={{ marginBottom: "20px", padding: "8px 15px", cursor: "pointer" }}
      >
                Go Back
                </button>
      <table style={{ width: "100%", borderCollapse: "collapse", marginTop: "20px" }}>
        <thead>
          <tr style={{ background: "#ddd" }}>
            <th style={tableHeaderStyle}>ISBN</th>
            <th style={tableHeaderStyle}>Title</th>
            <th style={tableHeaderStyle}>Author</th>
            <th style={tableHeaderStyle}>Genre</th>
          </tr>
        </thead>
        <tbody>
          {books.map(book => (
            <tr key={book.bookId}>
              <td style={tableCellStyle}>{book.isbn}</td>
              <td style={tableCellStyle}>{book.title}</td>
              <td style={tableCellStyle}>{book.author}</td>
              <td style={tableCellStyle}>{book.genre}</td>
              
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

export default BorrowedBooks;
