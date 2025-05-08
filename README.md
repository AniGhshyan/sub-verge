# User Management Microservice

This project is a microservice developed using Spring Boot 3, which provides a REST API for managing users and their subscriptions to digital services.

## Requirements

- **Java version**: 17
- **Database**: PostgreSQL
- **Logging**: SLF4J
- **Containerization**: Docker

## Functional Requirements

### User Management API
1. **Create User**  
   `POST /users` - Creates a new user.

2. **Get User Information**  
   `GET /users/{id}` - Retrieves information about a specific user.

3. **Update User Information**  
   `PUT /users/{id}` - Updates the details of a specific user.

4. **Delete User**  
   `DELETE /users/{id}` - Deletes a user from the system.

### Subscription Management API
1. **Add Subscription to User**  
   `POST /users/{id}/subscriptions` - Adds a subscription to a user.

2. **Get Subscriptions of User**  
   `GET /users/{id}/subscriptions` - Retrieves a list of all subscriptions for a specific user.

3. **Remove Subscription from User**  
   `DELETE /users/{id}/subscriptions/{sub_id}` - Removes a subscription from a user.

4. **Get Top-3 Popular Subscriptions**  
   `GET /subscriptions/top` - Retrieves the top-3 most popular subscriptions.

### Database Integration
- **PostgreSQL** is used to store the data.
- **Tables**:
    - `users`: Stores user details.
    - `subscriptions`: Stores the subscriptions associated with users.

### Logging
- The application uses **SLF4J** for logging.

### Docker Support
- **Dockerfile**: A Dockerfile has been created for deploying the service.
- **Docker Compose**: A `docker-compose.yml` file is provided to allow local deployment of the project along with a PostgreSQL database.

## Endpoints

### User Endpoints
- `POST /users`: Create a user.
- `GET /users/{id}`: Get user details.
- `PUT /users/{id}`: Update user information.
- `DELETE /users/{id}`: Delete a user.

### Subscription Endpoints
- `POST /users/{id}/subscriptions`: Add a subscription to the user.
- `GET /users/{id}/subscriptions`: Get all subscriptions of a user.
- `DELETE /users/{id}/subscriptions/{sub_id}`: Delete a subscription for the user.

### Miscellaneous
- `GET /subscriptions/top`: Get the top 3 most popular subscriptions.

## Getting Started

### Prerequisites

1. **Java 17** installed.
2. **Docker** and **Docker Compose** installed.

### Setting up the Project

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/AniGhshyan/sub-verge.git
   cd user-management-service
