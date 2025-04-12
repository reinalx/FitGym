import { View, Text } from 'react-native'
import React from 'react'

const DisplayWorkout = ({ workout, index }) => {
  return (
    <View key={index} className="w-full h-14 m-2 flex-row justify-between">
      <View>
        <Text className="text-white text-lg font-bold">
          {workout.exercise.name}
        </Text>
        <Text className="text-slate-200 text-s font-normal">
          {workout.exercise.muscleTarget}
        </Text>
      </View>

      <View>
        <Text className="text-white text-s font-bold self-end">
          {workout.targetSets} x {workout.targetReps}
        </Text>

        <Text className="text-slate-200 text-s font-normal">
          Target Weight: {workout.targetKg} Kg
        </Text>
      </View>
    </View>
  )
}

export default DisplayWorkout
