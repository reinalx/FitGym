import { View, Text, Image } from 'react-native'
import { Tabs } from 'expo-router'
import { icons } from '../../constants'
import { StatusBar } from 'expo-status-bar'

// eslint-disable-next-line react/prop-types
const TabsIcon = ({ icon, name, color, focused }) => {
  return (
        <View className="flex items-center justify-center">
            <Image source={icon}
            resizeMode="contain"
            tintColor={color}
            className="w-8 h-8"
            />
            <Text
            className={`${focused ? 'font-psemibold' : 'font-pregular'} text-xs `}
            style={{ color }} >
                {name}
            </Text>
        </View>
  )
}

const TabsLayout = () => {
  return (
    <>
      <Tabs
        screenOptions={{
          tabBarShowLabel: false,
          tabBarActiveTintColor: '#D21A2F',
          tabBarInactiveTintColor: '#161622',
          tabBarStyle: {
            backgroundColor: '#cbd5e1',
            borderTopColor: '#94a3b8',
            borderTopWidth: 0,
            height: 92
          }
        }}
      >
        <Tabs.Screen
          name="home"
          options={{
            title: 'Home',
            headerShown: false,
            tabBarIcon: ({ color, focused }) => (
              <TabsIcon
                icon={icons.home}
                name="home"
                color={color}
                focused={focused}
              />
            )
          }}
        />

        <Tabs.Screen
          name="exercises"
          options={{
            title: 'Exercises',
            headerShown: false,
            tabBarIcon: ({ color, focused }) => (
              <TabsIcon
                icon={icons.exercises}
                name="exercises"
                color={color}
                focused={focused}
              />
            )
          }}
        />

        <Tabs.Screen
          name="routines"
          options={{
            title: 'Routines',
            headerShown: false,
            tabBarIcon: ({ color, focused }) => (
              <TabsIcon
                icon={icons.routines}
                name="routines"
                color={color}
                focused={focused}
              />
            )
          }}
        />

        <Tabs.Screen
          name="profile"
          options={{
            title: 'Profile',
            headerShown: false,
            tabBarIcon: ({ color, focused }) => (
              <TabsIcon
                icon={icons.profile}
                name="profile"
                color={color}
                focused={focused}
              />
            )
          }}
        />
      </Tabs>
      <StatusBar backgroundColor="#D21A2F" style="light" />
    </>
  )
}

export default TabsLayout
