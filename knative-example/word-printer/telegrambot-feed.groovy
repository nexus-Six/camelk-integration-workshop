
from('telegram:bots?authorizationToken=<yourBotToken>&ChatID=<yourChatID>')
  .convertBodyTo(String.class)
  .to('log:info')
  .to('knative:channel/messages')