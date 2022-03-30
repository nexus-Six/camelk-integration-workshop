from('telegram:bots?authorizationToken=5192208346:AAGTcJjERQYOpP4f0TgQwVPGZUJ_iQBT3io')
  .convertBodyTo(String.class)
  .to('log:info')
  .to('knative:channel/messages')