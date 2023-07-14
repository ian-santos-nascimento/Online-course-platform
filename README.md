# This project is an online courses platform like HotMart (FullStack application)
#### It's a monolithic application that later will be transformed into a microservice architecture
#### The goal of this project is to implement technologies for dealing with heavy data storage such as videos files, and it's  consuming in FrontEnd with ReactJS
### Technologies involved:
- Java 18
- SpringBoot(Data, Security, Web)
- Swagger for documentation
- MongoDB(GridFS) for storage

### To be implemented
- Docker
- LoadBalancer to direct incoming traffic
- ApiGateway for security policy
- JWT auth
- ReactJS to show in FrontEnd

### How to run
 * Requirements:
    - Docker
    - Java 18
    - MongoDB
 - Clone this repository
 - Create a MongoDB Document called "hotmart" with host "mongo-on-docker" running on port 27017
 
### API endpoints:
(All endpoints are in /swagger-ui/index.html of this application created with springdoc)


    [GET][POST][DELETE] "/curso"
    [GET] "/curso/id" 

    [POST][UPDATE] "/secao"
    [GET][DELETE] "/secao/id
    
    [GET][POST][PUT]  "/usuario"

    [POST] "/video/secaoId"
    [GET] "video/id" NOTE: It can be secaoId or the videoId