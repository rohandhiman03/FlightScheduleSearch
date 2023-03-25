# Flight Schedule Search Software

This project is a software application that allows users to search for flight schedules for five different cities. Users can input their origin city, destination city, departure date, return date (if applicable), and user category to get results. The application retrieves data from Skyscanner's partner public APIs, processes the data, and displays the results in a user-friendly format. 

## Features

- User input of origin city, destination city, departure date, return date, and user category.
- Retrieval of data from Skyscanner's partner public APIs.
- Parsing of JSON data and display of flights.
- Save favorite flights in a local database.
- Retrieve favorite flights from the database upon login.
- Add or delete flights from the user's favorite flights stored in the database.

## Technologies Used

- Skyscanner's partner public APIs
- PostgreSQL relational database
- Object Relational Mapping
- Table DataGateway
- Strategy Design Pattern

## Getting Started

1. Clone the repository: `git clone https://github.com/yourusername/flight-schedule-search-software.git`
2. Install dependencies: `npm install`
3. Create a `.env` file and set the following environment variables:
    - `DATABASE_URL`: URL to connect to PostgreSQL database
    - `SKYSCANNER_API_KEY`: Skyscanner's API key
4. Start the application: `npm start`

## Usage

1. Enter the origin city, destination city, departure date, return date (if applicable), and user category.
2. Click the "Search" button to retrieve flight schedules.
3. To save a flight to favorites, click the "Add to Favorites" button.
4. To view favorite flights, click the "Favorites" tab.
5. To remove a flight from favorites, click the "Remove" button.

## Contributors

- [Your Name](https://github.com/YourGitHubUsername)
- [Collaborator Name](https://github.com/CollaboratorGitHubUsername)
