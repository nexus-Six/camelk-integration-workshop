## Get the price of Cardano coin from Binance and send it to the telegram channel

Make sure you have the `kamel` CLI, `kn` CLI and `oc` CLI installed. Below links might help:
https://camel.apache.org/camel-k/1.8.x/installation/installation.html
https://docs.openshift.com/container-platform/4.10/serverless/cli_tools/installing-kn.htm


Install "Red Hat Integration - Camel K" operator on your OpenShift cluster.
Additionally, install "Red Hat OpenShift Serverless" operator and from there, create `Knative Serving` and `Knative Eventing` instances.

Swap Telegram bot credentials to your own in `crypto-display.yaml`. It should look like this:

`.to("telegram:bots?authorizationToken=XXXXXXXXX:YYYYYYY-YYYYYYYYYYYYYYYYY_YY&chatID=XXXXXXXXXX")`

You can get your chatID value by sending any message to your bot in Telegram and then opening below address in your browser:

`https://api.telegram.org/botXXXXXXXXX:YYYYYYY-YYYYYYYYYYYYYYYYY_YY/getUpdates`

Copy your chatID from the response.

Run:

`kamel run crypto-example.yaml`

And then:

`kamel run crypto-display.groovy`

You can of course see the prices of other cryptocurencies, just change the symbol in the link in `crypto-example.yaml` to e.g. `BTCUSDT`.