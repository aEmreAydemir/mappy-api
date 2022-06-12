# mappy-api

a springboot backend implementation focused on Google Places Api where you can get nearby places in a given radius

Warning : This project requires your own private GoogleCredentials to work with. In order to use this, you must get your own google credentials.

Change the @Cors annotation to localhost:8080 while working in your local.

Summary of the project :

The project simply provides data to a frontend application written with Angular. In order for the backend to work, it's endpoint requires 3 parameters : longitude, latitude and a radius. Afterwards a request is sent to GooglePlacesApi via feignClient. The response is sent back to frontend.

Finally, the project is dockerized and deployed to google cloud run.
