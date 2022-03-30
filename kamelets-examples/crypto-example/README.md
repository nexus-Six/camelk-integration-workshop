## Get the price of Cardano coin from Binance and send it to the telegram channel

Make sure you have the `kamel` CLI, `kn` CLI and `oc` CLI installed. 

Install Camel K operator on your OpenShift cluster.
Additionally, install Serverless operator and from there `Knative Serving` and `Knative Eventing` instances.

Swap Telegram bot credentials to your own in `crypto-display.yaml`

Run:

`kamel run crypto-example.yaml`

And then:

`kamel run crypto-display.groovy`

You can of course see the prices of other cryptocurencies, just change the symbol in the link in `crypto-example.yaml` to e.g. `BTCUSDT`.