## Word-Splitter&Printer

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

The resulting integration will be scaled to 0 when not used (if you wait ~5 minutes, you'll see it).

### Install a Splitter
We're now going to deploy a splitter, using the Camel core Split EIP. The splitter will take all messages from the `messages` channel, split them and push the single words into the `words` channel.

`kamel run splitter.groovy`

This integration will be also materialized as a Knative autoscaling service, because the only entrypoint is passive (waits for a push notification).

### Install a feed

We're going to feed this chain of functions using a timed feed like this:

```
from('timer:clock?period=3000')
  .setBody().constant("Hello World from Camel K")
  .to('knative:channel/messages')
  .log('sent message to messages channel')
```
Every 3 seconds, the integration sends a message to the Knative `messages` channel, we can run the following command:

`kamel run feed.groovy`

If you've installed all the services, you'll find that the printer pod will print single words as they arrive from the feed (every 3 seconds, passing by the splitter function).

If you now stop the feed integration (`kamel delete feed`) you will notice that the other services (splitter and printer) will scale down to 0 in few minutes.

And if you reinstall the feed again (`kamel run feed.groovy`), the other integration will scale up again as soon as they receive messages (splitter first, then printer).

## Feed with telegram bot

By running `kamel run telegrambot-feed.groovy`, you can send messages to your  telegram bot and you can see all the single words of the message that you sent appearing in the `logs` of `printer` pod.