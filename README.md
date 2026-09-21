# Online Learning Management System (LMS) - Microservices

A distributed Online Learning Management System built with **Spring Boot microservices**. The system is split into 8 independent services that register with a central **Eureka Server** and communicate with each other using **OpenFeign**.

## Architecture

```
                    +------------------+
                    |  Eureka Server   |
                    | (Service Registry)|
                    +---------+--------+
                              |
      +---------+---------+---+-----+----------+----------+---------+
      |         |         |         |          |          |         |
  Student    Course   Instructor Enrollment  Payment  Assignment  Result
  Service    Service   Service    Service    Service   Service    Service
      |         |         |         |          |          |         |
      +---------+---------+----+----+----------+----------+---------+
                               |
                            MySQL
```

## Services

| Service | Responsibility |
|---|---|
| **eureka-server** | Service discovery and registry for all microservices |
| **student-service** | Manages student records and profiles |
| **course-service** | Manages courses and course details |
| **instructor-service** | Manages instructors and their assigned courses |
| **enrollment-service** | Handles student enrollment into courses |
| **payment-service** | Handles course fee payments |
| **assignment-service** | Manages assignments for courses |
| **result-service** | Stores and retrieves student results |

## Tech Stack

- **Java**, **Spring Boot**
- **Spring Cloud Netflix Eureka** (service discovery)
- **Spring Cloud OpenFeign** (inter-service communication)
- **Spring Data JPA** (persistence)
- **MySQL** (database)
- **Maven** (build tool)

## Prerequisites

- JDK 17 or higher
- Maven (or use the included `mvnw` wrapper)
- MySQL running locally

## How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/UdayChourasiya/Online-Learning-Platform.git
   cd Online-Learning-Platform
   ```

2. **Configure the database**
   Open `src/main/resources/application.properties` in each service and set your MySQL URL, username and password.

3. **Start Eureka Server first**
   ```bash
   cd eureka-server
   mvnw spring-boot:run
   ```
   Eureka dashboard (default port): http://localhost:8761

4. **Start the remaining services** (each in its own terminal)
   ```bash
   cd student-service
   mvnw spring-boot:run
   ```
   Repeat for `course-service`, `instructor-service`, `enrollment-service`, `payment-service`, `assignment-service` and `result-service`.

5. Open the Eureka dashboard and confirm all services show as **UP**.

## Project Structure

```
Online-Learning-Platform/
├── eureka-server/
├── student-service/
├── course-service/
├── instructor-service/
├── enrollment-service/
├── payment-service/
├── assignment-service/
└── result-service/
```

## Author

**Uday Chourasiya**
GitHub: [UdayChourasiya](https://github.com/UdayChourasiya)
