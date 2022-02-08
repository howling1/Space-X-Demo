# Space-X-demo
A mini website to search and manage Space-X ships, with JAVA, Springboot backend, Vue.js frontend, and MongoDB database. The ship data source is https://api.spacexdata.com/v4/ships

## Prerequisite


## Implementation
### Frontend important files:

Space-X-demo/frontend/package.json: packages and libraries used in the Vue.js application

Space-X-demo/frontend/components/Footer.vue: subcomponent Footer
<img width="1439" alt="image" src="https://user-images.githubusercontent.com/45118918/152957565-e6724d46-8199-4b31-9642-7cb91ae3dafb.png">

Space-X-demo/frontend/components/NavBar.vue: subcomponent NavBar
<img width="1438" alt="image" src="https://user-images.githubusercontent.com/45118918/152952286-68532d61-12f1-432a-88c0-ef42dec8c08d.png">

Space-X-demo/frontend/App.vue: main component of the frontend consisting of the NavBar component, the Footer component and the corresponding sub-component defined in the router below. The front-end application renders the current entire page through this component.

Space-X-demo/frontend/src/router.vue: route manager managing routes for all pages. We define the url for each page here.

Space-X-demo/frontend/components/Ship.vue: ship subcomponent
<img width="1411" alt="image" src="https://user-images.githubusercontent.com/45118918/152953040-97bc7237-8a49-48bb-8571-d8ed2706cdf4.png">

Space-X-demo/frontend/views/About.vue: About page(URL:http://localhost:3000/about)
<img width="1439" alt="image" src="https://user-images.githubusercontent.com/45118918/152953353-452e448c-89ea-4559-ae57-45c516371748.png">

Space-X-demo/frontend/views/Home.vue: Home page(URL:http://localhost:3000/)
<img width="1439" alt="image" src="https://user-images.githubusercontent.com/45118918/152953397-05409b6b-78b3-4ddd-8c7a-7fac8edd3067.png">

### Backend important files:

Space-X-demo/backend/pom.xml: project maven file including all packages used in backend

Space-X-demo/backend/src/main/resources/application.yml: configuration file for the my own MongoDB database. please change the username, and password according the user of your local MongoDB if you are running the website in your local PC.

Space-X-demo/backend/src/main/java/edu/tum/backend/BackendApplication.java: backend main execution program. Inside the main function to run the backend, I invoke the method "deleteAllShips" to clear the local database when the system is starting.

Space-X-demo/backend/src/main/java/edu/tum/backend/model/Ship.java: ship model used for receiving ship objects from the frontend and the MongoDB database and inserting ship documents into the database

Space-X-demo/backend/src/main/java/edu/tum/backend/repository/ShipRepository: ship repository, where all the database operations(CRUD) are defined

Space-X-demo/backend/src/main/java/edu/tum/backend/Service/ShipService: ship service, where all service methods regarding CRUD are defined

Space-X-demo/backend/src/main/java/edu/tum/backend/controller/ShipController: ship controller, taking responsibility of handling all requests regarding ships from the frontend

Space-X-demo/backend/src/main/java/edu/tum/backend/security/CorsConfig: CORS configuration file, where privileges are defined to allow the frontend to access controller methods of the backend

## Abbreviations
CRUD: Create, Read, Update, Delete

CORS: Cross-origin resource sharing


