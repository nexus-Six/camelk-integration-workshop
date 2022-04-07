# Telegram Kamelet Example

In this example, we will create a simple telegram source and use `KameletBinding` to bind the source to a channel. Then we will create a `Printer` that prints out the messages sent to the telegram source.

Ensure that you have an OpenShift cluster available and the necessary command 
line tools such as `oc` and `kamel` installed on your laptop/computer. 
Remember to check the requirements on [README.md](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/README.md). In this example, we need 
to install the **Red Hat integration - Camel K** operator and **Red Hat 
OpenShift Serverless** operator.

We need to create both **Knative Serving** and **Knative Eventing** instances 
via **OpenShift Serverless** operator in their own namespaces.


### Create the telegram source Kamelet

By running `oc apply -f telegram-simple-source.kamelet.yaml`, we will create the telegram source. You can check the ready source by running the command:

`oc get kamelets`

### Create a telegram channel

We need to create an Knative channel for telegram,

`oc apply -f telegram-channel.yaml` 

or if you have `kn` CLI installed, you can run:

`kn create channel telegram`

### Create the KameletBinding

Now we should bind the telegram source to the channel

`oc apply -f telegram-source-binding.yaml`

Here, we need to put our telegram bot token into the `telegram-source-binding.yaml` file.

### Create the Printer

Now we need to create a Camel K integration that will print out the messages from the `telegram` channel by running:

`kamel run printer.groovy`

The Printer integration will be scaled to 0 when not used (if you wait for a few minutes, you'll see it). 

Once the `printer` starts running, you can try to send some messages to your telegram bot and run

`kamel logs printer`

You should be able to see the messages that you just sent to the bot appearing in the logs.

If now you stop sending the messages, you will notice that the printer will scale down to zero in a few minutes.

Now if you start sending some messages again, the `printer` integration will scale up agin as soon as it receives the messages.
