# PocketGuide Android

### Code challenges for: 
  - Paradigma Digital (flavor for Rick&MortyApi)
  - OpenBank (flavor for MarvelApi)

#### with ❤️ by @santiihoyos

###### (Translate to English incoming...)

Este repositorio contiene la aplicación Android "PocketGuide" esta aplicación permite al usuario consultar las apis de http://rickandmortyapi.com y https://developer.marvel.com


## Resumen de la arquitectura (MVVM)

### Grafico de MVVM
![arquitectura](https://user-images.githubusercontent.com/10730150/113680141-d6689f00-96c0-11eb-979d-a0aed945d296.jpg)



### Libraries
 - Dagger2
 - Navigaton (Android JetPack)
 - ViewModel (Android JetPack)
 - LiveData  (Android JetPack)
 - Picasso
 - Mockito
 - Espresso
 - etc...

## Flavors & Build variants
El código está compuesto por dos build variants(debug y release) y 2 flavors(rickAndMorty y marvel) no todos los modulos contienen ambos flavors incluso pueden no tener alguno.

## Injector
Cada módulo gradle del código contiene su propio componente y sus módulos dagger que ayuda a abstraer todas las implementaciones y exponer solo clases abstractas o interfaces, incluso el componente ViewModel.Provider de androidx está adaptado para funcionar con Dagger2.

## Módulos

### :base
Este módulo contiene las clases base de los modulos "feature_*"(:characters & :heroes) es decir aquellos que contienen los casos de uso de la app para los distintos flavors. También es dependencia del módulo :app que es el punto de entrada de la aplicación.

### :base_api
Contiene el código compartido entre los modulos api que son ":api_rickAndMorty" y ":api_marvel" y contienen las definiciones e implementaciones de los casos de uso de la api correspondiente a cada uno.

### :api_rickAndMorty
Contiene el código la definicion del repositorio de http://rickandmortyapi.com y la implementación que solo es accesible internamente y expuesta mediante los abstractos o interfaces con Dagger2.

### :api_rickAndMorty
Contiene el código la definicion del repositorio de https://developer.marvel.com y la implementación que solo es accesible internamente y expuesta mediante los abstractos o interfaces con Dagger2.

### :api_keyvalue
Es un modulo que abstrae el concepto de guardar mediante pares clave valor mediante KeyValueRepository y con una implementación usando SharedPreferences de Android.

### :characters
Contiene las abstraciones de los casos de uso y sus implementaciones implementa un listado de characters y un pequeño detella donde se puede marcar a un personaje como favorito.

### :heroes
En implementación y será parecido a :characters pero para la api de marvel.

### :app
Módulo de entrada a la app, además contiene un Fragment dummy para rellenar el nav y el Main ambos como la misma estructura que el resto de la app.

## Tests
A modo de ejemplo he añadido 3 test sobre 3 disitntas caps de la app sobre el interactor(CharacterDetailInteractorImpl) y view model(CharacterDetailViewModelIImpl) y sobre la vista (CharacterDetailActivityTest) en el modulo :characters
