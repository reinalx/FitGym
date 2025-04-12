import { View, Text, Image } from 'react-native'

import { styles } from '../styles'

const DisplayExercise = ({ name, description, muscleTarget, muscleGroup, picture, otherStyles }) => {
  // TODO: Cambiar imagen
  const image = require('../assets/exerciseImage/pressBench.jpg')

  return (
    <View
      className="flex-1 h-32 bg-slate-200 rounded-xl my-2 px-2 flex-row opacity-95 "
      style={styles.softShadowContainer}
    >
      <View className="ml-2 w-1/3 h-24 self-center">
        <Image
          source={image}
          className="w-24 h-24 rounded-xl"
          resizeMode="contain"
        />
      </View>

      <View className="w-2/3 mt-4">
        <Text className="text-2xl font-semibold ">{name}</Text>
        <Text className="text-md font">{description}</Text>
        <View className="flex-row justify-between mr-4">
          <Text className="text-xs font-light">Músculo objetivo</Text>
          <Text className="text-xs font-light">{muscleTarget}</Text>
        </View>
        <View className="flex-row justify-between mr-4">
          <Text className="text-xs font-light">Grupo muscular</Text>
          <Text className="text-xs font-light">{muscleGroup}</Text>
        </View>
      </View>
    </View>
  )
}

export default DisplayExercise
