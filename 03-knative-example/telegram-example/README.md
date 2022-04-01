## Telegram Kamelet Example

In this example, we will create a simple telegram source and use `KameletBinding` to bind the source to a channel. Then we will create a `Printer` that prints out the messages sent to the telegram source.

### Create the telegram source Kamelet

By running `oc apply -f telegram-simple-source.kamelet.yaml`, we will create the telegram source. You can check the ready source by running the command:

`oc get kamelets`

### Create a telegram channel

We need to create an in-memory channel for telegram,

`oc apply -f telegram-channel.yaml` 

or if you have `kn` CLI installed, you can run:

`kn create channel telegram`

### Create the KameletBinding

Now we should bind the telegram source to the channel

`oc apply -f telegram-source-binding.yaml`

Here, we need to put our telegram bot token and our chat ID into the `yaml` file.

### Create the Printer

Now we need to run the "consumer" -- Printer by running:

`kamel run printer.groovy`

It will take a while. As soon as you see the Printer is running, you can try to send some messages to your telegram bot and run

`kamel logs printer`

You should be able to see your chat id, your telegram information and the messages appearing in the logs.