# Reminder App

A modern Android application for managing reminders with a clean Material Design interface. Built using Kotlin and following MVVM architecture pattern.

## About App

Reminder App is a simple yet powerful tool to help users manage their daily tasks and reminders. It provides a clean, intuitive interface for creating, editing, and managing reminders with optional date/time scheduling.

## Features

- **Create Reminders**: Add new reminders with title, description, and optional date/time
- **Edit Reminders**: Modify existing reminders
- **Delete Reminders**: Remove unwanted reminders
- **View All Reminders**: See all your reminders in a clean, organized list
- **Date/Time Selection**: Easy date and time picking through native Android pickers
- **Empty State Handling**: Clear indication when no reminders exist
- **Material Design**: Modern UI following Material Design guidelines
- **Offline Support**: All data stored locally using Room database
- **MVVM Architecture**: Clean architecture pattern for better maintainability

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/remiderapp/
│   │   │   ├── data/
│   │   │   │   ├── Reminder.kt
│   │   │   │   ├── ReminderDao.kt
│   │   │   │   └── ReminderDatabase.kt
│   │   │   ├── repository/
│   │   │   │   └── ReminderRepository.kt
│   │   │   ├── ui/
│   │   │   │   ├── ReminderAdapter.kt
│   │   │   │   └── AddEditReminderDialog.kt
│   │   │   ├── viewmodel/
│   │   │   │   └── ReminderViewModel.kt
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── item_reminder.xml
│   │   │   │   └── dialog_add_edit_reminder.xml
│   │   │   └── values/
│   │   │       └── strings.xml
│   │   └── AndroidManifest.xml
│   └── test/
└── build.gradle.kts
```

## Main Components

### Data Layer
- **Reminder**: Data entity class representing a reminder
- **ReminderDao**: Data Access Object for database operations
- **ReminderDatabase**: Room database configuration

### Repository Layer
- **ReminderRepository**: Abstracts data operations and provides a clean API

### ViewModel Layer
- **ReminderViewModel**: Manages UI-related data and handles business logic

### UI Layer
- **MainActivity**: Main screen with RecyclerView
- **ReminderAdapter**: Handles reminder list display
- **AddEditReminderDialog**: Dialog for adding/editing reminders

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer
- Kotlin 1.8.0 or newer
- Android SDK 24 or newer
- Gradle 8.1.0 or newer

### Installation
1. Clone the repository:
```bash
git clone https://github.com/yourusername/reminder-app.git
```

2. Open the project in Android Studio

3. Sync project with Gradle files

4. Run the app on an emulator or physical device

## Usage

### Adding a Reminder
1. Tap the floating action button (+)
2. Enter reminder title (required)
3. Optionally add description
4. Optionally select date/time
5. Tap Save

### Editing a Reminder
1. Tap the edit icon on any reminder
2. Modify the details
3. Tap Save

### Deleting a Reminder
1. Tap the delete icon on any reminder
2. Confirm deletion

## Extending

### Adding New Features
1. **Notifications**: Implement reminder notifications
2. **Categories**: Add reminder categories/tags
3. **Priority Levels**: Add priority to reminders
4. **Search**: Add search functionality
5. **Sorting**: Add sorting options
6. **Backup**: Add backup/restore functionality

### Code Structure
The app follows MVVM architecture, making it easy to add new features:
1. Add new data entities in the data layer
2. Update DAO and Repository
3. Add ViewModel logic
4. Create UI components

## Dependencies

- **AndroidX Core**: Core Android functionality
- **Material Design**: UI components
- **Room**: Local database
- **Lifecycle**: ViewModel and LiveData
- **RecyclerView**: List display
- **ViewBinding**: View binding


## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Acknowledgments

- Material Design Guidelines
- Android Architecture Components
- Room Persistence Library
- AndroidX Libraries
