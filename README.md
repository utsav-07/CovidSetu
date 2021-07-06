# Covid Setu
An app to broadcast SOS for help to users of a particular pincode. Users of that pincode  will recieve an SOS notification with the details of the person who made SOS call
and users can locate that person on the Google maps as well so that they can help that person. This app will also show its users the real-time stats about covid-19 cases in india. 

Video walk through:
https://youtu.be/bAdrccZEX4M

## Libraries used
<br>
->  liquid-swipe
<br>
->  Retrofit2
<br>
->  Coroutines
<br>
->  volley
<br>
->  Gson
<br>
->  okHttp
<br>

## API used
<br>
-> Firebase Cloud Messaging
<br>
-> FusedLocationProvider
<br>
<a href=https://drive.google.com/file/d/1LvXQt4Sew_jComP7-lyKzpNJ4DsRByDh/view?usp>
-> Covid-19India API
</a>
<br>


## Tech Stack Used
<br>
-> Google Firebase - for broadcasting message
<br>
-> PHP             - for creating user login and regIstration API
<br>
-> MySql           - for creating the database of users
<br>

## How it works

#### Authentication and Registration

  >>  we have hosted the backend(PHP files and MySql) on 000webhost in which authentication and varification API is created using PHP and Myql is    used as database
   Volley is used for making the requests
<br>

#### Covid-19 India Real-Time Stats


>> for getting the real-time data we are using Covid-19India API and we are making request through okHttp and converting response json to objects through Gson converter liberary and then we are displaying the data.

 #### SOS Alert

 >> We have used Publisher-Subscriber model here. we are using Pincodes as topics that users have subscribed and we are pushing the notification through firebase cloud messaging
    API to the users which have subscribed same topics (Pincode).

  #### the notification that user get contains

     1) SOS message title and discription
   
     2) Name of the sender
 
     3) Phone No. of sender
 
     4) Location of the sender
    
     -> we are sending location as coordinates(latitude,longitude) and on clicking Locate on map button 
     we are showing that coordinate on Google maps.
 
 ## Contributions
 
 ### Udit Utsav
 
 -> Designed the Login,Register and Dashboard UI for the app using liquid-swip library
 <br>
 -> Created Login and registration API for the app using PHP
 <br>
 -> Created Database in MySql for storing data of users
 <br>
 -> Hosted backend (PHP files and MySql database) on 000webshost
 <br>
 (used java for impleplement these features in app)
 
 ### Suraj Prasad Soni 
 -> Designed UI for showing covid-19 real time stats and SOS ALert feature
 <br>
 -> used Covid-19 India API to fetch the data
 <br>
 -> Used Firebase Cloud messaging to push the notification to all user of particular pincode.
 <br>
 -> used coordinates from recived notification data  to locate it on Google maps.
 <br>
 -> Wrote Documentation for the project
    (used kotlin for implementing these features in app)
    


 (chick here)
 
 
# Screenshots
![1slide1](https://user-images.githubusercontent.com/48099786/124544556-ecfbcf00-de44-11eb-8f6d-f2516eb4bb9a.jpeg)
<br>
![2slide2](https://user-images.githubusercontent.com/48099786/124544560-eec59280-de44-11eb-8115-f6b33049433c.jpeg)
<br>
![3slide3](https://user-images.githubusercontent.com/48099786/124544563-eff6bf80-de44-11eb-8550-3b80b1441853.jpeg)
<br>
![register_page](https://user-images.githubusercontent.com/48099786/124544582-f6853700-de44-11eb-8e21-49634a43eeae.jpeg)
<br>
![login_page](https://user-images.githubusercontent.com/48099786/124544588-f84efa80-de44-11eb-8886-f9c696b2f04f.jpeg)
<br>
![4main_dashboard](https://user-images.githubusercontent.com/48099786/124544613-043abc80-de45-11eb-8fba-ed14d30a00aa.jpeg)
<br>
![5covid_tracker_dashboard](https://user-images.githubusercontent.com/48099786/124544622-07ce4380-de45-11eb-83dd-bf2a1ba7fc4d.jpeg)
<br>
![6pincode_dashboard](https://user-images.githubusercontent.com/48099786/124544638-11f04200-de45-11eb-9238-9a266329f7ab.jpeg)
<br>
![7broadcast_SOS_dashboard](https://user-images.githubusercontent.com/48099786/124544644-174d8c80-de45-11eb-9488-f801669635ba.jpeg)
<br>
![8target](https://user-images.githubusercontent.com/48099786/124544657-1f0d3100-de45-11eb-8b94-4f2aab98c561.jpeg)
<br>
![9location_on_map](https://user-images.githubusercontent.com/48099786/124544670-26ccd580-de45-11eb-852b-a5eb9df656c5.jpeg)
<br>
![path](https://user-images.githubusercontent.com/48099786/124546407-37327f80-de48-11eb-8d8d-0cb9c38368b9.jpeg)
