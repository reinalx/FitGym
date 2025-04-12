import { View, Text } from 'react-native'
import { styles } from '../styles'
import DisplayWorkout from './DisplayWorkout'

// AÑADIR GRADED COLORS Y CONVERTIR EN PRESSABLE
const DisplayDailyRoutine = ({ name, description, workouts }) => {
  return (
    <View
      className="m-2 rounded-2xl bg-blue-500 "
      style={styles.shadowContainer}
    >
      <View className="m-4">
        <View>
          <Text className="text-white text-4xl font-bold">{name}</Text>
          <Text className="text-slate-200 text-l font-normal">
            {description}
          </Text>
        </View>
        <View className="m-2">
          {workouts.map((workout, index) => {
            return (
              <DisplayWorkout workout={workout} key={index} />
            )
          })}
        </View>
      </View>
    </View>
  )
}

export default DisplayDailyRoutine
