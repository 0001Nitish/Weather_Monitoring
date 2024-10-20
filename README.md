# Weather_Monitoring
Clone the Repository: git clone (https://github.com/0001Nitish/Weather_Monitoring.git) cd

Install Dependencies: Ensure you have Maven installed. Run the following command to install the necessary dependencies: mvn clean install

Database Setup:

Ensure you have a running instance of a database (e.g., MySQL, PostgreSQL).

Update the application.properties file with your database configuration: properties spring.datasource.url=jdbc:postgres://localhost:5432/your_database spring.datasource.username=your_username spring.datasource.password=your_password spring.jpa.hibernate.ddl-auto=update

DDL

-- Database: weather

-- DROP DATABASE IF EXISTS weather;

CREATE DATABASE weather
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_India.1252'
    LC_CTYPE = 'English_India.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

Run the Application: Use the following command to start the Spring Boot application: mvn spring-boot:run

Access the Application: Open your browser and navigate to http://localhost:8080.

Postman Collection :{"collection":{"info":{"_postman_id":"f2a23a12-09cc-4cff-8846-b600f3c9f119","name":"Weather","schema":"https://schema.getpostman.com/json/collection/v2.1.0/collection.json","updatedAt":"2024-10-20T13:56:12.000Z","createdAt":"2024-10-19T11:32:17.000Z","lastUpdatedBy":"37272584","uid":"37272584-f2a23a12-09cc-4cff-8846-b600f3c9f119"},"item":[{"name":"health","id":"08faa8e6-48e9-4ab6-8f7c-7c56ec931d1b","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[]},"response":[],"uid":"37272584-08faa8e6-48e9-4ab6-8f7c-7c56ec931d1b"},{"name":"getweatherByCity","id":"0bb26d8d-6848-4885-8837-fd9be428915d","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"http://localhost:8080/getWeatherByCity?city=Delhi","protocol":"http","host":["localhost"],"port":"8080","path":["getWeatherByCity"],"query":[{"key":"city","value":"Delhi"}]}},"response":[],"uid":"37272584-0bb26d8d-6848-4885-8837-fd9be428915d"},{"name":"fetchAllWeather","id":"855a6f47-7ea9-4687-9cb0-08d333453eca","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"http://localhost:8080/fetchAllWeather","protocol":"http","host":["localhost"],"port":"8080","path":["fetchAllWeather"]}},"response":[],"uid":"37272584-855a6f47-7ea9-4687-9cb0-08d333453eca"},{"name":"fetchWeatherByCity","id":"b56ffa78-d3ba-48e7-9a22-a7e6fdebace4","protocolProfileBehavior":{"disableBodyPruning":true},"request":{"method":"GET","header":[],"url":{"raw":"http://localhost:8080/fetchAllWeather","protocol":"http","host":["localhost"],"port":"8080","path":["fetchAllWeather"]}},"response":[],"uid":"37272584-b56ffa78-d3ba-48e7-9a22-a7e6fdebace4"}]}}
