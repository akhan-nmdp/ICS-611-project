import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function BorrowedBooks() {

    const [borrowedBooks, setBorrowedBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    useEffect(() => {
      // Fetch data from API
      axios.get("http://localhost:8080/borrowed-books")
        .then(response => {
          setBorrowedBooks(response.data);
          setLoading(false);
          console.log(response.data);
        })
        .catch(error => {
          setError(error);
          setLoading(false);
        });
    }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  const handleReturnBook = async (borrowId) => {
    try {
      await axios.put(`http://localhost:8080/borrowed-books/return/${borrowId}`);
      // After successful return, refetch the updated list
      const response = await axios.get("http://localhost:8080/borrowed-books");
      setBorrowedBooks(response.data);
  
    } catch (error) {
      console.error("Failed to return book:", error);
      alert("Error returning the book. Please try again.");
    }
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
            <th style={tableHeaderStyle}>Member Name</th>
            <th style={tableHeaderStyle}>Book Name</th>
            <th style={tableHeaderStyle}>Borrow Date</th>
            <th style={tableHeaderStyle}>Return Date</th>
            <th style={tableHeaderStyle}>Borrow Status</th>
          </tr>
        </thead>
        <tbody>
  {borrowedBooks.map(borrowedBook => (
    <tr key={borrowedBook.borrowId}>
      <td style={tableCellStyle}>{borrowedBook.memberName}</td>
      <td style={tableCellStyle}>{borrowedBook.bookName}</td>
      <td style={tableCellStyle}>{borrowedBook.borrowDate}</td>
      <td style={tableCellStyle}>{borrowedBook.returnDate}</td>
      <td style={tableCellStyle}>
        {borrowedBook.borrowStatus}
        {borrowedBook.borrowStatus === 'BORROWED' && (
          <button 
            onClick={() => handleReturnBook(borrowedBook.borrowId)}
            style={{
              marginLeft: "10px",
              padding: "5px 10px",
              cursor: "pointer",
              backgroundColor: "#28a745",
              color: "white",
              border: "none",
              borderRadius: "5px"
            }}
          >
            Return
          </button>
        )}
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

export default BorrowedBooks;
