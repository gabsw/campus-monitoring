# Sprint 4 Assessment - 19/12/2019

## Achievements

1. Login Feature


## Missing

1. Frontend is not being served by Spring Boot or any alternative web server.

## Testing frontend

To test frontend functionality, follow these steps:
1. In branch `frontend`, go to `Campus-Monitoring/src/main/resources/templates`
2. Open `index.html`
3. Login as one of the administrators (eg: username: `pedro_bastos`) or as a regular collaborator (eg: username: `joana_martins`)
**NOTE:** use `password` as password to all users

**NOTE:** once the frontend files are making API Requests to the backend in the VM provided for the project, make sure you are connected to University of Aveiro network (in person or through VPN) to access the VM


## Example requests

To test the application's REST API, you can use Postman with the provided examples in the folder `postman_requests`.

Just import the collections on Postman and make sure that you can access UA's internal network.

These requests cover all currently implemented API features. The documentation for accessing the API is provided in the folder `API Documentation` or in the Spring Boot app: `/src/main/resources/static/api.html`.
