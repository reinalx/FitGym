import { Stack } from 'expo-router'
import { SafeAreaProvider } from 'react-native-safe-area-context'

const AuthLayout = () => {
  return (
    <SafeAreaProvider>
      <Stack>
        <Stack.Screen name='signIn' options={{ headerShown: false }} />
        <Stack.Screen name='signUp' options={{ headerShown: false }} />
      </Stack>
    </SafeAreaProvider>
  )
}

export default AuthLayout
