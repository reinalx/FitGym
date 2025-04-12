import { View } from 'react-native'
import FormField from './FormField'

const HeaderSearchExercise = ({ searchQuery, setSearchQuery }) => {
  return (
     <View className="mb-4">
          <FormField
            title="Nombre"
            placeholder="Ejercicio"
            value={searchQuery.name}
            handleChangeText={(e) => setSearchQuery({ ...searchQuery, name: e })}
            otherStyles="mt-7"
          />
          <View className="flex-row space-x-4 justify-between">
            <FormField
              title="Musculo objetivo"
              placeholder="pecho"
              value={searchQuery.muscleTarget}
              handleChangeText={(e) => setSearchQuery({ ...searchQuery, muscleTarget: e })}
              otherStyles="mt-7 w-2/5 "
            />
            <FormField
              title="Grupo muscular"
              placeholder="Empuje"
              value={searchQuery.muscleGroup}
              handleChangeText={(e) => setSearchQuery({ ...searchQuery, muscleGroup: e })}
              otherStyles="mt-7 w-1/2"
            />

          </View>
    </View>
  )
}

export default HeaderSearchExercise
