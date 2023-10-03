## This project is an online courses platform like HotMart (FullStack application)
#### It's a monolithic application that later will be transformed into a microservice architecture
#### The goal of this project is to implement technologies for dealing with heavy data storage such as videos files, and it's  consuming in FrontEnd

## Technologies:
- Java 18
- Swagger for documentation
- MongoDB(GridFS) for storage
##### To be implemented:
  - SpringBoot(Data, Security, Web)
  - Docker
  - LoadBalancer to direct incoming traffic
  - BD cache(Caffeine ou Redis)
  - ApiGateway for security policy
  - JWT auth
  - ReactJS to show in FrontEnd

## How to run
 - Clone this repository with "git clone git@github.com:ian-santos-nascimento/Online-course-platform.git"

#### Create Mongo DB
 - Follow the tutorial of you OS system https://www.mongodb.com/docs/manual/tutorial/ to install mongoDB if you dont have one
 - start mongodb with "sudo systemctl start mongod" (using systemctl)
 - Connect to mongo on port 27017 with "mongosh mongodb://localhost:27017"
 - Create the DB called hotmart with "use hotmart"
 - To stop mongo use "sudo systemctl stop mongod" (using systemctl)


## Architecture:
- The architecture of this project is designed to be a firstly monolithic then switched to microservices
- So the packages are separated to each domain/entity(curso,pagamento,usuario) be transformed into a service
 
## API endpoints:
(All endpoints are in http://localhost:8080/swagger-ui/index.html of this application created with springdoc)