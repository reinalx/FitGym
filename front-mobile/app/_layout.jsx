import '../global.css'
import { Stack, SplashScreen } from 'expo-router'
import { SafeAreaProvider } from 'react-native-safe-area-context'
import { UserInfoProvider } from '../context/UserInfoContext'
import { useFonts } from 'expo-font'
import { useEffect } from 'react'
import { StatusBar } from 'expo-status-bar'

const RootLayout = () => {
  const [fontsLoaded, error] = useFonts({
    // Cargamos las fuentes que vamos a usar
    'Poppins-Black': require('../assets/fonts/Poppins-Black.ttf'),
    'Poppins-Bold': require('../assets/fonts/Poppins-Bold.ttf'),
    'Poppins-ExtraBold': require('../assets/fonts/Poppins-ExtraBold.ttf'),
    'Poppins-ExtraLight': require('../assets/fonts/Poppins-ExtraLight.ttf'),
    'Poppins-Light': require('../assets/fonts/Poppins-Light.ttf'),
    'Poppins-Medium': require('../assets/fonts/Poppins-Medium.ttf'),
    'Poppins-Regular': require('../assets/fonts/Poppins-Regular.ttf'),
    'Poppins-SemiBold': require('../assets/fonts/Poppins-SemiBold.ttf'),
    'Poppins-Thin': require('../assets/fonts/Poppins-Thin.ttf')
  })
  useEffect(() => {
    if (error) throw error
    if (fontsLoaded) SplashScreen.hideAsync()
  }, [fontsLoaded, error])

  if (!fontsLoaded && !error) return null // Esto lo añado por si acaso no se carga el font o hay errores

  return (
    <SafeAreaProvider>
      <UserInfoProvider>
        <Stack>
          <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
          <Stack.Screen name="index" options={{ headerShown: false }} />
          <Stack.Screen name="(auth)" options={{ headerShown: false }} />
        </Stack>
        <StatusBar backgroundColor="#e2e8f0" style="light" />
      </UserInfoProvider>
    </SafeAreaProvider>
  )
}

export default RootLayout
