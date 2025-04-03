import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Welcome to My Awesome Library System</h1>
      <button
        onClick={() => navigate("/books")}
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          cursor: "pointer",
          background: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
        }}
      >
        View Books
      </button>
      <button
        onClick={() => navigate("/borrowed-books")}
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          cursor: "pointer",
          background: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
        }}
      >
        View Borrowed Books
      </button>
      <button
        onClick={() => navigate("/members")}
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          cursor: "pointer",
          background: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
        }}
      >
        View Members
      </button>
      <button
        onClick={() => navigate("/librarians")}
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          cursor: "pointer",
          background: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
        }}
      >
        View Librarians
      </button>
<<<<<<< HEAD
=======

>>>>>>> borrow_feature
      <button
        onClick={() => navigate("/fines")}
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          cursor: "pointer",
          background: "blue",
          color: "white",
          border: "none",
          borderRadius: "5px",
        }}
      >
        View Fines
      </button>

    </div>
  );
}

export default Home;
