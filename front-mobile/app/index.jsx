import { View, Text, ScrollView, Image } from 'react-native'
import * as NavigationBar from 'expo-navigation-bar'
import { StatusBar } from 'expo-status-bar'
import CustomButton from '../components/CustomButton'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { router } from 'expo-router'
import { useEffect } from 'react'

const App = () => {
  NavigationBar.setBackgroundColorAsync('#D21A2F')
  NavigationBar.setButtonStyleAsync('light')
  NavigationBar.setVisibilityAsync('hidden')

  const insets = useSafeAreaInsets()

  return (
    <View className=" w-full h-full" style={{ paddingTop: insets.top }}>
      <ScrollView
        contentContainerStyle={{ height: '100%', backgroundColor: '#e2e8f0' }}
      >
        <View className=" justify-around items-center flex-1">
          <View className="flex-row justify-center items-center bg-primary w-full">
            <Image
              source={require('../assets/logo_empty.png')}
              className="w-24 h-24"
            />
            <Text className="text-slate-100 text-4xl font-bold">FitGym</Text>
          </View>

          <View className="  justify-around items-center w-full h-[89%] gap-16 bg-slate-200">
            <View className="flex-row items-center">
              <Image
                source={require('../assets/girlWorkout.jpg')}
                className=" w-52 h-96 object-fill border-2 border-slate-400 rounded-2xl  border-opacity-5"
              />
              <Image
                source={require('../assets/home1.jpg')}
                className=" w-48 h-96 m-2 object-fill rounded-2xl border-2 border-slate-400 border-opacity-5"
              />
            </View>

            <Text className="text-slate-800 text-2xl text-center  leading-6 font-semibold max-w-96">
              ¿Listo para transformar tu forma de entrenar? Únete a nuestra
              comunidad y alcanza tus metas con FitGym.
            </Text>
            <CustomButton
              title="¡Comenzar!"
              handlePress={() => router.push('/(auth)/signIn')
              }
              containerStyles="bg-[#D21A2F] opacity-95 rounded-full px-4 py-2
              text-white w-96"
              textStyles="text-2xl text-slate-100 font-bold"
            />
          </View>
        </View>
      </ScrollView>

      <StatusBar backgroundColor="#D21A2F" style="light" />
    </View>
  )
}

export default App
