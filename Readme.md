# Youtube API
<p align="center">
An API that fetches the latest YouTube videos for a given topic. <br><br>
  <a href="https://spring.io/projects/spring-boot">
    <img alt="RabbitMQ" src = "https://i2.wp.com/www.thecuriousdev.org/wp-content/uploads/2017/12/spring-boot-logo.png" height=50 width=100>
  </a>
  <a href="https://www.mongodb.com/">
    <img alt="Mongo" src="https://webassets.mongodb.com/_com_assets/cms/MongoDB_Logo_FullColorBlack_RGB-4td3yuxzjs.png" height=50 width=185/>
  </a>
  <a href="https://www.docker.com/">
    <img alt="Docker" src = "https://www.docker.com/sites/default/files/d8/2019-07/vertical-logo-monochromatic.png" height=50 width=58>
  </a>
</p>
<br>

# Table of Contents
- [Getting Started](#getting-started)
- [Code Overview](#code-overview)
- [API](#api)
<br>

## Getting Started
### Essential Environment Variables
1. <b>api.key.list</b> : Comma-separated api keys. Eg: <i>key1,key2,key3</i>
2. <b>video.fetch.topic</b> : Topic of videos to be fetched. Eg: <i>cricket</i>
3. <b>video.fetch.interval</b> : Interval for populating database with videos in ms. Eg: <i>30000</i> 
4. <b>spring.data.mongodb.host</b> : Mongo service name for youtube-api-app to connect. 



### Starting the API
This application uses Docker Compose to start youtube-api-app and mongodb. 

Optional: Pull the application image from DockerHub using the command:
  ```bash
  $ docker pull dhanushkamath/youtube-api:1.0.0
  ```

Run the application with the command:
  ```bash
  $ docker-compose up
  ```

Test the API with the included Postman collection - [Postman](test/)  

To scale youtube-api-app service, use:
  ```bash
  $ docker-compose up --scale youtube-api-app=<number-of-containers>
  ```

## Code Overview
### Application Structure
- `src/main/java` - This folder contains the application source code.
- `test/` - This folder contains the POSTMAN collection with all requests configured.

## API
## GET /videos
----
  Returns a json response containing videos. 

* **URL**

  /api/videos

* **Method:**

  `GET`   
    
*  **Query Params**
   
   
    * `text: [string]` | <i>To search for videos with given text in video title and/or description</i>
    * `page: [integer]` | <i>The page number</i>   
    * `size: [size]` | <i>The size of each page</i>
    
 * **URL Params**
   
    None

* **Notes**
  * If <i><b>text</b></i> is passed, response will contain best matches for the given text. Supports partial match as well.
  * If <i><b>page</b></i> is passed, response will have pagination elements. 

 
## Authors
* **Dhanush Kamath** - [GitHub](https://github.com/dhanushkamath) | [Medium](https://dhanushnkamath.medium.com) | [LinkedIn](http://dhanushnkamath.medium.com)
