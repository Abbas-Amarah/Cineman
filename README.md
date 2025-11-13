Cineman App
A modern Android application built entirely with Jetpack Compose, designed to display movies fetched from the TMDB API. This project demonstrates my ability to architect, develop, and manage an interactive and scalable Android app from scratch using the most modern Android development tools and patterns.

Overview
Cinema App is a work-in-progress project that showcases clean, maintainable, and testable code with a strong separation of concerns. It interacts with TMDB API to fetch and display real movie data while following modern Android development best practices.

Tech Stack
UI Layer
Jetpack Compose – Fully declarative UI framework for building responsive and dynamic layouts.
Type-safe Navigation (Navigation 3) – Simplifies navigation between screens with compile-time safety.
State Management – Efficiently handles recomposition and UI state updates.
Architecture
Clean Architecture – Ensures clear boundaries between layers for scalability and maintainability.
MVVM (Model–View–ViewModel) – Separates UI logic from business logic.
Repository Pattern – Abstracts data sources and provides a clean API for the rest of the app.
Use Cases (Interactors) – Encapsulate business logic and make it reusable across view models.
Dependency Injection
Koin – Lightweight and powerful DI framework for managing dependencies cleanly and efficiently.
Networking
Retrofit – Handles API calls to fetch data from TMDB API.
Gson Converter – For easy serialization and deserialization of JSON responses.
Key Features
Fetches live movie data from TMDB API.
Clean, modular, and testable codebase.
Reactive UI with proper state management.
Scalable architecture ready for future features.
Current Status
The app is under active development. Planned future improvements include:

Caching with Room database.
Offline support.
Improved UI/UX animations.
Advanced filtering and search.
