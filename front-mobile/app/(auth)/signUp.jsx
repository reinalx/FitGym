import { View, Text, ScrollView, Image } from 'react-native'

import FormField from '../../components/FormField'
import CustomButton from '../../components/CustomButton'

import { useState } from 'react'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { StatusBar } from 'expo-status-bar'

import { images } from '../../constants'
import { Link, router } from 'expo-router'

const signUp = () => {
  const [form, setForm] = useState({
    userName: '',
    password: '',
    email: '',
    firstName: '',
    lastName: ''
  })

  // TODO
  const [isSubmitting, setIsSubmitting] = useState(false)
  const handleSubmit = () => {
    console.log('Enviar')
  }
  const insets = useSafeAreaInsets()
  return (
    <ScrollView
      contentContainerStyle={{
        paddingTop: insets.top

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
            title="Nombre"
            placeholder="Joaquin"
            value={form.firstName}
            handleChangeText={(e) => setForm({ ...form, firstName: e })}
            otherStyles="mt-7"
            keyboardType="email-address"
          />
          <FormField
            title="Apellido"
            placeholder="Gonzalez"
            value={form.lastName}
            handleChangeText={(e) => setForm({ ...form, lastName: e })}
            otherStyles="mt-7"
            keyboardType="email-address"
          />

          <FormField
            title="Nombre de usuario"
            placeholder="joaquin29"
            value={form.userName}
            handleChangeText={(e) => setForm({ ...form, email: e })}
            otherStyles="mt-7"
            keyboardType="email-address"
          />

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
            <Text className="text-lg text-gray-100 font-pregular ">
              Already have an account?
            </Text>
            <Link
              href="/signIn"
              className="text-lg font-psemibold text-secondary"
            >
              Sign in
            </Link>
          </View>
        </View>
      </View>
      <StatusBar backgroundColor="#D21A2F" style="light" />
    </ScrollView>
  )
}

export default signUp
