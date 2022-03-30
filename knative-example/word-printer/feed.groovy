from('timer:clock?period=3000')
  .setBody().constant("Hello World from Camel K")
  .to('knative:channel/messages')
  .log('sent message to messages channel')