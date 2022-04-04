## Camel K Hello World Example

This example demonstrates a simple Camel K integration that periodically prints a "Hello World from Camel K" message.

Ensure that you have an OpenShift cluster available and the necessary command line tools such as `oc` and `kamel` installed on your laptop/computer. Remember to check the requirements on [README.me](https://github.com/nexus-Six/camelk-integration-workshop/blob/master/README.md)

### 1. Preparing the project 

We will create a new project for running this example. This can be done by directly from the OpenShift Web Console or by executing the command:

```
oc new-project camelk-helloworld
oc project camelk-helloworld
```

### 2. Running a simple integration
This example contains a simple Camel K integration that prints out a "Hello World from Camel K" message. The `helloworld.groovy` file contains a simple integration that uses `timer` and `log` components. 

> NOTE: Camel K supports multiple languages for writing integrations, e.g. Java, XML, YAML, Groovy, Kotlin, JavaScript and so on. You can find various examples using different languages in the examples pack that is downloadable from the [release page](https://github.com/apache/camel-k/releases). 

> Dependency management is automatically handled by Camel K that imports all required libraries from the Camel catalog via code inspection. This means you can use all 300+ Camel components directly in your routes.

We are ready to run the integration on our `camelk-helloworld` project in the cluster. Use the following command to run it in "dev mode", in order to see the logs in the integration terminal:

`kamel run helloworld.groovy --dev`

If everything is ok, after the build phase finishes, you should see the Camel integration running and continuously printing "Hello World from Camel K." in the terminal.

When running in dev mode, you can change the integration code and let Camel K redeploy the changes automatically.

To exit **dev mode** and terminate the running integration, hit `ctrl+c`.

To run the integration without "dev mode", please simply run:

`kamel run helloworld.groovy`

After executing the command, you can check the integratinos by running:

`oc get integrations`   

or 

`kamel get`

You can check the logs of the integration using the command:

`kamel log <integration name>`
