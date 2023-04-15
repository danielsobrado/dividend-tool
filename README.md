# Dividend Tool

Dividend Tool is a Spring Boot application designed to help users manage their stock portfolios and track dividend income. The application scrapes dividend data from the Nasdaq website and allows users to manage their portfolios, holdings, and buy/sell operations.

## Features

- Scrape dividend data from the Nasdaq website for stocks in users' portfolios
- Manage users, portfolios, holdings, and buy/sell operations
- Generate reports of dividend income at specific points in time based on holdings and scraped dividend data

## Technologies

- Spring Boot
- Spring MVC Rest
- Spring HATEOAS
- Spring Data JPA
- Lombok
- PostgresSQL
- Gradle

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- JDK 11 or higher
- PostgresSQL

### Installation

1. Clone the repository:

```
git clone https://github.com/yourusername/dividend-tool.git
```


2. Change into the project directory:

```
cd dividend-tool
```


3. Update `application.properties` with your PostgresSQL credentials and database URL:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```


4. Build the project:

```
./gradlew build
```


5. Run the application:

```
./gradlew bootRun
```


The application should now be running on `http://localhost:8080`.

## Usage

Once the application is running, you can interact with the REST API to create, read, update, and delete users, portfolios, holdings, and buy/sell operations. The dividend data scraping service will automatically collect dividend data from the Nasdaq website for the stocks in users' portfolios.
