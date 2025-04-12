import { View, Text, Pressable } from 'react-native'
import React, { useState } from 'react'

const PressableDay = ({ title, isSelected }) => {
  const [isPressed, setIsPressed] = useState(isSelected)
  const text = () => {
    switch (title) {
      case 'Monday':
        return 'M'
      case 'Tuesday':
        return 'Tu'
      case 'Wednesday':
        return 'W'
      case 'Thursday':
        return 'Th'
      case 'Friday':
        return 'F'
      case 'Saturday':
        return 'Sa'
      case 'Sunday':
        return 'Su'
      default:
        return 'N'
    }
  }
  return (
    <Pressable className="flex-1 ">
      <Text
        className={`${isPressed ? 'font-bold text-white bg-primary rounded-full text-s' : 'font-normal text-slate-600 text-xs'} p-4 text-center `}
      >
        {text()}
      </Text>
    </Pressable>
  )
}

export default PressableDay
