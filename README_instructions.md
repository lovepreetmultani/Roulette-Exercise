This repo contains the skeleton for a  roulette wheel REST service implemented in Spring Boot that candidates are
expected to clone and add functionality to as part of the Slots And Casino interview process.

## Requirements

- Java 17+
- Maven 3+

## Exercise Specification

Candidates are asked to implement the business logic for the [RouletteService](api/src/main/java/roxor/games/roulette/service/RouletteService.java) (and any supporting classes deemed necessary) only.

Imagine that a colleague has implemented what's already in this repository and that you're tasked with finishing it off to this specification.

The service is expected to be able to 
1. Receive a number of bets do any validation thought necessary
2. Spin a representation of a roulette wheel
3. Resolve all bets based on the spin result and return the results.

The only [bet type](https://www.gamblingsites.com/online-casino/games/roulette/bets/) that should be initially implemented is

- Single number (pays 36x bet)

The Roulette REST API has been defined and *mostly* implemented.

The model classes defining the request and response API are already implemented in the [model](model) module.

Pay attention to implementing a solution which is **designed with extensibility in mind** and which has **good test coverage**.

The intention is that you don't spend more than a few hours designing and implementing a solution for this.

Please provide a README to explain any assumptions you have made or any further updates you would have liked to have made if you had more time.

If you have any further questions you have about the spec please let us know (if it would be helpful we can arrange a short meeting to answer any of your queries)

## How To Run

First, from the root of the project build both the required modules: 

```
mvn clean install
```

Then, start the Roulette REST service:

```
mvn -f api/pom.xml spring-boot:run
```

You then access its [Swagger UI](http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config)

[//]: # (or [API docs]&#40;http://localhost:8080/api-docs/&#41;)

## Roulette Service REST API examples

### Single Player bet

#### Request

```
POST  http://localhost:8080/v1/roulette/spin
```

```json
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
```

#### Response

```json
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
```


### Multiple player bets (with forcing included)

#### Request

```
POST  http://localhost:8080/v1/roulette/spin
```

```json
{
  "forcedPocket": "36", // This field is **optional** and should **only** be used in testing scenarios
  "playerBets": [
    {
      "playerName": "Nick",
      "betType": "number",
      "pocket": "7",
      "betAmount": "1.00"
    },
    {
      "playerName": "Jules",
      "betType": "number",
      "pocket": "36",
      "betAmount": "2.00"
    }
  ]
}
```

#### Response

Example response JSON showing roulette spin result and the resolution of all submitted bets:

```json
{
 "pocket": 36,
 "betResults": [
  {
   "playerBet": {
    "playerName": "Nick",
    "betType": "number",
    "pocket": "7",
    "betAmount": "1.00"
   },
   "outcome": "lose",
   "winAmount": "0.00"
  },
  {
   "playerBet": {
    "playerName": "Jules",
    "betType": "number",
    "pocket": "36",
    "betAmount": "2.00"
   },
   "outcome": "win",
   "winAmount": "72.00"
  }
 ]
}
```

### Validation Errors

If there was an issue validating the request please return a `BAD_REQUEST` `400`



