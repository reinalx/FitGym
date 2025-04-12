import { View, Dimensions } from 'react-native'

const GridBox = ({ numColums = 2, children }) => {
  const WIDTH = Dimensions.get('window').width
  const size = WIDTH / numColums
  return (
    <View className={`h-[${size}px] w-[196px] bg-black-100`}>
      {children}
    </View>
  )
}

export default GridBox
