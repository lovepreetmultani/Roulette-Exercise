## Roulette Service
This is a sample implementation of the Roulette Service, which is designed to handle bets, simulate roulette wheel spins, and resolve bets based on the spin result.

### Overview
The Roulette Service is responsible for:

Receiving a number of bets and performing any necessary validation.
Simulating a roulette wheel spin.
Resolving all bets based on the spin result and returning the results to the client.
Currently, the only supported bet type is the "Single number" bet, which pays out 36 times the bet amount.

### Implementation Details
Java 17
Spring Boot 2
Spring Framework
Maven

### Project Structure
The project is structured as follows:

src/main/java: Contains the Java source code.
roxor.games.roulette.controller: Controllers for handling REST API requests.
roxor.games.roulette.game: Classes related to the roulette game logic, including the RouletteWheel and WheelResult classes.
roxor.games.roulette.model.request: Request DTOs (Data Transfer Objects) for defining the API request structure.
roxor.games.roulette.model.response: Response DTOs for defining the API response structure.
roxor.games.roulette.service: The RouletteService class, responsible for implementing the core business logic.
src/test/java: Contains unit tests for the service classes.
pom.xml: Maven configuration file.
Running the Application
To run the application, follow these steps:

Ensure you have Java and Maven installed on your system.

Clone this repository to your local machine.

Open a terminal or command prompt and navigate to the project directory.

Run the following command to build the project and start the Spring Boot application:

mvn spring-boot:run

The application will start, and you can access the REST API endpoints.

REST API Endpoints
POST /v1/roulette/spin: Submit a spin request with a list of player bets.

#### Example Request Body:

{
"playerBets": [
{
"playerName": "Sam",
"betType": "number",
"pocket": "4",
"betAmount": "1.00"
}
]
}

##### Example Response:

{
"pocket": 30,
"betResults": [
{
"playerBet": {
"playerName": "Sam",
"betType": "number",
"pocket": "4",
"betAmount": "1.00"
},
"outcome": "lose",
"winAmount": "0.00"
}
]
}
