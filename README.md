# camelk-integration-workshop
A workshop to get started with Red Hat Integration Technology such as CamelK, Kamelets, Binding and CamelK on Quarkus

### Prerequisites
To finish all the tasks in this workshop, here's what you need to have:

#### 1. OpenShift Cluster with admin privileges. You need to be able to install operators.

#### 2. Install CLIs and move them to the PATH
    1. `kamel` CLI 
    2. `kn` CLI 
    3. `oc` CLI 

#### 3. Operators on your OpenShift cluster:
    1. Red Hat Integration - Camel K
    2. Red Hat OpenShift Serverless with instances of:
        a. Knative Serving 
        b. Knative Eventing

#### 4. Telegram bot
1. Create a Telegram bot as shown here: https://www.nicolaferraro.me/2016/05/27/creating-a-telegram-bot-in-5-minutes-with-apache-camel/

2. Remember to store your API key somewhere safe. Once it's done, find your bot on Telegram and send any message to it. After that, open below URL in your browser:
`https://api.telegram.org/botXXXXXXXXX:YYYYYYY-YYYYYYYYYYYYYYYYY_YY/getUpdates`

3. Copy `chatID` form the response and story it along with API Key.
When sending a message to the bot, you might need to provide both to authenticate. Use this format to do so:
`.to("telegram:bots?authorizationToken=XXXXXXXXX:YYYYYYY-YYYYYYYYYYYYYYYYY_YY&chatID=XXXXXXXXXX")`

