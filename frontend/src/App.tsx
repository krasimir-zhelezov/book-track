import { Route, Routes } from 'react-router-dom'
import './App.css'
import Search from './pages/Search'
import Components from './pages/Components'
import BookView from './pages/BookView'
import SignUp from './pages/SignUp'
import SignIn from './pages/SignIn'

function App() {

  return (
    <Routes>
      <Route path="/search" element={<Search/>}></Route>
      <Route path="/components" element={<Components/>}></Route>
      <Route path="/book/:id" element={<BookView/>}></Route>
      <Route path="/auth/sign-up" element={<SignUp/>}></Route>
      <Route path="/auth/sign-in" element={<SignIn/>}></Route>
    </Routes>
  )
}

export default App
