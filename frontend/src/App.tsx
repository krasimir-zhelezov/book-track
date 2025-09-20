import { Route, Routes } from 'react-router-dom'
import './App.css'
import Search from './pages/Search'
import Components from './pages/Components'
import BookView from './pages/BookView'

function App() {

  return (
    <Routes>
      <Route path="/search" element={<Search/>}></Route>
      <Route path="/components" element={<Components/>}></Route>
      <Route path="/book/:id" element={<BookView/>}></Route>
    </Routes>
  )
}

export default App
