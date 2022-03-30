This example shows how Camel K can be used to connect Knative building blocks to create awesome applications.

It's assumed that both Camel K and Knative are properly installed (including Knative Build, Serving and Eventing) into the cluster. Refer to the specific documentation to install and configure all components.

We're going to create two channels:

  - messages
  - words

```
oc apply -f messages-channel.yaml
oc apply -f words-channel.yaml
```
### Install a printer
We'll install a Camel K integration that will print all words from the `words` channel by running:

`kamel run printer.groovy`

### Install a Splitter
We're now going to deploy a splitter, using the Camel core Split EIP. The splitter will take all messages from the `messages` channel, split them and push the single words into the `words` channel.

`kamel run splitter.groovy`

### Install a feed

We're going to feed this chain of functions using a timed feed like this:
`kamel run feed.groovy`