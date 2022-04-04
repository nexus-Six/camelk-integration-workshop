# Chuck Norris Source Kamelet

This example demonstrates how to create a simple Chuck Norris Kamelet resource and run a simple integration which shows you the jokes in the terminal.

Ensure that you have an OpenShift cluster available and the necessary command 
line tools such as `oc` and `kamel` installed on your laptop/computer. 
Remember to check the requirements on [README.md](https://github.com/nexus-Six/
camelk-integration-workshop/blob/master/README.md).  

## Preparing the project

We will create a new project for running this example. This can be done by 
directly from the OpenShift 
Web Console or by executing the command:

```
oc new-project chuck-norris-example
oc project chuck-norris-example
```

## Introduction to Kamelets

Kamelets (**Kamel** route snipp**ets**) are a concept that allow users to 
connect to external systems via a simplified interface. A `Kamelet` can act as 
**source** of data or alternatively a **sink**: a source allows to consume 
data from an external system, while a sink allows you to send data to an 
external system or execute a particular action and get the result. 

In this example, we will create a **chuck-norris-source** that calls the Chuck 
Norris API to get the jokes. 


### Create chuck-norris-source

The `chuck-norris-source.kamelet.yaml` file contains the basic definition of a 
`Kamelet` resources. 

> - A `metadata` section contains the `name` of the Kamelet and other 
information such as the tyoe of Kamelet (`source` or `sink`)
> - A `spec` section contains the description and a set of parameters in JSON 
format that you can use to configure the Kamelet
> - A`type` section contains the information about the output media type (e.g. 
`application/json`, `text/plain`)
> - A route `template`/`flow` defines the detailed behaviour of the Kamelet

We can create the Kamelet `chuck-norris-source` by executing the command:

`oc apply -f chuck-norris-source.kamelet.yaml`

> Kamelets are standard YAML files, but the extension `.kamelet.yaml` can help 
IDEs to recognize them.
> NOTE: A wide set of canned Kamelets is availble at [Apache Kamelet Catalog]
(https://camel.apache.org/camel-kamelets/next/index.html)


### Run an integration

If you run the integration in dev mode, you will be able to see the chuck norris joke in the terminal window:

`kamel run joke-log.yaml --dev`

Alternatively, you can also run the integration by:

`kamel run joke-log.yaml`

and retrieve the logs by:

`kamel logs joke-log`

