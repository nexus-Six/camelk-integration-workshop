# Chuck Norris Telegram Bot Example

This example demonstrate how to use Kamelets and KameletBinding to create a telegram bot that can send you Chuck Norris jokes. 

Ensure that you have an OpenShift cluster available and the necessary command line tools such as `oc` and `kamel` installed on your laptop/computer. Remember to check the requirements on [README.md](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/README.md). In this example, we need to install the **Red Hat integration - Camel K** operator and **Red Hat OpenShift Serverless** operator.

We need to create both **Knative Serving** and **Knative Eventing** instances via **OpenShift Serverless** operator in their own namespaces.


## Preparing the project

We will create a new project for running this example. This can be done by directly from the OpenShift 
Web Console or by executing the command:

```
oc new-project telegram-jokebot
oc project telegram-jokebot
```

## Introduction to Kamelets

Kamelets (**Kamel** route snipp**ets**) are a concept that allow users to connect to external systems via a simplified interface. A `Kamelet` can act as **source** of data or alternatively a **sink**: a source allows to consume data from an external system, while a sink allows you to send data to an external system or execute a particular action and get the result. 

In this example, we will create a **chuck-norris-source** that calls the Chuck Norris API to get the jokes. After that, we will create a **telegram-sink** that retrieves the Chuck Norris jokes and sends them to our telegram.

### Create chuck-norris-source

The `chuck-norris-source.kamelet.yaml` file contains the basic definition of a `Kamelet` resources. 

> - A `metadata` section contains the `name` of the Kamelet and other information such as the tyoe of Kamelet (`source` or `sink`)
> - A `spec` section contains the description and a set of parameters in JSON format that you can use to configure the Kamelet
> - A`type` section contains the information about the output media type (e.g. `application/json`, `text/plain`)
> - A route `template`/`flow` defines the detailed behaviour of the Kamelet

We can create the Kamelet `chuck-norris-source` by executing the command:

`oc apply -f chuck-norris-source.kamelet.yaml`

> Kamelets are standard YAML files, but the extension `.kamelet.yaml` can help IDEs to recognize them.
> NOTE: A wide set of canned Kamelets is availble at [Apache Kamelet Catalog](https://camel.apache.org/camel-kamelets/next/index.html)



## Binding Kamelets

A `KameletBinding` allows you to move data from a system described by a `Kamelet` towards a Knative channel, or from a Knative channel/broker to another external system described by a `Kamelet`. The high-level flows of an event-driven architecure is shown here:

 ![Figure.1](https://developers.redhat.com/sites/default/files/blog/2021/01/eda_jabberwocky.png)

> source: https://developers.redhat.com/blog/2021/04/02/design-event-driven-integrations-with-kamelets-and-camel-k#using_kamelets_in_an_event_driven_architecture

In this example, we will follow this architecture, first create the binding to start emitting events (jokes) into a Knative channel. Then we will bind the event channel to our "telegram sink". The sink will consequently start receiving events (jokes) and sending it to the telegram.

### Create a message channel

Now we need to create an Knative in-memory channel to receive the event from 
`chuck-norris-source`:

`oc apply -f messages-channel.yaml`

### Create a KameletBinding between chuck-norris-source and message channel

Then, we need to create connection between the `messages` channel and `chuck-norris-source` by using `KameletBinding`:

`oc apply -f chuck-noris-binding.yaml`

This binding will take the `chuck-norris-source` Kamelet (as referred in the `source:`), and make sure that messages produced by the Kamelet are forwarded to the Knative InMemoryChannel named `messages` (as referred in the `sink:`).

### Create the telegram sink

Similar to the previous section, we can simply run the command `oc apply -f telegram-simple-sink.kamelet.yaml` to create the telegram sink.

and we need to connect the sink to the event channel `messages`:

`oc apply -f telegram-sink-binding.yaml`

In `telegram-sink-binding` file, you should provide your Chat ID and your telegram bot token. So the binding will configures it using the specific properties and ensures that the messages from the `messages` channel are forwarded to the `telegram-simple-sink`.

After 1-2 min, you should see the telegram bot starting sending you Chuck Norris Joke.

