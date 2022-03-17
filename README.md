# camelk-integration-workshop
A workshop to get started with Red Hat Integration Technology such as CamelK, Kamelets, Binding and CamelK on Quarkus

## Telegram Integration

#### Prerequisites:
1. Apache Camel K running in an accessible Kubernetes instance (AKA, you're logged to OpenShift)
2. You have a Telegram account
3. VS Code running locally with the Tooling for Apache Camel K by Red Hat extension installed

#### Instructions:
1. Create a Telegram bot as shown here: https://www.nicolaferraro.me/2016/05/27/creating-a-telegram-bot-in-5-minutes-with-apache-camel/
2. Save the API token somewhere safe
3. Paste your token in application.properties file
4. Run the command 
    
    ```Shell
    kamel run --property file:application.properties hashtagbot.groovy --dev
    ```

5. Open your Telegram account (in web browser or on mobile device)
6. Find the bot you've just created
7. Text the bot and enjoy!