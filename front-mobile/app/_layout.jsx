import { Stack } from 'expo-router'
// <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
// <Stack.Screen name="(auth)" options={{ headerShown: false }} />
import '../global.css'
const RootLayout = () => {
  return (
    <Stack>
      <Stack.Screen name="index" options={{ headerShown: false }} />

    </Stack>
  )
}

export default RootLayout
