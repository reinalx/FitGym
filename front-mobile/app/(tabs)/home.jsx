import { View, Text, ScrollView } from 'react-native'

import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { useUserInfoContext } from '../../context/UserInfoContext'
import DisplayDays from '../../components/DisplayDays'
import DisplayDailyRoutine from '../../components/DisplayDailyRoutine'
import { workouts } from '../../fake/workout'

const home = () => {
  const insets = useSafeAreaInsets()
  const { user } = useUserInfoContext()

  return (
    <View className=" w-full h-full" style={{ paddingTop: insets.top }}>
      <ScrollView
        contentContainerStyle={{ backgroundColor: '#f1f5f9' }}
      >
        <View className="  h-44 flex-row items-center p-4 ">
          <Text className=" font-bold text-4xl text-primary">
            Hola {user?.userName}
          </Text>
        </View>
        <View className="w-full h-14">
          <DisplayDays />
        </View>
        <View className="w-full m-4">
          <Text className=" font-semibold text-2xl text-primary">
            Esta es tu rutina de rutina de hoy
          </Text>
        </View>
        <View className=" ">
          <DisplayDailyRoutine
            name="Push"
            description="Rutina centrada para los musculos encargados de realizar los empujes"
            workouts={workouts}
          />
        </View>

      </ScrollView>
    </View>
  )
}

export default home
