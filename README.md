# Food Sharing App
## User stories
##### For restaurants and dorm rooms with leftover foods: they can share food with people in starving, home shelters and any organizations that will use the food for the needy
##### For hungry people, home shelters and any organization in need of food: they can make a food request with a description of how much food in need

## Modular architecture
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/modular%20structure.png">
</p>

## Technologies to be used
### FrontEnd:
Display: Recycler View
<br>Map: Google Map API Parse API
<br>Platform: Android Studio
<br>Database: MongoDB
<br>Structure: MVP structure
<br>Debug: Setho


### BackEnd
Cloud: AWS/Parse
<br>ElasticSearch to store the post/request and support geo search
<br>Parse server to mange all the backend data including requests, user info and images.

## APIs
Google map API to display nearby food post and request on map
<br>Parse API to store data on cloud
## Task assignments
He Li: Overall layout, Recycler View, Goole Map API, MVP structure
<br>ZBY: AWS server, Backend storage, login interface, data connections from server

## Definition of Sprint 1, 2 and 3
Sprint 1: Research on how to use recycler view, room and google map API. Optimize design, specify what tool to use.
<br>Sprint 2: Overall layout and simple post feature done
<br>Sprint 3: Use AWS for server deployment and Parse for cloud database

## Project infrastructure
One main activity, with a top bar and bottom navigator bar and a view pager in the mid.
<br>Use view pager and container fragment to hold fragments on different pages.

### Initial design of the app
For initial design, this is what the app will look like.
#### Page 1
The food nearby page will be binded with recycler view to display post and request nearby.<br> People also can make a post/request for food on this page. 
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/app_page1.png">
</p>

#### Page 2
Google map API will be used on this page to display the food posts (blue) and requests (green) on the map.
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/app_page2.png">
</p>


## Progress So Far
### Display fake data with Recycler View
#### Click Post details and make new post button will go to a new fragment
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/foodPage.gif">
</p>

### Display fake geo points on Google map
#### Embedded Google Map in fragment 
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/mapPage.gif">
</p>

### Use google account to support login
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/settingPage.gif">
</p>

### Backend deployment
#### AWS EC2
Amazon Elastic Compute Cloud (Amazon EC2) provides scalable computing capacity in the Amazon Web Services (AWS) cloud.

