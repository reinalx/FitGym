import { View, Text, TextInput, TouchableOpacity, Image } from 'react-native'
import { useState } from 'react'

import { icons } from '../constants'
import { styles } from '../styles'

const FormField = ({
  title,
  placeholder,
  value,
  handleChangeText,
  inputStyles,
  otherStyles,
  keyboardType
}) => {
  const [showPassword, setShowPassword] = useState(false)

  return (
    <View className={`space-y-2 ${otherStyles}`} >
      <Text className="text-base mb-2 text-gray-800 font-pmedium">{title}</Text>
      <View
        className="border-2 border-slate-300 w-full h-16 px-4 bg-slate-200 rounded-2xl
       focus:border-primary items-center flex-row  " style={styles.softShadowContainer}
      >
        <TextInput
          className="flex-1 text-white font-psemibold text-base"
          value={value}
          onChangeText={handleChangeText}
          placeholder={placeholder}
          placeholderTextColor="#7b7b8b"
          keyboardType={keyboardType}
          secureTextEntry={title === 'Password' && !showPassword}
        />
        {title === 'Password' && (
          <TouchableOpacity onPress={() => setShowPassword(!showPassword)}>
            <Image
              source={!showPassword ? icons.eye : icons.eyeHide}
              className="w-6 h-6 "
              resizeMode="contain"
            />
          </TouchableOpacity>
        )}
      </View>
    </View>
  )
}

export default FormField
