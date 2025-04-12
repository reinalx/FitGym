import { configureStore } from '@reduxjs/toolkit'
import usersReducer from './users/slice'

// TODO: Gestionar el estado de la app

export const store = configureStore({
  reducer: {
    users: usersReducer
  }
})
