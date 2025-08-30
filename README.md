# My To-Do List App

##  Overview

This is a simple, yet powerful To-Do List Android application built using modern Android development practices. It helps users manage their daily tasks efficiently by allowing them to add, track, and delete tasks. The app uses Room for local data persistence and ViewModel for a clean, maintainable architecture.

##  Features

* **Create Tasks**: Easily add new tasks with a title and a description.
* **Track Progress**: Mark tasks as completed with a checkbox. The task's title and description will be visually "striked-through" to indicate completion.
* **Conditional Deletion**: Swipe to delete a task, but only after it has been marked as completed. This prevents accidental deletion of active tasks.
* **Data Persistence**: Your tasks are saved locally on your device using the Room database, so they remain even after you close the app.
* **Clean Architecture**: The app is built with a ViewModel and Repository pattern, following best practices for a scalable and testable codebase.

##  Technologies Used

* **Android SDK**
* **Kotlin** (or Java, depending on your project language)
* **Room Database**: For local data storage.
* **Android Architecture Components**:
    * **ViewModel**: To manage UI-related data in a lifecycle-conscious way.
    * **LiveData**: For observable data.
    * **Repository Pattern**: For a clear separation of concerns between data sources and the UI.
* **RecyclerView**: To efficiently display the list of tasks.
* **Data Binding**: To declaratively bind UI components in your layouts to data sources.
* **Material Components**: For modern and responsive UI elements.

## Setup Instructions

To run this project, clone the repository and open it in Android Studio.

## Requirements
Android Studio

A device or emulator running Android 8.0 or higher.
