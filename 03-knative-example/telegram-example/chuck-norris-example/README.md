## Chuck Norris Telegram Bot Example

This example shows you how to use Kamelets and KameletBinding to create a telegram bot that can send you Chuck Norris jokes.

### Create chuck-norris-source

First of all, we need to create a Kamelet `chuck-norris-source`:

`oc apply -f chuck-norris-source.kamelet.yaml`

### Create a message channel

We need to create an in-memory channel as a sink to receive the event from 
`chuck-norris-source`:

`oc apply -f messages-channel.yaml`

### Create a KameletBinding 

Now we need to create connection between the `messages` channel and `chuck-norris-source` by using `KameletBinding`:

`oc apply -f chuck-noris-binding.yaml`

### Create a telegram sink

In order to receive the joke from our telegram bot, we need to create a telegram sink (`kamelet`):

`oc apply -f telegram-simple-sink.yaml`

and we need to connect the sink to the event channel `messages`:

`oc apply -f telegram-sink-binding.yaml`

In this file, you should provide your Chat ID and your telegram bot token.

After 1-2 min, you should see the telegram bot starting sending you Chuck Norris Joke.

