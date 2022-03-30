from('knative:channel/telegram')
    .unmarshal()
    .json()
    .setBody { it.in.body.text }
    .log('Received: ${body}')