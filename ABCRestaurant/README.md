# ABC Restaurant Platform

The **ABC Restaurant Platform** is a comprehensive web-based application designed to manage restaurant operations, including user management, product management, and category management. It includes both the backend and frontend components of the system, with user-friendly features for administrators and customers alike.

## Table of Contents
1. [Project Structure](#project-structure)
2. [Modules](#modules)
    - [ABCCommon](#abccommon)
    - [ABCWebParent](#abcwebparent)
    - [ABCBackEnd](#abcbackend)
    - [ABCFrontEnd](#abcfrontend)
3. [Dependencies](#dependencies)
4. [Database Configuration](#database-configuration)
5. [Testing](#testing)
6. [Version Control](#version-control)
7. [Running the Application](#running-the-application)
8. [GitHub Repository](#github-repository)

## Project Structure

The project is organized as a multi-module Maven project that includes several key components:
- **ABCCommon**: Shared entities and configurations across the project.
- **ABCWebParent**: The main parent module that links the backend and frontend services.
- **ABCBackEnd**: Handles the admin functionalities such as managing users, roles, products, and categories.
- **ABCFrontEnd**: Frontend component where customers can view categories and products.

Each module is a Maven project and is interlinked through Maven's dependency management.

## Modules

### ABCCommon
This module contains common entities and configurations used by both backend and frontend. Key entities include `User`, `Role`, `Product`, and `Category`.

#### Key Dependencies:
- `spring-boot-starter-data-jpa`: For JPA/Hibernate-based persistence.
- `lombok`: To reduce boilerplate code through annotations.

### ABCWebParent
The parent module that aggregates both frontend and backend components. It defines the packaging as `pom` and includes `ABCBackEnd` and `ABCFrontEnd` modules.

#### Key Dependencies:
- `spring-boot-starter-web`: For building web applications.
- `spring-boot-starter-security`: To handle authentication and authorization.
- `spring-boot-starter-thymeleaf`: For rendering dynamic content on the frontend.

### ABCBackEnd
This is the admin component of the platform where administrators manage products, categories, and users. The backend interacts with the MySQL database and provides a REST API.

#### Key Dependencies:
- `spring-boot-starter-web`: Web development and REST API support.
- `spring-boot-starter-data-jpa`: Data persistence using JPA.
- `mysql-connector-java`: MySQL database connectivity.

### ABCFrontEnd
The frontend is responsible for rendering customer-facing pages, such as product listings and categories. It integrates Bootstrap and JQuery for the UI/UX experience.

#### Key Dependencies:
- `bootstrap`: For responsive design.
- `jquery`: To handle UI interactions.

## Dependencies

The project leverages several key dependencies:
- **Spring Boot**: For creating standalone applications with embedded servers.
- **Spring Data JPA**: For database interactions using JPA/Hibernate.
- **Spring Security**: For managing authentication and authorization.
- **Thymeleaf**: For rendering views on the server-side.
- **MySQL**: As the relational database for storing information about users, roles, products, and categories.
- **Lombok**: To reduce boilerplate Java code (e.g., getters, setters).
- **Bootstrap & JQuery**: For frontend styling and dynamic elements.

## Database Configuration

The application uses **MySQL** as the database. The following tables are created in the database:
- **users**: Stores user data.
- **roles**: Stores role information for users.
- **products**: Stores product details.
- **categories**: Stores product categories.
- **users_roles**: A many-to-many relationship table linking users and roles.

### Example Data
Here’s an example of the `users` table:
+----+---------------------+----------+----------+--------------------------------------------------+ | id | email | firstName| lastName | password | +----+---------------------+----------+----------+--------------------------------------------------+ | 1 | john.doe@gmail.com | John | Doe | $2a$10$Zg8j7BVe.... | | 2 | jane.smith@gmail.com | Jane | Smith | $2a$10$2dfdsfsd... | +----+---------------------+----------+----------+--------------------------------------------------+

sql

## Testing

The project uses **JUnit 5** for unit testing. Tests are written to ensure that the user management functionality works as expected. Example tests include:
- Creating a user with one or multiple roles.
- Updating user details.
- Enabling/disabling users.

To run tests, execute:
### bash
mvn test
## Sample Test
@Test
public void testCreateNewUserWithOneRole() {
    Role roleAdmin = entityManager.find(Role.class, 1);
    User userNameHM = new User("ham@abc.net", "ham2024", "Hamzath", "vanan");
    userNameHM.addRoles(roleAdmin);
    User savedUser = userRepo.save(userNameHM);
    assertThat(savedUser.getId()).isGreaterThan(0);
}

### Version Control
The project is version-controlled using Git and hosted on GitHub. The branching strategy involves feature-based branches for isolated development, which are merged back into the master branch after testing.

## Key branches:

master: Contains the stable version of the project.
feature-branches: Separate branches for developing new features like customer CRUD, product CRUD, etc.
A detailed commit history demonstrates daily progress, including testing and new feature implementations.

Link to the GitHub repository: https://github.com/Hamzathvanan/abc-restaurant-platform.git

Git Workflow
The development workflow follows a feature-branching model, where new functionality is developed on separate branches and merged after proper testing. For example:

Branch customer-home was used for developing the customer-facing home page.
Branch product-category-crud was used for developing product and category management.
Running the Application
Clone the repository:

### bash
git clone https://github.com/Hamzathvanan/abc-restaurant-platform.git
Navigate to the project directory:

### bash

> cd abc-restaurant-platform

### Build and run the application using Maven:

> mvn clean install

## Start the backend:

> cd ABCWebParent/ABCBackEnd
> mvn spring-boot:run

## Start the frontend:

> cd ABCWebParent/ABCFrontEnd
> mvn spring-boot:run
The application will be available at http://localhost:8080.

GitHub Repository
The project is publicly available on GitHub: https://github.com/Hamzathvanan/abc-restaurant-platform.git

This `README.md` covers the structure, dependencies, database, testing, and version control techniques employed in the `ABC Restaurant Platform` project. It is formatted to help users quickly get started with the application and provides the necessary information about running, testing, and contributing to the project.