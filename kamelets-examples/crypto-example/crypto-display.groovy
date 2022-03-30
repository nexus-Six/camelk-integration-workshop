from("knative:event/market.ada.usdt")
    .unmarshal()
        .json()
    .setBody {"\nSymbol: "+it.in.body.symbol+"\nPrice: "+it.in.body.price }
        .to("telegram:bots?authorizationToken=<APIToken&ChatID>")