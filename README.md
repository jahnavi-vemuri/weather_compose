# weather_compose

This app is written in Kotlin using Jetpack compose in Android Studio.

I got data from Open Weather and use their API to get that data.

For this, I had to sign up and confirm my account. Once I do all that, I received an API Key which I used to fetch the data through API calls using Retrofit.

I used the search view controller to get the input (city name) from the user.

Fetch the weather data of the city.

Data is displayed in the display view controller. Lon and Lat, Temp, Pressure, Wind speed, Country are displayed.

No of screens - 2 Search screen and Weather data screen

Integrated MapView in the weather app displayed the view controller, annotated using a custom marker at the location and a scaffold was implemented when clicked on market a bottom sheet poped up.

<img width="274" alt="Screenshot 2024-05-08 at 12 35 19 PM" src="https://github.com/jahnavi-vemuri/weather_compose/assets/127096031/3e8e5bb9-fd32-48df-b7b8-41eac6b2f94c">
<img width="276" alt="Screenshot 2024-05-08 at 12 35 43 PM" src="https://github.com/jahnavi-vemuri/weather_compose/assets/127096031/56407ede-93d4-4884-a804-45c6e078bb15">
<img width="269" alt="Screenshot 2024-05-08 at 12 36 05 PM" src="https://github.com/jahnavi-vemuri/weather_compose/assets/127096031/052ccd01-c23a-48d6-bb80-711f6558ff1a">
<img width="273" alt="Screenshot 2024-05-08 at 12 36 20 PM" src="https://github.com/jahnavi-vemuri/weather_compose/assets/127096031/36edbcd0-3157-47a1-ad81-553ca5903759">
