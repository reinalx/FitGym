import { View, Text, Image } from 'react-native'
import { images } from '../constants'
import { router } from 'expo-router'
import CustomButton from './CustomButton'

const EmptyState = ({ title, subTitle, titleButton, handlePress }) => {
  return (
    <View className="justify-center items-center px-4">
      <Image
        source={images.sad}
        className="w-[270px] h-[215px] "
        resizeMode="contain"
      />
      <Text className="text-xl text-center font-psemibold text-white mt-2">
        {title}
      </Text>
      <Text className="font-pmedium text-sm text-gray-100 ">{subTitle}</Text>

      <CustomButton
        title={titleButton}
        handlePress={() => router.push(handlePress || '/create')}
        containerStyles="w-full my-5 "
      />
    </View>
  )
}

export default EmptyState
