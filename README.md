# Luke's Gym Backend

## Introduction
Luke's Gym Backend is the server-side application for Luke's Gym Website. It's built using Spring Boot and is designed to handle various backend functionalities such as user management, workout scheduling, and more.

## Prerequisites
Before you begin, ensure you have the following installed on your system:
- Docker
- Git (optional, for cloning the repository)

## Getting Started

### Cloning the Repository
(Optional) If you have Git installed, you can start by cloning the repository to your local machine:

```bash
git clone [repository-url]
cd lukegymbackend
```

Replace [repository-url] with the URL of your Git repository.

### Running with Docker

1. **Building the Docker Image**

   Navigate to the root directory of the project, where the `Dockerfile` is located. Run the following command to build a Docker image named `lukegymbackend`:

   ```bash
   docker build -t lukegymbackend .
   ```
2. **Starting the Docker Container**

    After the image has been built, run the following command to start the container. This command maps port 8080 of the container to port 8080 on your host machine, making the application accessible through your local system:

    ```bash
    docker run -p 8080:8080 lukegymbackend
    ```
       With this command, the Docker container will be started, and the application will begin running inside it. This setup allows you to access the application through your local machine at the designated port.

## Accessing the Application

After starting the Docker container, the application will be available at:

```plaintext
http://localhost:8080
```

Visit this URL in your web browser to interact with Luke's Gym Backend API:

```plaintext
http://localhost:8080/swagger-ui
```
