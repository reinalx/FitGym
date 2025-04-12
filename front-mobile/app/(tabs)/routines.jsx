import { View, Text, Pressable, Image, ScrollView, FlatList } from 'react-native'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { icons } from '../../constants'
import FieldInput from '../../components/FieldInput'
import { useEffect, useState } from 'react'
import { getRoutines } from '../../backend/routineService'
import DisplayRoutine from '../../components/DisplayRoutine'

const Routines = () => {
  const inset = useSafeAreaInsets()

  const [refreshing, setRefreshing] = useState(false)
  const [routines, setRoutines] = useState([])
  const [query, setQuery] = useState('')

  useEffect(() => {
    setRefreshing(true)
    getRoutines(1, query)
      .then((res) => {
        setRoutines(res)
      })
      .catch((err) => console.log(err))
      .finally(() => setRefreshing(false))
  }, [query])
  console.log(routines)
  return (
    <View className="w-full h-full" style={{ paddingTop: inset.top }}>
      <View className="flex-row  justify-between my-8 mx-4">
        <Text className="text-primary text-4xl font-bold mb-4">Routines</Text>
        <Pressable className="mr-4 mt-2">
          <Image
            source={icons.search}
            tintColor={'#D21A2F'}
            className="w-6 h-6"
            resizeMode="contain"
          />
        </Pressable>
      </View>
      <View>
        <FlatList
          data={routines}
          keyExtractor={(item) => item.id}
          renderItem={({ item }) => <DisplayRoutine routine={item} />}
          refreshing={refreshing}
          onRefresh={() => {
            setRefreshing(true)
            getRoutines(1, query)
              .then((res) => {
                setRoutines(res)
              })
              .catch((err) => console.log(err))
              .finally(() => setRefreshing(false))
          }}
          stickyHeaderHiddenOnScroll={true}
          ListHeaderComponent={
            <View className="mx-4 mb-8">
              <FieldInput
                title="Nombre"
                placeholder="Push, Pull, etc"
                value={query}
                handleChangeText={(e) => setQuery(e)}
                otherStyles="mt-7"
                keyboardType="email-address"
              />
            </View>
          }
          ListFooterComponent={
            <View className="flex-row justify-center">
              <Text className="text-center text-gray-400 text-md h-44 ">
                No hay más resultados
              </Text>
            </View>
          }
        />
      </View>
    </View>
  )
}

export default Routines
