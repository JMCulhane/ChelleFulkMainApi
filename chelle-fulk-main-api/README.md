# Chelle Fulk Main API

This is a Spring Boot API for:
- Super user login
- Submitting new records
- Submitting new music videos
- Sending emails from the contact form

## Structure
- `controller/` - REST controllers
- `service/` - Business logic
- `repository/` - Data access
- `model/` - Data types (to be aligned with frontend)

## Setup
1. Java 17+ and Maven required
2. To build: `mvn clean install`
3. To run: `mvn spring-boot:run`

## Next Steps
- Implement endpoints and data types
- Configure database and email settings
