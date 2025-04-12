import { View, Text, Pressable } from 'react-native'
import PressableDay from './PressableDay'
import { StyleSheet } from 'nativewind'
import { styles } from '../styles'

const DisplayDays = () => {
  return (
    <View className="ml-2 mr-2 flex-1 h-14  items-center justify-center">
      <View
        className=" w-full h-14  border-2 border-primary rounded-full bg-slate-100 flex-row justify-around items-center "
        style={styles.shadowContainer}
        >
        <PressableDay title="Monday" isSelected={true} />
        <PressableDay title="Tuesday" isSelected={false} />
        <PressableDay title="Wednesday" isSelected={false} />
        <PressableDay title="Thursday" isSelected={false} />
        <PressableDay title="Friday" isSelected={false} />
        <PressableDay title="Saturday" isSelected={false} />
        <PressableDay title="Sunday" isSelected={false} />
      </View>
    </View>
  )
}

export default DisplayDays
