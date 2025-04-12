import images from '../constants/images'

export async function getExercises (exerciseId, name, muscleTarget, muscleGroup, page, size) {
  return {
    items: [
      {
        id: 1,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      },
      {
        id: 2,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      },
      {
        id: 3,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      },
      {
        id: 4,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      },
      {
        id: 5,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      },
      {
        id: 6,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      },
      {
        id: 7,
        name: 'Push',
        description:
          'Rutina centrada para los musculos encargados de realizar los empujes',
        muscleTarget: 'Pecho',
        muscleGroup: 'Empuje',
        picture: '../assets/exerciseImage/pressBench.jpg'
      }
    ],
    existMoreItems: false
  }
}

export async function getExerciseById (exerciseId) {
  return {
    id: 1,
    name: 'Push',
    description: 'Rutina centrada para los musculos encargados de realizar los empujes',
    muscleTarget: 'Muscular',
    muscleGroup: 'Front'
  }
}
