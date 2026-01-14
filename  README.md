# CourseHub — Android Test Assignment

Приложение-тест, демонстрирующее мои навыки в разработке на Android с использованием многомодульной архитектуры и Clean Architecture.

## 🛠 Стек технологий

- **Язык**: [Kotlin](https://kotlinlang.org/) + Coroutines (Asynchronous programming)
- **Архитектура**: Clean Architecture + MVVM (Model-View-ViewModel)
- **Реактивный подход**: Kotlin Flow (Data streams)
- **Dependency Injection**: [Dagger 2](https://dagger.dev/) (Внедрение зависимостей)
- **Сеть**: [Retrofit 2](https://square.github.io/retrofit/) + OkHttp (REST API)
- **UI**: XML Layouts + ViewBinding
- **Списки**: [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates) (Композиция списков)
- **Навигация**: Jetpack Navigation Component
- **Многомодульность**: Разделение по слоям и фичам
- **Gradle**: Kotlin DSL + Version Catalog (libs.versions.toml)

## 🏗 Архитектура проекта

Проект реализован по принципам **Clean Architecture** и разделен на модули для обеспечения слабой связанности (low coupling) и высокой тестируемости.

### Модули:
- `:app` — точка входа, конфигурация Dagger (AppComponent) и навигация.
- `:domain` — (Pure Kotlin) бизнес-логика: UseCases, модели данных, интерфейсы репозиториев.
- `:data` — реализация данных: Retrofit API, репозитории, мапперы.
- `:core` — базовые классы (BaseFragment, BaseViewModel), сетевые настройки и утилиты.
- `:feature-auth` — модуль экрана авторизации.
- `:feature-main` — основной модуль, содержащий Bottom Navigation и экраны:
    - Главная
    - Избранное
    - Аккаунт (заглушка)

## 📐 Схема взаимодействия слоев
`Presentation (UI/ViewModel) -> Domain (UseCase/Models) <- Data (Repository/API)`

## 🚀 Как запустить
1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/твой-логин/CourseHub_Isaev_Test.git