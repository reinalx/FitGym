import { ScrollView, View, Text, StatusBar, Image } from 'react-native'
import React, { useState } from 'react'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import FormField from '../../components/FormField'
import { images } from '../../constants'
import CustomButton from '../../components/CustomButton'
import { Link, router } from 'expo-router'

const signIn = () => {
  const [form, setForm] = useState({
    email: '',
    password: ''
  })

  const [isSubmitting, setIsSubmitting] = useState(false)

  // TODO: REALIZAR EL LOGIN DE USUARIO
  const handleSubmit = () => {
    setIsSubmitting(true)
    setTimeout(() => {
      setIsSubmitting(false)
    }, 1000)
    router.replace('/home')
  }
  const insets = useSafeAreaInsets()

  // TODO: Añadir validación de formulario
  return (
    <ScrollView
      contentContainerStyle={{
        paddingTop: insets.top,
        flex: 1
      }}
    >
      <View className="h-full bg-slate-100">
        <View className=" w-full justify-between min-h-[85vh] px-4 my-6">
          <View className="flex-row  items-center">
            <Image
              source={images.logo}
              className="w-48 h-48 "
              resizeMode="contain"
            />
            <Text className="text-black text-4xl font-bold">FitGym</Text>
          </View>
          <Text className=" text-2xl  font-bold mt-10 font-psemibold">
            Iniciar sesión
          </Text>

          <FormField
            title="Email"
            placeholder="example@gmail.com"
            value={form.email}
            handleChangeText={(e) => setForm({ ...form, email: e })}
            otherStyles="mt-7"
            keyboardType="email-address"
          />

          <FormField
            title="Password"
            placeholder="********"
            value={form.password}
            handleChangeText={(e) => setForm({ ...form, password: e })}
            otherStyles="mt-7"
            keyboardType="default"
            showPassword={false}
          />

          <CustomButton
            title="Sign in"
            handlePress={handleSubmit}
            containerStyles="mt-7 w-full "
            textStyles={'text-white text-lg font-semibold'}
            isLoading={isSubmitting}
          />

          <View className="justify-center pt-5 flex-row gap-2">
            <Text className="text-lg text-gray-400 font-pregular ">
              Don't have an account?
            </Text>
            <Link
              href="/signUp"
              className="text-lg font-psemibold text-secondary"
            >
              Sign up
            </Link>
          </View>
        </View>
      </View>

      <StatusBar backgroundColor="#D21A2F" style="light" />
    </ScrollView>
  )
}

export default signIn
