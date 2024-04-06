# Population Data API

## Introduction
The Population Data API provides endpoints to retrieve information about population statistics, including population density, UN member counts, and Euro users count.

## Technologies Used
- Java
- Spring Boot
- Maven (for dependency management)
- Postman (for testing API endpoints)

## Setup Instructions
1. Clone the repository:
   https://github.com/williamramos08/Population-Data-API
2. Build the project using Maven:
   mvn clean install
3. Run the application:
   mvn spring-boot:run
4. Access the API endpoints using a tool like Postman or through a web browser.

## Endpoints
1. **Population Density:**
- Endpoint: `/population/density`
- Method: GET
- Description: Retrieves population density data for each country.

2. **Population Statistics:**
- Endpoint: `/population/stats`
- Method: GET
- Description: Retrieves population statistics such as mean, median, and standard deviation of population density.

3. **Count of UN Members:**
- Endpoint: `/population/un-members`
- Method: GET
- Description: Counts the number of UN member countries.

4. **Count of Euro Users:**
- Endpoint: `/population/euro-users`
- Method: GET
- Description: Counts the number of countries using Euro as currency.

## Usage
- Send GET requests to the specified endpoints to retrieve population data.
- Use the responses for data analysis, visualization, or any other relevant purposes.

## Contributors
- [Your Name](https://github.com/your_username)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.



