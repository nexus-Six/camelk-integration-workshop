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
&#9744; `oc new-project userX-helloworld` - create a new project with userx-helloworld \
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
&#9744; change the `from('timer:tick?period=3000')` to `from('timer:tick?period=5000')` in the `helloworld.groovy` file 
> The running integratino will reload itself first, a few seconds later you will see the hello world message printing out in the terminal every 5 seconds 

&#9744; `oc delete project userx-helloworld` - clean up the namespace

### 2. Chuck Norris Joke Bot
[This example](https://github.com/nexus-Six/camelk-integration-workshop/tree/master/02-kamelets-examples/chuck-norris-example) demonstrates how to create a simple Chuck Norris Kamelet source and how to integrate it with telegram. Our goal is to receive some funny jokes on our telegram.

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

#### Let's get start

&#9744; `oc new-project userX-chuck-norris` - create a new project for the second example \
&#9744; oc apply -f [chuck-norris-source.kamelet.yaml](https://github.com/nexus-Six/camelk-integration-workshop/blob/readme-br/02-kamelets-examples/chuck-norris-example/chuck-norris-source.kamelet.yaml) - use the `kamel` to create the chuck norris source kamelet 
> You can use `kamel init xxxx-source.kamelet.yaml / xxxx-sink.kamelet.yaml` to instantiate a kamelet template for creating your own custom kamelet. 

&#9744; `oc get kamelets` - check if the chuck norris source is ready \
&#9744; kamel run [chuck-norris-example.groovy](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/02-kamelets-examples/chuck-norris-example/chuck-norris-example.groovy) --dev - run the integration using chuck norris kamelet in the "dev mode" 
> You should see the jokes appearing in the terminal. 
> [All the supported languages for Camel K integration](https://camel.apache.org/camel-k/1.8.x/languages/languages.html)

&#9744; kamel run [chuck-norris-telegram.groovy](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/02-kamelets-examples/chuck-norris-example/chuck-norris-telegram.groovy) --dev - run the integration with telegram, provide the bot Token and chat ID \
&#9744; `oc delete project userX-chuck-norris` - clean up the project


### 3. Message Printer
In [this example](https://github.com/nexus-Six/camelk-integration-workshop/tree/readme-br/03-knative-example/telegram-example/printer-example), we will create a simple telegram source `Kamelet` and use `KameletBinding` to bind the source to a Knative channel. Then we will create a **Printer** that prints out the messages sent to the telegram source. 

&#9744; `oc new-project userX-message-printer` - create a new project for the message printer example
&#9744; `oc get csv` - make sure the Camel K and Serverless is running in your namespace 
> We will elaborate more on the Serverless and Knative during the workshop. 

&#9744; oc apply -f [telegram-simple-source.kamelet.file](https://github.com/nexus-Six/camelk-integration-workshop/blob/readme-br/03-knative-example/telegram-example/printer-example/telegram-simple-source.kamelet.yaml) - create the telegram source kamelet
> More explanation for the kamelet file will be present in the workshop.

&#9744; `oc get kamelets` - check if the source kamelet is ready \
&#9744; oc apply -f [telegram-channel.yaml](https://github.com/nexus-Six/camelk-integration-workshop/blob/readme-br/03-knative-example/telegram-example/printer-example/telegram-channel.yaml) - create a Knative InMemory Channel \
&#9744; oc apply -f [telegram-source-binding.yaml](https://github.com/nexus-Six/camelk-integration-workshop/blob/readme-br/03-knative-example/telegram-example/printer-example/telegram-source-binding.yaml) - create the binding between the Knative channel and the telegram source \
&#9744; `oc get integrations` - check the status of the kamelet binding \
&#9744; oc apply -f [printer.groovy](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/03-knative-example/telegram-example/printer-example/printer.groovy) - create the integration that prints out the messages sent to the telegram bot 
> We will explain it in details during the workshop.
> You should see the printer running in one pod first

&#9744; Send some messages to your telegram bot \
&#9744; `kamel logs printer` - check the logs of the printer
> You can also go to the OpenShift web console and check the logs of Printer pod.

&#9744; Stop sending messages to the telegram bot \
&#9744; Printer pod autoscales to zero \
&#9744; `oc delete project userX-message-printer` - clean up the namespace







