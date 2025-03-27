import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function Books() {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const [selectedBook, setSelectedBook] = useState(null); // Store the selected book for borrowing
  const [selectedMember, setSelectedMember] = useState(""); 
  const [modalVisible, setModalVisible] = useState(false); 

  useEffect(() => {
    // Fetch data from API for books
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

  useEffect(() => {
    // Fetch data from API for members
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

  const handleBorrowBook = (book) => {
    // Set the selected book and show the modal
    setSelectedBook(book);
    setModalVisible(true);
  };

  const closeModal = () => {
    setModalVisible(false); // Close the modal
    setSelectedBook(null); // Reset the selected book
    setSelectedMember(""); // Reset the selected member
  };

  const handleModalSubmit = () => {
    if (selectedMember && selectedBook) {
      const borrowDate = new Date();
      const returnDate = new Date(borrowDate);
      returnDate.setDate(borrowDate.getDate() + 14); // Set return date to two weeks from borrow date

      // Format dates as strings (e.g., "YYYY-MM-DD")
      const formattedBorrowDate = borrowDate.toISOString().split('T')[0];
      const formattedReturnDate = returnDate.toISOString().split('T')[0];

      // Prepare the data to be sent to the borrowedBooks table
      const borrowedBookData = {
        memberId: null,
        memberName: selectedMember,
        bookId: selectedBook.bookId,
        borrowDate: formattedBorrowDate,
        returnDate: formattedReturnDate,
      };

      // Call the API to save the borrowed book information
      axios.post("http://localhost:8080/borrowed-books", borrowedBookData)
        .then(response => {
          console.log("Book borrowed successfully:", response.data);
          
          // Update availability of borrowed books in the state
          const updatedBooks = books.map(book => 
            book.bookId === selectedBook.bookId ? { ...book, availability: false } : book
          );
          setBooks(updatedBooks); // Update the books state with the new availability status

          console.log(updatedBooks)
          console.log(borrowedBookData)
          // Close the modal and reset the form
          closeModal();
        })
        .catch(error => {
          console.error("Error borrowing book:", error);
        });
    }
  };

  return (
    <div style={{ maxWidth: "600px", margin: "auto", textAlign: "center" }}>
      <h1>Book List</h1>
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
            <th style={tableHeaderStyle}>Availability</th>
            <th style={tableHeaderStyle}>Borrow</th>
          </tr>
        </thead>
        <tbody>
          {books.map((book) => (
            <tr key={book.bookId}>
              <td style={tableCellStyle}>{book.isbn}</td>
              <td style={tableCellStyle}>{book.title}</td>
              <td style={tableCellStyle}>{book.author}</td>
              <td style={tableCellStyle}>{book.genre}</td>
              <td style={tableCellStyle}>
                {book.availability ? "✅ Yes" : "❌ No"}
              </td>
              <td style={tableCellStyle}>
                {book.availability ? (
                  <button
                    onClick={() => handleBorrowBook(book)}
                    style={{
                      padding: "5px 10px",
                      cursor: "pointer",
                      backgroundColor: "#4CAF50",
                      color: "white",
                      border: "none",
                      borderRadius: "4px",
                    }}
                  >
                    Borrow
                  </button>
                ) : (
                  <button
                    disabled
                    style={{
                      padding: "5px 10px",
                      cursor: "not-allowed",
                      backgroundColor: "#ccc",
                      color: "white",
                      border: "none",
                      borderRadius: "4px"
                    }}
                  >
                    Borrow
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Modal for borrowing a book */}
      {modalVisible && selectedBook && (
        <div style={modalOverlayStyle}>
          <div style={modalContentStyle}>
            <h2>Select Member</h2>
            <select
              value={selectedMember}
              onChange={(e) => setSelectedMember(e.target.value)}
              style={{
                padding: "5px",
                cursor: "pointer",
                width: "100%",
                borderRadius: "4px",
              }}
            >
              <option value="">Select Member</option>
              {members.map((member) => (
                <option key={member.id} value={member.name}>
                  {member.name}
                </option>
              ))}
            </select>
            <h3>Selected Book</h3>
            <p>{selectedBook.title}</p>
            <div style={{ marginTop: "10px" }}>
              <button
                onClick={handleModalSubmit}
                style={{
                  padding: "5px 10px",
                  cursor: "pointer",
                  backgroundColor: "#4CAF50",
                  color: "white",
                  border: "none",
                  borderRadius: "4px",
                }}
              >
                Confirm Borrow
              </button>
              <button
                onClick={closeModal}
                style={{
                  padding: "5px 10px",
                  cursor: "pointer",
                  backgroundColor: "#f44336",
                  color: "white",
                  border: "none",
                  borderRadius: "4px",
                  marginLeft: "10px"
                }}
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
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

const modalOverlayStyle = {
  position: "fixed",
  top: 0,
  left: 0,
  width: "100%",
  height: "100%",
  backgroundColor: "rgba(0, 0, 0, 0.5)",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
};

const modalContentStyle = {
  backgroundColor: "white",
  padding: "20px",
  borderRadius: "8px",
  minWidth: "300px",
  textAlign: "center",
};

export default Books;
