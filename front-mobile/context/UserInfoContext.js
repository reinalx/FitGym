import { createContext, useState, useEffect, useContext } from 'react'
import { loginUser } from '../backend/userService'

const UserInfoContext = createContext()

export const UserInfoProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false)
  const [user, setUser] = useState(null)
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    setIsLoading(true)
    loginUser('prueba@prueba.com', 'prueba')
      .then((user) => {
        setUser(user)
        setIsLoggedIn(true)
      })
      .catch((error) => {
        throw new Error('Error', error.message)
      })
      .finally(() => {
        setIsLoading(false)
      })
  }, [])

  return (
    <UserInfoContext.Provider value={{
      isLoggedIn,
      user,
      isLoading,
      setIsLoggedIn,
      setUser,
      setIsLoading

    }}>
      {children}
    </UserInfoContext.Provider>
  )
}

export const useUserInfoContext = () => useContext(UserInfoContext) // Para abstraer el como usar el contexto
