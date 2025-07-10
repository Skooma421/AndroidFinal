# DashCook

DashCook is an Android recipe app that fetches data dynamically from backend services to provide users with a seamless cooking experience.

## Overview

The app displays recipes, categories (tags), and detailed recipe information. All data is retrieved from four backend services:

- **All Recipes Service**: Provides the complete list of recipes for the home screen.
- **Recipes by Tag Service**: Filters recipes based on selected categories.
- **Recipe by ID Service**: Retrieves detailed information for a specific recipe.
- **Tags Service**: Supplies available recipe tags for filtering.

## Key Features

- **Dynamic Data Loading**: Uses Retrofit with Kotlin Flows and LiveData for reactive UI updates.
- **Navigation**: Implements Safe Args for type-safe navigation between fragments.
- **Efficient Lists**: Uses RecyclerView with DiffUtil for smooth and efficient list updates.
- **Image Loading**: Loads recipe images with Glide for optimized performance.
- **User Authentication**: Supports registration and login with local session management using SharedPreferences.

## Architecture

- **ViewModels** manages data retrieval and expose flows to the UI.
- **Fragments** observes data and update UI accordingly.
- **Adapters** handles displaying lists of recipes and tags.
- **SessionManager** manages user login status and credentials locally.

## Usage

- Browse recipes and tags on the home screen.
- Select a tag to view filtered recipes.
- View detailed recipe information.
- Register or log in to personalize the experience.

---
