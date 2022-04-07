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

### Create a telegram account 
We prepared three Camel K examples, from simple to advanced. For the advanced examples, we will run an integration between telegram and Knative sources. Therefore, we need a telegram account before we get started.

> Note: if you haven't used telegram before, you can simply download the `Telegram` mobile app and create a new account there. Remember to create an unique username as well.

After we have the telegram account, we still need two things:
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

## Start the workshop

First, we need to login to the OpenShift cluster from both web console and terminal. 
> Note: we will elaborate on this during the workshop.

### 1. Hello World
[This example](https://github.com/nexus-Six/camelk-integration-workshop/tree/master/01-helloworld-example) demonstrates a simple Camel K integration that periodically prints a "Hello World from Camel K" message.

&#9744; `oc get csv` -



