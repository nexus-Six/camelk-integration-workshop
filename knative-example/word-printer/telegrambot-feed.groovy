// haven't test


from('telegram:bots?authorizationToken=<yourToken&ChatID>')
  .convertBodyTo(String.class)
  .to('log:info')
  .to('knative:channel/messages')