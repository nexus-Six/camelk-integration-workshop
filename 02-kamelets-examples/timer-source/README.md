## Timer Source Hello World example


### Timer Source Kamelet

Make sure you have the `kamel` CLI and  `oc` CLI installed. 

First run:

`kamel install`

You should get all the availble `kamelets` offered by Camel K

`oc get kamelets`


### Timer Source integration
Today we are going to use Timer Source.

As soon as the `timer-source.kamelet` is available in your cluster, you can use it in any integration such as the one defined in `timer.groovy`

run the integration via:

`> kamel run timer.groovy --dev` 

As soon as the integration starts, you should be able to log the timer source events:

```
[1] 2022-03-18 08:42:04,211 INFO  [route1] (Camel (camel-1) thread #0 - timer://tick) Hello World
[1] 2022-03-18 08:42:05,202 INFO  [route1] (Camel (camel-1) thread #0 - timer://tick) Hello World
[1] 2022-03-18 08:42:06,203 INFO  [route1] (Camel (camel-1) thread #0 - timer://tick) Hello World
[1] 2022-03-18 08:42:07,203 INFO  [route1] (Camel (camel-1) thread #0 - timer://tick) Hello World
```

### Timer Source KameletBinding 

Make sure you have `OpenShift-Serverless` operator installed. Don't forget to install/create an instance of `Knative Eventing` and `Knative Serving`

First you should declare the knative destination:

`oc apply -f messages-channel.yaml`

Once the destination is ready, you can reference it from a KameletBinding in the`timer-source-kamelet-binding.yaml`. In order to bind the connector to the destination apply the configuration:

`oc apply -f timer-source-kamelet-binding.yaml`

You can confirm the creation of the of the `KameletBinding` listing the resources:

`oc apply get kameletbindings`