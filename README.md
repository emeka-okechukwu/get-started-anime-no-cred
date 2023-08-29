# Get Started Anime

## Table of Contents
- [About The Project](#about-the-project)
  - [Introduction](#introduction)
  - [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Setting Up and Running the Application](#setting-up-and-running-the-application)
- [Contributing](#contributing)
- [Contact](#contact)


## About The Project

### Introduction

Get Started Anime is an anime recommendation platform that leverages the power of the OpenAI API to provide personalized anime suggestions based on a user's top 5 movie preferences. The platform aims to assist individuals who are new to anime in discovering shows that align with their existing movie and TV show preferences.

Check out the live deployment of the project [here](https://get-started-anime.vercel.app).


### Technologies Used

- **Frontend**: React (TypeScript), HTML, CSS, Material-UI
- **Backend**: Java, Spring Boot
- **APIs**: OpenAI API, The Movie Database API, Cohere API for deployment


## Getting Started

### Prerequisites

Before you begin, ensure you have the following tools installed:

- [Node.js](https://nodejs.org/) and npm (Node Package Manager)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download) (recommended for development)


### Setting Up and Running the Application

1. Clone the repository

   Open your terminal and run the following command:

   ```sh
   git clone https://github.com/emeka-okechukwu-dev/get-started-anime.git
   ```

2. Start the backend server

   Navigate to the backend directory:
   
   ```sh
   cd backend
   ```

   Update the `application.properties` file with your API keys:

   ```sh
   openai.apiKey=your_openai_api_key
   cohere.apiKey=your_cohere_api_key # Cohere is optional for local projects.
   tmdb.apiKey=your_tmdb_api_key
   ```
   
   Start the backend server (make sure your JDK is properly configured):

   ```sh
   java -jar build/libs/get-started-anime-0.0.1-SNAPSHOT.jar
   ```

3. Start the frontend server

   Navigate to the frontend directory:

   ```sh
   cd frontend
   ```

   Install frontend dependencies:

   ```sh
   npm install
   ```

   Update the `config.tsx` file with your TMDB API key:

   ```sh
   export const TMDB_API_KEY = "your_tmdb_api_key";
   ```

   Start the frontend development server:

   ```sh
   npm start
   ```

4. Access the application

   Open a web browser and go to:

   ```sh
   http://localhost:3000/
   ```

   You should now be able to access the application locally.

5. Enter your top 5 movie preferences and watch as the platform generates personalized anime recommendations just for you!

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Contributing

Every contribution is appreciated. If you have an idea for improving the project, please fork the repository and create a pull request or open an issue with the tag "enhancement" to share your suggestion.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Acknowledgments

Credit is due to the developers behind the creators of the [OpenAI](https://openai.com), [Cohere](https://cohere.com), and [The Movie Database](https://www.themoviedb.org/) for providing the tools that power this project.


## Contact

Emeka Okechukwu - chuks.egkedu@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>