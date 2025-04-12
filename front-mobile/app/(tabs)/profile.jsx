import { View, Text, ScrollView, Image } from 'react-native'
import { useSafeAreaInsets } from 'react-native-safe-area-context'
import { useUserInfoContext } from '../../context/UserInfoContext'
import { icons, images } from '../../constants'
import { softShadowContainer } from '../../styles/shadows'
import GridBox from '../../components/GridBox'

const Profile = () => {
  const inset = useSafeAreaInsets()
  const { user } = useUserInfoContext()

  return (
    <View className="w-full h-full" style={{ flex: 1, padding: inset.top }}>
      <View className="mt-8">
        <View className="flex-row h-20 w-full justify-between  ">
          <Image
            source={icons.settings}
            className="w-8 h-8"
            tintColor={'#D21A2F'}
          />
          <Image
            source={icons.logOut}
            tintColor={'#D21A2F'}
            className="w-8 h-8"
          />
        </View>
        <ScrollView

        >
          <View className=" items-center flex-1 grid">
            <View className="mb-4" style={softShadowContainer}>
              <Image
                className="w-40 h-40 rounded-full border-2 border-primary"
                source={images.profilePicture}
              />
            </View>
            <Text className=" text-4xl font-bold ">{user?.userName}</Text>
          </View>
          <GridBox>
            <Text>Prueba</Text>
          </GridBox>
        </ScrollView>
      </View>
    </View>
  )
}

export default Profile
