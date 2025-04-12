import { TouchableOpacity, Text, ActivityIndicator } from 'react-native'

const CustomButton = ({
  title,
  handlePress,
  containerStyles,
  textStyles,
  isLoading
}) => {
  return (
    <TouchableOpacity
      onPress={handlePress /* Funcion que se ejecuta cuando se presiona */}
      activeOpacity={0.7 /* Opacity de la imagen cuando se presiona */}
      disabled={isLoading /* Disabilita el boton cuando se está cargando */}
      className={`bg-primary rounded-xl min-h-[62px] justify-center items-center 
      ${containerStyles}
      ${isLoading ? 'opacity-50' : ''}`}
    >
      <Text className={` font-psemibold text-lg ${textStyles}`}>
        {isLoading ? <ActivityIndicator size="small" color="#FF9C01" /> : title}
      </Text>
    </TouchableOpacity>
  )
}

export default CustomButton
