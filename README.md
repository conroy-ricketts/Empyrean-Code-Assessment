# Empyrean Coding Assessment

This is a simple Android application that displays data from a mock API server.

## Features

- User Authentication
- Feed Display: Users can see a list of feed items displayed after login
- Item Details: Users can tap on any item in the feed to view detailed information, including author details and comments
- Basic error handling for network requests
- Dark Mode Theme: Users can change the app theme by toggling their device theme

## Libraries Used

- Dagger for dependency injection.
- RxJava for reactive programming and facilitating data fetching to view models (this project implements MVVM).
- Retrofit for access to mock API server endpoints.

## Getting Started

### Assumptions

The mock API server is not included in this repo! You must have a copy of the mock API server on your local machine.

### Running Locally

1. Clone this repo.
2. Be sure that you have the mock API server setup and running locally.
3. Open this project in Android Studio at the root folder.
4. Run the app on your favorite emulator!

## Things I'd Like To Improve

There are a number of features I would love to add if I had more time including:
- Unit testing
- More polished UI
- Local caching or offline support
- Loading states
- The ability to favorite tiems
- Animations
- An overall more polished UX
