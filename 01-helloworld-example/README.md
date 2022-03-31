## Camel K Hello World Example

This example shows a simple Camel K integration that periodically prints a "Hello World from Camel K" message.

Use the following command to run it in "dev mode", in order to see the logs in the integration terminal:

`kamel run helloworld.groovy --dev`

If everything is ok, after the build phase finishes, you should see the Camel integration running and continuously printing "Hello World!..." in the terminal window.

When running in dev mode, you can change the integration code and let Camel K redeploy the changes automatically.