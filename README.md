
# Twilio Client Quickstart for Java
This web application is built up using Spring boot.

![enter image description here](https://raw.githubusercontent.com/wnsfernando95/twilio_client_spring_java/master/images/twilio.jpg)

# Setup
### Requirements

 1. Java - 11
 2. [Ngrok](https://ngrok.com/download) 
 3. [A Twilio account signed up](https://www.twilio.com/console)

### Twilio Account Settings
This is an completed Twilio client spring boot project. Before run the project you should have to gather following properties.

| Config Value | Description |
|--|--|
|  `twilio.account_sid`| Your primary Twilio account identifier - find this [in the console here](https://www.twilio.com/console). |
|`twilio.twiml.app.sid`|  The TwiML application with a voice URL configured to access your server running this app - create one  [in the console here](https://www.twilio.com/console/voice/twiml/apps). Also, you will need to configure the Voice "REQUEST URL" on the TwiML app once you've got your server up and running.|
|`twilio.trial_number`|A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://www.twilio.com/console/phone-numbers/incoming)|
|`twilio.api.key` / `twilio.api.secret`|Your REST API Key information needed to create an [Access Token](https://www.twilio.com/docs/iam/access-tokens) - create [one here](https://www.twilio.com/console/project/api-keys).|

### Local development

 1. When after you gather above details, you should have to update [application.properties](https://github.com/wnsfernando95/twilio_client_spring_java/blob/master/twilio_client_spring/src/main/resources/application.properties) file with gatherd keys.
 2. Then open the cmd or terminal and go to root directry  (where the application.properties file available) then run `mvn spring-boot:run` command.
 3. Then navigate to [http://localhost:8080](http://localhost:8080/) - Here you can see the application is working fine.
 4. You should have to run it with ngork to locally check it. So to that open the terminal and run `ngrok http 8080` command. So the terminal will display ngrok forwarded URL. Copy it and navigate to it from the browser.
 5. This is the final step. All the application side changes are alomost over. Now you should have to setup the twilio configurations. Go to the [TWIML application](https://www.twilio.com/console/voice/twiml/apps) and click on the application you created. And update the voice URL with `ngrok forwaded URL + /call/voice` and keep the method type as default (HTTP Post). 
			 Ex: https://71da93bcb0b9.ngrok.io/call/voice
And finally update the phone voice URL so you are ok to test the application. To update the phone voice URL navigate to the [phone numbers](https://www.twilio.com/console/phone-numbers/incoming) . Click on a phone number and then update the voice URL with `ngrok forwaded URL + /call/direct-client` and keep the method type as default (HTTP Post). 
			Ex: https://71da93bcb0b9.ngrok.io/call/direct-client

Hopefully, Now your application is running without issue. If you faced any issue post those in [issues section](https://github.com/wnsfernando95/twilio_client_spring_java/issues). 

			







