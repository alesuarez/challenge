# Tenpo challenge

Tenpo challenge is a web application based on microservices that runs in Docker containers. This README provides instructions for installing and running MyApp using Docker Compose.

## Prerequisites

Make sure you have the following components installed on your system:

- Docker: [https://www.docker.com](https://www.docker.com)
- Docker Compose: [https://docs.docker.com/compose](https://docs.docker.com/compose)

## Installation Steps

Follow the steps below to install and run Tenpo challenge in your local environment:

1. Clone the Tenpo challenge repository from GitHub:

   ```shell
   git@github.com:alesuarez/challenge.git
   
2. Run the following command to build the Docker images and start the containers:

    ```shell
    docker-compose up --build

This command will download the necessary images, create and run the containers based on the configuration specified in the docker-compose.yml file. The containers will run in the background (-d).

3. After the containers have started successfully, you can access to Swagger Documentation through your web browser at the following URL:


         http://localhost:8080/challenge/swagger-ui.html

## Available endpoints
1. Get a percentage

         POST http://localhost:8080/challenge/calculator/percentage
         
         body:
              {
                  "valueOne": 10,
                  "valueTwo": 30
              }
   
2. All Call History paginated data 

    
    GET http://localhost:8080/challenge/history?page=0&size=10

Observations: you can specify the amount of data and the page number
- page: page number
- size: number of records per page

By default page=0 and size=10

## How to test

1. MOCKYFLY

    This API uses app.mockfly.dev as mock service.
    Three possible answers are configured:

- If the sum of the values is equal to 500.0 the service returns an Internal Service Exception.
- If the sum of the values is equal to 100.0 the service returns 30%.
- If the sum of the values is equal to 200.0 the service returns 10%.
- By default 10%

Postman collection: https://api.postman.com/collections/10961811-f12c27e1-986e-4650-9985-74ddf3f16de7?access_key=PMAT-01H2PKH2CWSQ26T72REYKVR4RA

2. API

Get 30% 

        curl --location 'http://localhost:8080/challenge/calculator/percentage' \
        --header 'Content-Type: application/json' \
        --data '{
        "valueOne": 25,
        "valueTwo": 75
        }'

Get 10%

        curl --location 'http://localhost:8080/challenge/calculator/percentage' \
        --header 'Content-Type: application/json' \
        --data '{
        "valueOne": 150,
        "valueTwo": 50
        }'

Retry 3 times and go to the database

        curl --location 'http://localhost:8080/challenge/calculator/percentage' \
        --header 'Content-Type: application/json' \
        --data '{
        "valueOne": 200,
        "valueTwo": 300
        }'


## Technologies

- Spring boot
- Maven
- Resilience4j
- Lombok
- Swagger
- Docker