# Credit Card Managment

A simple credit card management Rest application running on the JVM that manages credit card transactions.

## DESCRIPTION

This Rest API Application is to store and retrieve credit card details from the database. The new card details are added into the system by passing the name of the card holder, card number and limit for that card. Balance update functionality can be enabled based on the future need, but as of now that functionality is disabled. 

### Functionalities Added 

1. Card details will be added into the system based on the customer input. 
2. Only Unique card details are allowed to enter, if you try to enter existing card details system will not allow to enter.  
3. Balance adding future is disabled, if required we can enable that functionality by un commenting the code and then user can add blance to the existing cards. 

### Added Api's:
1. It will be possible to create a new credit card 
2. It will be possible to fetch all the credit card transactions.  

## Api requirements and running instructions
1. Java 8
2. Maven 4 to build the application.
3. Transactions can be viewed from the database by hitting this url from browser : http://localhost:8080/h2-console/
4. In memory database H2 is used
5. The schema and database related configuration can be found in this location : CreditCardManager\src\main\resources\application.properties
```
spring.datasource.url=jdbc:h2:mem:cardmanager
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.properties.hibernate.globally_quoted_identifiers=true
#spring.jpa.show-sql=true
```
6. From the root folder of the application run this command : mvn clean install
7. To Run this application, open command prompt from root directory of this project and run this command : 
	mvn spring-boot:run
	(or)
java -jar target/CreditCardManager-0.0.1-SNAPSHOT.jar  

8. To check that application started successfully go to browser and enter this URL : http://localhost:8080/creditCard/test
        This should produce result as below : 
            Hello Application started Successfully !!! 
## Technology used
- H2 in memory database.
- Spring Boot, including Spring Data JPA.
- slf4j for logging.

## Features not implemented
1. Credit card balance update future is disabled.
2. Authentication and Authorization can be implemented using Spring Security .

# API Endpoints:

### Post Endpoint:

1. To Create a new Wallet, pass the following JSON in the body of the post request:
    
    #### Request : 
 URL: http://localhost:8080/creditCard/add    
    ``` 
    {
    "name":"Allice",   
    "number":"1111222233334444",
    "limit":30
    }
    ```     
 #### Response :
 ``` 
    {
        "name": "Allice",
        "number": "1111222233334444",
        "limit": 30,
        "balance": 0
    }
 ```
### GET Endpoint : 

 1. To fetch all credit card transactions:

    #### Request : 
    URL: http://localhost:8080/creditCard/getAll
    
    #### Response : 
    
    ``` 
    [
    {
        "name": "Allice",
        "number": "1111222233334444",
        "limit": 30,
        "balance": 0
    }
    ]
    ``` 
