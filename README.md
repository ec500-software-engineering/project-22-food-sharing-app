# Food Sharing App
## User stories
##### For restaurants and dorm rooms with leftover foods: they can share food with people in starving, home shelters and any organizations that will use the food for the needy
##### For hungry people, home shelters and any organization in need of food: they can make a food request with a description of how much food in need

## Modular architecture
<p align="center"> 
<img src="https://github.com/ec500-software-engineering/project-team-22-food-sharing-app/blob/master/pics/modular%20structure.png">
</p>

## Technologies to be used
Display: Recycler View
<br>Map: Google Map API
<br>Platform: Android Studio
<br>Database: Google Room / Firebase 
<br>Structure: MVP structure
<br>Debug: Setho
<br>Cloud: AWS/Google Cloud

## APIs
Google map API to display nearby food post and request on map
## Task assignments
He Li: Overall layout, Recycler View, Google Room / Firebase, MVP structure
<br>ZBY: Google Map API, AWS/Google Cloud

## Definition of Sprint 1 and 2
Sprint 1: Research on how to use recycler view, room and google map API. Optimize design, specify what tool to use.
<br>Sprint 2: Overall layout and simple post feature done

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
