# Task51C - News and Video Player Application

A simple Android application that combines news browsing and YouTube video playback functionality.

## Features

### News Section
- Browse news articles
- View news details
- Top stories section

### Video Section
- YouTube video playback
- Playlist management
- Video navigation controls

## Requirements
- Android Studio
- Android SDK
- Internet connection for news and video playback

## Installation
1. Clone the repository
2. Open the project in Android Studio
3. Build and run the application

## Usage

### News Section
1. Launch the application
2. The news section will be displayed by default
3. Browse through the news articles
4. Tap on a news item to view details

### Video Section
1. Tap the video icon in the bottom navigation
2. Login to access the video section
3. Add YouTube videos to your playlist:
   - Enter a YouTube URL in the input field
   - Tap "ADD TO PLAYLIST"
4. Play videos:
   - Tap "PLAY" to start playing the playlist
   - Use the navigation buttons (< and >) to move between videos
5. View your playlist:
   - Tap "MY PLAYLIST" to see all added videos

## Technical Details
- Uses YouTube Android Player API for video playback
- Implements RecyclerView for efficient list display
- Uses SharedPreferences for user login state management
- Follows Material Design guidelines for UI components

## Dependencies
- AndroidX
- Material Design Components
- YouTube Android Player API

## Author
Shicheng Xiang