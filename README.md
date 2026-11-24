# NetLib для приложения «Хвост»

Android-библиотека для записи питомцев к ветеринару. Модуль `:NetLib` реализует 16 REST-операций спринта А и клиент протокола WebSocket спринта Г. Приложение использует интерфейсы репозиториев и WSClient из пакета domain. Сетевые DTO, разбор ошибок, преобразования и Android-хранилище находятся в data.

## Сборка и подключение

1. Откройте проект в Android Studio с JDK 25 и Android SDK 37.
2. Соберите библиотеку и демонстрационное приложение командой `./gradlew :NetLib:assembleRelease :app:assembleDebug`. Минимальная версия Android — 14.
3. Для подключения исходного модуля добавьте `include(":NetLib")` в settings.gradle.kts и `implementation(project(":NetLib"))` в зависимости приложения. Модуль использует каталог версий из этого проекта.
4. Запустите PocketBase и realtime-сервер из комплекта «Хвост». Для эмулятора используйте адреса ниже. При запуске на телефоне подставьте IP компьютера. Отладочная сборка приложения разрешает HTTP.

```kotlin
val net = HvostNetLib(applicationContext, "http://10.0.2.2:8090/api/", "ws://10.0.2.2:8091/ws")
```

REST использует Ktor Client с движком OkHttp и kotlinx.serialization. Репозитории возвращают NetworkResult. После входа библиотека сохраняет токен в SharedPreferences. Для событий используйте Flow из Kotlin coroutines: начните сбор realtimeClient.events перед connect(), а при выходе вызовите disconnect() и очистите токен через authRepository.saveToken(null). Метод net.close() закрывает соединение и HTTP-клиент.

## Проверка

- Модульные тесты: `./gradlew :NetLib:testDebugUnitTest`.
- Проверка SharedPreferences на подключенном устройстве: `./gradlew :NetLib:connectedDebugAndroidTest`.
- Сквозной тест создает пользователя в отдельной локальной тестовой базе, проверяет профиль и загрузку файлов, создает питомца и запись, запрашивает очередь, отменяет запись и удаляет питомца. Учетная запись остается в тестовой базе. Для запуска укажите оба адреса:

```sh
HVOST_TEST_URL=http://127.0.0.1:8090/api/ HVOST_TEST_WS_URL=ws://127.0.0.1:8091/ws ./gradlew :NetLib:testDebugUnitTest --tests '*HvostLiveTest' --rerun-tasks
```
