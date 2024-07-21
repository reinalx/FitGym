import { View, Text, ScrollView } from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'
import LinearGradient from 'react-native-linear-gradient'

const App2 = () => {
  return (
    <SafeAreaView className="bg-slate-950 w-full h-full">
      <ScrollView contentContainerStyle={{ height: '100%' }}>
        <Text className="bg-sky-500">Pija</Text>
      </ScrollView>
    </SafeAreaView>
  )
}

export default App2
