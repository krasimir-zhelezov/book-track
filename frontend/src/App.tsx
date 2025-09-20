import { Route, Routes } from 'react-router-dom'
import './App.css'
import Search from './pages/Search'
import Components from './pages/Components'

function App() {

  return (
    <Routes>
      <Route path="/search" element={<Search/>}></Route>
      <Route path="/components" element={<Components/>}></Route>
    </Routes>
  )
}

export default App
