# API Documentation for Usuario

## This module later will be transformed into a service

### Search User by Email

    - **URL**: `/usuarios`
      - **HTTP Method**: `GET`
      - **Description**: Search for a user by providing the email in the request body.
      - **Request Content Type**: JSON
      - **Response Content Type**: JSON
      - **Success Response Code**: 200 OK
      - **Responses**:
          - **Success**:
              - **HTTP Code**: 200 OK
              - **Response Body Schema**: [Usuario](#usuario-model)
          - **Not Found**:
              - **HTTP Code**: 404 Not Found
          - **Internal Server Error**:
              - **HTTP Code**: 500 Internal Server Error

### Save User
    
    - **URL**: `/usuarios`
      - **HTTP Method**: `POST`
      - **Description**: Save a user.
      - **Request Content Type**: JSON
      - **Response Content Type**: JSON
      - **Success Response Code**: 200 OK
      - **Responses**:
          - **Success**:
              - **HTTP Code**: 200 OK
              - **Response Body Schema**: [Usuario](#usuario-model)
          - **Not Found**:
              - **HTTP Code**: 404 Not Found
          - **Internal Server Error**:
              - **HTTP Code**: 500 Internal Server Error

### Update User
    
    - **URL**: `/usuarios`
      - **HTTP Method**: `PUT`
      - **Description**: Update a user.
      - **Request Content Type**: JSON
      - **Response Content Type**: JSON
      - **Success Response Code**: 201 Created
      - **Responses**:
          - **Success**:
              - **HTTP Code**: 201 Created
          - **Not Found**:
              - **HTTP Code**: 404 Not Found
          - **Internal Server Error**:
              - **HTTP Code**: 500 Internal Server Error

## Usuario Model

- **Fields**:
    - `id` (String): The id of the user
    - `name` (String): Name of the user
    - `email` (String): The email and unique identifier of the user
    - `password` (String): The password of the user
    - `dataCriacao` (Date): The creation date of the user
