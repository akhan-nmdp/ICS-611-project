import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Home";
import Books from "./Books";
import BorrowedBooks from "./BorrowedBooks";
import Members from "./Members";
import Librarians from "./Librarians";
import Fines from "./Fines";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/books" element={<Books />} />
        <Route path="/borrowed-books" element={<BorrowedBooks />} />
        <Route path="/members" element={<Members />} />
        <Route path="/librarians" element={<Librarians />} />
        <Route path="/fines" element={<Fines />} />
      </Routes>
    </Router>
  );
}

export default App;
