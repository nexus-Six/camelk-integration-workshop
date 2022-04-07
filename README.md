# Camel K Intergration Workshop

This workshop aims to help you have your first hands-on experience with Camel K and the important concepts such as Kamelets, KameletBinding and Knative. 

## Prerequisites
Before we start going through the workshop, make sure that you have **VSCode** or **VSCodium** installed on your machine.
> If you are familiar with Git, you can clone this GitHub Repository and open it with VSCode.

We need to install the VSCode extensions -- **Extension Pack for Apache Camel by Red Hat** for later use.

### Preparing for the cluster
In this workshop, we will create and run all the Camel K integrations on the OpenShift cluster.

We need to use the following CLI to execute the integration files later on, therefore, make sure that you installed all the following CLIs on your machine:

- [oc](https://docs.openshift.com/container-platform/4.7/cli_reference/openshift_cli/getting-started-cli.html)
- [kamel](https://camel.apache.org/camel-k/1.8.x/cli/cli.html) 
- [kn](https://knative-v1.netlify.app/docs/install/install-kn/)

We need the following operators installed on the OpenShift cluster:

-  Red Hat Integration - Camel K
-  Red Hat OpenShift Serverless  

## Start the workshop

First, we need to login to the OpenShift cluster from both web console and terminal. 
> Note: we will elaborate on this during the workshop.

### 1. Hello World
[This example](https://github.com/nexus-Six/camelk-integration-workshop/tree/master/01-helloworld-example) demonstrates a simple Camel K integration that periodically prints a "Hello World from Camel K"Open the terminal in the VSCode

&#9744; Open the terminal in the VSCode \
&#9744; Login to OpenShift in the terminal \
&#9744; `oc new-project userx-helloworld` - create a new project with userx-helloworld \
&#9744; `oc get csv` - you should see the Camel K operator running \
&#9744; Create a [helloworld.groovy](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/01-helloworld-example/helloworld.groovy) file 
> We will elaborate on the integration file during the workshop 

&#9744; `kamel run helloworld.groovy` - run the integration using `kamel`
> It might take up to 3min for the integration running. 

&#9744; `oc get integrations` or `kamel get` - check if the integration is  running \
&#9744; `kamel logs helloworld` - get the logs of the running integration
> You will see the hello world message printing out in the terminal each 3 seconds
> If you run `kamel run helloworld.groovy --logs` in the beginning, you will see the logs showing in the terminal as well

&#9744; `kamel run helloworld.groovy --dev` - alternatively, run the integration in the `dev mode` \
&#9744; change the `from('timer:tick?period=3000')` to `from('timer:tick?period=5000')` in the `helloworld.groovy` file \
> The running integratino will reload itself first, a few seconds later you will see the hello world message printing out in the terminal every 5 seconds 

### 2. Chuck Norris Joke
[This example](https://github.com/nexus-Six/camelk-integration-workshop/tree/master/02-kamelets-examples/chuck-norris-example) demonstrates how to create a simple Chuck Norris Kamelet source and how to integrate it with telegram. Our goal is to receive some funny jokes on our telegram

> Note: if you haven't used telegram before, you can simply download the `Telegram` mobile app and create a new account there. Remember to create an unique username as well.

We still need two things:
1. A telegram bot
2. Chat ID

#### Create a telegram bot
&#9744; Search for username @BotFather \
&#9744; Start the chat (/start) \
&#9744; Send /newbot to @BotFather \
&#9744; Follow the instruction of @BotFather and create a name and username for the bot.
&#9744; Once the bot is created, we will get a token to access the HTTP API, **keep the token secure and store it safely**. 

#### Get our Chat ID
&#9744; Search for username @RawDataBot with name `Telegram Bot Raw` \
&#9744; Start the chat (/start) \
&#9744; You will receive a message from the @RawDataBot, store the `"id"` in the `"chat"` section somewhere safe. 

> Once we get the telegram bot token and the chat ID, remember to save it somewhere that you can find them. We will need them in the following examples.




