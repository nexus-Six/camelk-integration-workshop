## Chuck Norris Source Kamelet

This example will show you how to create a simple Chuck Norris Kamelet source and run a simple integration which shows you the jokes in your terminal logs.

### Create Chuck Norris Source

`oc apply -f chuck-norris-source.kamelet.yaml`

### Run an integration

If you run the integration in dev mode, you will be able to see the chuck norris joke in your terminal:

`kamel run joke-log.yaml --dev`

Alternatively, you can also run the integration by:

`kamel run joke-log.yaml`

and retrieve the logs by:

`kamel logs joke-log`

