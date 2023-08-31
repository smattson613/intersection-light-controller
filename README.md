# intersection-light-controller

## Use Cases
1. Start the lights at a particular intersection.
2. Stop the lights at a particular intersection.
3. Push the config of a particular light at an intersection.
4. Get the config of a particular light at an intersection.

## Deliverables
1. A model of the Light Controller System.
2. A Java Spring Boot application that can fulfill the use cases.

Here is the Java Spring Boot deliverable that was requested during Tuesday's interview after having completed the model during the interview process. I have also added a Postman collection for your convenience. I will add a sample POST JSON at the bottom of this readme.

Inside the Spring Boot application I fulfilled the use cases while adding logic to ensure intersections could not have two green lights at the same time nor could a light be green while the other light is yellow.

All custom exceptions are sent to an exception handler that generates a ResponseEntity so a user on Postman can receive an HTTP status and the error message information.

Getting the config of an intersection returns a DTO with the ID and current light times.

All JPA methods used are tested in a repository test. The service layer test is mocked, and with more time the service layer testing would be more robust.

##### Sample POST JSON

{
    "intersectionId":0,
    "nslight":"GREEN",
    "ewlight":"RED",
    "power":false,
    "nslightRed":45,
    "nslightYellow":5,
    "nslightGreen":15,
    "ewlightRed":45,
    "ewlightYellow":5,
    "ewlightGreen":15
}
