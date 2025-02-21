# Simple Java Project: File Max Number Finder

This is a simple Java project that provides a RESTful API to find the maximum number from a specified number of elements in a file located at a given local path. The project includes Swagger for API documentation and can be deployed using Docker Compose.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
    - [Building the Project](#building-the-project)
    - [Running the Application Locally](#running-the-application-locally)
    - [Accessing the API Documentation](#accessing-the-api-documentation)
- [Deployment with Docker Compose](#deployment-with-docker-compose)
- [API Endpoint](#api-endpoint)

---

## Features

- **Single Endpoint**: `/api/v1/files/findMax` to retrieve the maximum number from a file.
- **Header**
    - `localPathToFile` (String, required): The local path to the file containing numbers.
- **Parameter**:
    - `numberOfElements` (Integer, required): The number of elements from the file to consider for finding the maximum.
- **Swagger Integration**: Accessible API documentation via Swagger UI.
- **Docker Deployment**: Easy deployment using Docker Compose.

---

## Prerequisites

Before you begin, ensure you have the following installed on your machine:

- **Java Development Kit (JDK)**: Version 17 or higher.
- **Maven**: For building the project.
- **Docker**: For containerization.
- **Docker Compose**: For managing multi-container Docker applications.

You can download and install these tools from their official websites:

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

---

## Getting Started

### Building the Project

1. **Clone the Repository**

   ```bash
   git clone https://github.com/AndreiPronsky/ComfortSoftTestTask.git
   cd ComfortSoftTestTask
   ```

2. **Build the Project Using Maven**

   ```bash
   mvn clean install
   ```

   This command will compile the project, run the tests, and package it into a JAR file.

### Running the Application Locally

1. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

2. **Accessing the Application**

   You can use tools like [Postman](https://www.postman.com/) or [cURL](https://curl.se/) to interact with the API.

   **Example Request**:

```bash
curl --location 'http://localhost:8080/api/v1/files/findMax' \
     --header 'localPathToFile: Test.xlsx' \
     --get \
     --data-urlencode 'numberOfElements=10'
```

### Accessing the API Documentation

The project includes Swagger for interactive API documentation.

1. **Start the Application**

   Ensure the application is running as described above.

2. **Open Swagger UI**

   Navigate to `http://localhost:8080/swagger-ui.html` in your web browser.

   Here, you can explore the available endpoints, see the request and response structures, and even test the API directly.

---

## Deployment with Docker Compose

To deploy the application using Docker Compose, follow these steps:

1. **Build the Docker Image**

   Ensure you are in the project root directory and run:

   ```bash
   docker build -t ComfortSoftTestTask .
   ```

2. **Create `docker-compose.yaml`**

   Create a `docker-compose.yaml` file with the following content:

   ```yaml
   version: '3.8'

   services:
     app:
       image: ComfortSoftTestTask
       container_name: ComfortSoftTestTask
       ports:
         - "8080:8080"
       volumes:
         - ./data:/data
   ```

   **Note**: Adjust the `volumes` section as needed to map the directory containing your files.

3. **Start the Application with Docker Compose**

   ```bash
   docker-compose up -d
   ```

   This command will start the application in a Docker container and map port `8080` of the host to the container.

4. **Verify the Deployment**

   Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to access the Swagger UI.

---

## API Endpoint

### **Endpoint**: `/findMax`

- **Method**: `GET`
- **Description**: Retrieves the maximum number from a specified number of elements in a file.
- **Header**
  - `localPathToFile` (String, required): The local path to the file containing numbers.
- **Parameter**:
  - `numberOfElements` (Integer, required): The number of elements from the file to consider for finding the maximum.

- **Responses**:

    - **200 OK**: Successfully retrieved the maximum number.
      ```json
      243
      ```
    - **400 Bad Request**: Invalid input parameters.
      ```json
      {
        "errors": [
          {
            "field": "localPathToFile",
            "message": "Path to a file should not be null or blank"
          },
          {
            "field": "numberOfElements",
            "message": "Number must be greater than 0"
          }
        ]
      }
      ```
    - **500 Internal Server Error**: An unexpected error occurred.

---