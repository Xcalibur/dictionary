# Словарь
## Сборка

targetSdk 31
gradle jdk 11

## Библиотеки

### Архитектурные компоненты для поддержки жизненого цикла
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
implementation("androidx.fragment:fragment-ktx:1.3.6")

### Навигация
implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

### Поддержка платформеных апи
implementation 'androidx.core:core-ktx:1.7.0'
implementation 'androidx.appcompat:appcompat:1.3.1'
implementation 'com.google.android.material:material:1.4.0'

### UI Контейнер
implementation 'androidx.constraintlayout:constraintlayout:2.1.1'

### Подргрузка картинок
implementation 'com.squareup.picasso:picasso:2.71828'

### Работа с апи
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.okhttp3:logging-interceptor:4.9.0'
