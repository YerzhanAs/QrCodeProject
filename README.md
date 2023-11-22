# QR Code Project

The QR Code Project is a Spring Boot-based application designed to generate, store, and retrieve QR codes. It offers RESTful endpoints to facilitate the creation of QR codes from provided links and stores them in a PostgreSQL database. Additionally, the application allows users to retrieve stored QR code images based on unique identifiers.

## Table of Contents

- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
    - [Generating QR Codes](#generating-qr-codes)
    - [Retrieving QR Code Images](#retrieving-qr-code-images)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)


## Getting Started

### Prerequisites

Before running the QR Code Project, ensure you have the following prerequisites:

- Java 17
- Maven
- PostgreSQL

### Installation

1. Clone the repository:
   git clone https://github.com/YerzhanAs/QrCodeProject.git
2. Navigate to the project directory:
   cd QrCodeProject
3. mvn clean install

## Usage

### Generating QR Codes

To generate a QR code, use the following endpoint:

- **Endpoint:** `POST /qrcodes/generate`
- **Parameters:**
    - `link` (Query Parameter): The link to be encoded into the QR code.
    - or
    - `QRCodeRequest` (Request Body): A JSON object containing the link.

**Example (using cURL):**

Using Query Parameter:
```bash
curl -X POST "http://localhost:8080/qrcodes/generate?link=https://example.com" -w "\n"
```
Using Request Body:
```bash
curl -X POST -H "Content-Type: application/json" -d '{"link": "https://example.com"}' "http://localhost:8080/qrcodes/generate2" -w "\n"
```

### Retrieving QR Code Images

To retrieve a stored QR code image, use the following endpoint::

- **Endpoint:** `GET /qrcodes/{imageId}`
- **Path Variable:**
    - `imageId`  The unique identifier of the QR code image.


**Example (using cURL):**

```bash
curl -X GET "http://localhost:8080/qrcodes/1" -w "\n"
```


## Project Structure

The project follows the standard Spring Boot project structure:

- **src/main/java/com/example/qrcodeproject:**
    - **controller:** Contains controllers responsible for handling HTTP requests.
    - **entity:** Defines entities used for database operations.
    - **repository:** Contains Spring Data JPA repositories for database access.
    - **service:** Contains business logic and services for QR code generation and storage.

- **src/main/resources:**
    - **application.properties:** Configuration properties for the Spring Boot application.
    - **templates:** Thymeleaf templates for rendering HTML pages.

## Dependencies

### Spring Boot Starters:

- `spring-boot-starter-data-jpa`
- `spring-boot-starter-thymeleaf`
- `spring-boot-starter-validation`
- `spring-boot-starter-web`
- `spring-boot-devtools`
- `postgresql`
- `lombok`
- `spring-boot-starter-test`

### ZXing Library:

- `com.google.zxing:core:3.3.3`










   
