import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ChatRoomList from "./ChatRoomList";

function App() {   
  return (
  <Router>
    <Routes>
      <Route path="/" element={<ChatRoomList />} />
    </Routes>
  </Router>
  );
}

export default App;
