<h1 align="center">Weatherens</h1>

<p align="center">
  <a href="http://developer.android.com/index.html"><img alt="API" src="https://img.shields.io/badge/platform-Android-green.svg"/></a>
  <a href="https://android-arsenal.com/api?level=22"><img alt="API" src="https://img.shields.io/badge/API-22%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

<p align="center">  
Weatherens is a sample project made by me with a training purpose.
The target was to use and adopt modern android app architecture principles, libraries and approaches together with the pure Kotlin code in a single place.
You can use the project as you want in any purpose and feel free to contact me if you have any questions or proposals regarding to the project
</p>
</br>

## Architecture
Weatherens is based on MVVM architecture and a repository pattern.

![final-architecture](https://user-images.githubusercontent.com/78301962/107705428-c2f81380-6ccf-11eb-8a4c-3f74d2d59293.png)

## Main libraries used
- Minimum SDK level 22
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for concurrency.
- Hilt for DI.
- JetPack
  - LiveData - notify views from domain using observables
  - Lifecycle - dispose of observing data when lifecycle state changes
  - ViewModel - UI related lifecycle aware holder
  - Room - ORM
- [Retrofit2](https://github.com/square/retrofit) - construct the REST API

## API
Weatherens using the [Weather API](https://www.weatherapi.com/) to get weather data for cities<br>
API key was removed from the project, if you want to use the API you should create your own key and set it as WeatherensConstants - WEATHER_API_KEY value

## License
```xml
Designed and developed by 2021 akrnch (Aleksey Karankevich)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
