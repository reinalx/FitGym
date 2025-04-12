import { View, TextInput } from 'react-native'
import { useState } from 'react'

const FieldInput = ({ initialKeyword }) => {
  const [query, setQuery] = useState(initialKeyword || '')
  return (
    <View
      className="border-2 border-primary w-full h-16 px-4 rounded-2xl
       focus:border-secondary-200 items-center flex-row space-x-4  "
    >
      <TextInput
        className="text-base mt-0.5 flex-1 text-blackfont-pregular"
        value={query}
        onChangeText={(e) => setQuery(e)}
        placeholder="Search for a exercise"
        placeholderTextColor="#94a3b8"
      />
    </View>
  )
}

export default FieldInput
