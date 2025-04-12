import { View, Text, FlatList, RefreshControl } from 'react-native'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { useEffect, useState } from 'react'
import EmptyState from '../../components/EmptyState'
import { getExercises } from '../../backend/exerciseService'
import HeaderSearchExercise from '../../components/HeaderSearchExercise'
import DisplayExercise from '../../components/DisplayExercise'

const Exercises = () => {
  const inset = useSafeAreaInsets()

  const [refreshing, setRefreshing] = useState(false)
  const [exercises, setExercises] = useState([])
  const [existMoreItems, setExistMoreItems] = useState(false)

  const [searchQuery, setSearchQuery] = useState({
    name: '',
    muscleTarget: '',
    muscleGroup: ''
  })

  const [page, setPage] = useState(0)
  const [size, setSize] = useState(20)

  useEffect(() => {
    getExercises(searchQuery.name, searchQuery.muscleTarget, searchQuery.muscleGroup, page, size)
      .then((res) => {
        setExercises(res.items)
        setExistMoreItems(res.existMoreItems)
      })
      .catch((err) => console.log(err))
  }, [searchQuery, page, size])

  const handleOnRefresh = () => {
    setRefreshing(true)
    getExercises(searchQuery.name, searchQuery.muscleTarget, searchQuery.muscleGroup, page, size)
      .then((res) => {
        setExercises(res.items)
        setExistMoreItems(res.existMoreItems)
      })
      .catch((err) => console.log(err))
      .finally(() => setRefreshing(false))
  }

  // ARREGLAR QUE EL CONTENIDO SE QUEDA DEBAJO DEL TAB
  // APLICAR UN DESPLEGABLE CON ANIMACIÓN
  return (
    <View
      className="w-full h-full"
      style={{ paddingTop: inset.top, paddingBottom: inset.bottom }}
    >
      <View className="mt-8 mx-4">
        <Text className="text-primary text-4xl font-bold mb-4">Ejercicios</Text>

        <FlatList
          data={exercises}
          keyExtractor={(item) => item.id}
          stickyHeaderHiddenOnScroll={true}
          ItemSeparatorComponent={() => (
            <View className="h-0.5 bg-slate-200 opacity-40" />
          )}
          renderItem={({ item }) => (
            <DisplayExercise
              name={item.name}
              description={item.description}
              muscleTarget={item.muscleTarget}
              muscleGroup={item.muscleGroup}
              picture={item.picture}
              imageStyles=""
            />
          )}
          ListHeaderComponent={() => {
            return (
              <HeaderSearchExercise
                searchQuery={searchQuery}
                setSearchQuery={setSearchQuery}
              />
            )
          }}
          ListEmptyComponent={() => {
            return (
              <EmptyState
                title="No hay ejercicios"
                subTitle="Añade un ejercicio para comenzar o vuelve a intentarlo más tarde"
                titleButton="Añadir ejercicio"
                handlePress="/create"
              />
            )
          }}
          ListFooterComponent={() => {
            return <View className="h-24" />
          }}
          refreshControl={
            <RefreshControl refreshing={refreshing} onRefresh={handleOnRefresh} />
          }
        />
      </View>
    </View>
  )
}

export default Exercises
