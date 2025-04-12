import { View, Text, Pressable, Alert } from 'react-native'
import React from 'react'
import { shadowContainer } from '../styles/shadows'

const DisplayRoutine = ({ routine }) => {
  return (
    <Pressable
    >
      <View className="bg-gray-200 my-3 mx-4 p-6 rounded-full" style={shadowContainer}>
          <Text className=" text-3xl font-semibold">
              {routine?.name}
          </Text>
          <Text className="text-slate-600 text-sm">
              {routine?.description}
          </Text>
      </View>
    </Pressable>
  )
}

export default DisplayRoutine
