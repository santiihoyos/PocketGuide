# PocketGuide Android 
[![CI](https://github.com/santiihoyos/PocketGuide/actions/workflows/test%20for%20all%20build%20variants%20and%20flavors.yml/badge.svg?branch=master)](https://github.com/santiihoyos/PocketGuide/actions/workflows/test%20for%20all%20build%20variants%20and%20flavors.yml)
[![android-studio](https://img.shields.io/badge/4.1.3-success.svg?style=flat&logo=android-studio&label=Android%20Studio)](https://developer.android.com/studio/)
[![kotlin](https://img.shields.io/badge/1.4.31-success.svg?style=flat&logo=kotlin&label=Kotlin)](https://github.com/JetBrains/kotlin/releases/tag/v1.4.10)
[![gradle-plugin](https://img.shields.io/badge/4.1.3-success.svg?style=flat&logo=gradle&label=Gradle%20Plugin)](https://developer.android.com/studio/releases/gradle-plugin)

### Code challenges for: 
  - Paradigma Digital (flavor for Rick&MortyApi)
  - OpenBank (flavor for MarvelApi)

#### with ❤️ by @santiihoyos

###### (Translate to English incoming...)

Este repositorio contiene la aplicación Android "PocketGuide" esta aplicación permite al usuario consultar las apis de http://rickandmortyapi.com y https://developer.marvel.com

## Get started!

⚠️ Attention: For **marvelDebug** or **marvelRelease** builds target you need setup this props into **local.properties**
> marvel.apiKey="here_your_api_key_from_developer.marvel.com"
> marvel.privateKey="here_your_private_key_from_developer.marvel.com"

## Vídeo Demo en YouTube
[![SC2 Video](https://user-images.githubusercontent.com/10730150/114312120-d36f1380-9af1-11eb-89b9-8378e84dee5b.png)](https://www.youtube.com/watch?v=2vfhOPgtKeQ "SC2 Mini game - Click to Watch!")

## Resumen de la arquitectura (MVVM)

### Gráfico de MVVM
![arquitectura](https://user-images.githubusercontent.com/10730150/113680141-d6689f00-96c0-11eb-979d-a0aed945d296.jpg)

Esta arquitectura esta pensada para que la capa vista sea totalmente agnóstica a la implementación del viewModel (la vista pide y ESCUCHA al viewModel y mediante la abstracción del ViewModel) es importante esto puesto que el viewModel no debe tener referencias a la vista y mucho menos llamar a ninguna función en la capa de vista. Para devolver los datos a la vista se usa LiveData o Flows o directamente en el return de la función puesto que **trabajamos con corutinas y nos permite una programación asíncrona con una sintaxis síncrona.**

Los ViewModels tienen como dependencia un Interactor que implementan cada uno su contrato en función de la pantalla en la que estén e interactúan con la capa de datos a través de los UseCases; estos últimos su utilidad es encapsular los posibles errores de la api y no exponer HttpExceptions por ejemplo al Interactor puesto que por ejemplo los modulos feature por ejemplo :characters no tienen retrofit como dependencia.

Los repositorios tienen su abstracción que define como deben comportarse y su implementación que usando una biblioteca se implementan. Por ejemplo para el caso de MarvelRestRepository.kt su implementación MarvelRestRpositoryRetrofitImpl.kt es su implementación usando Retrofit. El inyector solo expone la abstracción.


### Bibliotecas

 - Dagger2
 - Navigaton (Android JetPack)
 - ViewModel (Android JetPack)
 - LiveData  (Android JetPack)
 - Paging3   (Android JetPack)
 - Picasso
 - Mockito
 - Espresso
 - Retrofit
 - OkHttp
 - etc...

## Flavors & Build variants
El código está compuesto por dos build variants(debug y release) y 2 flavors(rickAndMorty y marvel) todos los modulos android tienen debug y release como build varants y algunos solo marvel como flavor o rickAndMorty o los 2 (como :characters) 

## Injector (Dagger2)
Cada módulo gradle del código contiene su propio componente y sus módulos dagger esto ayuda a abstraer todas las implementaciones y exponer solo clases abstractas o interfaces, incluso el componente ViewModelProvider.Factory de androidx está adaptado para funcionar con Dagger2 y ser provisto como una dependencia mas inyectable de la capa de vista.

## Modules summary

### :base
Este módulo contiene las clases base de los modulos "feature" para nuestro caso sería(:characters) es decir aquellos que contienen los casos de uso de la app para los distintos flavors. También es dependencia del módulo :app que es el punto de entrada de la aplicación y tiene 1 fragment de relleno (DummyFragment) y MainActivity, ambos siguen la arquitectura del resto de la app.

### :base_api
Contiene el código compartido entre los modulos api que son ":api_rickAndMorty" y ":api_marvel" que contienen las definiciones e implementaciones de los casos de uso de la api correspondiente a cada uno pero usando las clases bases de :base_api (éste).

### :api_rickAndMorty
Contiene la abstracción del repositorio de http://rickandmortyapi.com y la implementación que solo es accesible internamente y expuesta mediante los abstractos o interfaces con Dagger2.

### :api_marvel
Contiene la abstracción del repositorio de http://developer.marvel.com y la implementación que solo es accesible internamente y expuesta mediante los abstractos o interfaces con Dagger2.

### :api_keyvalue
Es un modulo que abstrae el concepto de guardar mediante pares clave valor mediante KeyValueRepository y con la implementación usa SharedPreferences de Android.

### :characters
Contiene las abstraciones de los casos de uso (listado y detalle) y sus implementaciones. Al tener ambos flavors tiene como dependencia dependeindo del falvor :api_rickAndMorty o :api_marvel. Esto obliga a que las impelementaciones entre los distintos flavors es distinta pero se puede reutilizar gran parte gracias a las abstraciones. Este modulo es un ejemplo de reutilización de codigo garcias a los flavors y la arquitectura basada en abstraciones.

### :app
Módulo de entrada a la app, además contiene un Fragment dummy para rellenar el nav y el MainActivity ambos como la misma estructura que el resto de la app. Tiene como dependencia dependiendo el flavor :characters-RickAndMorty o :characters-Marvel

## Tests
A modo de ejemplo he añadido 3 test sobre 3 disitntas caps de la app sobre el interactor(CharacterDetailInteractorImpl) y view model(CharacterDetailViewModelIImpl) y sobre la vista (CharacterDetailActivityTest) en el modulo :characters y para los flavors marvelDebug y rickAndMortyDebug. Nota: importante configurar emulador para lanzar los androidTests.

## CI (Integración continua)
Se ha creado un job en el workflow principal para pasar los test unitarios en cada commit que se haga sobre la rama default (master) o sobre otra rama con Pull request abierta. Se peude encontrar en el directorio **.github/workflows**
