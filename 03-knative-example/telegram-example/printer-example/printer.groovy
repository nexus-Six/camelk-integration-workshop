from('knative:channel/telegram')
  .convertBodyTo(String.class)
  .to('log:info')