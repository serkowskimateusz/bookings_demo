# bookings_demo
This is a demo of Spring Boot WebFlux framework with Reactive Cassandra
This project exposes 2 POST Endpoints.
### /api/bookings/newBooking
### /api/bookings/containerAvailability
## Project setup
Spring boot microservice works together with cassandra database. There is docker-compose file with proper setup. (Application will fail if there is no cassandra up and running)
To build and setup go to the root directory in project and input in the terminal 
### docker-compose up 
## Consume REST endpoints
use OPEN API SWAGGER webpage http://localhost:8087/swagger-ui.html
