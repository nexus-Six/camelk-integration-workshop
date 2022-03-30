from('timer:tick?period=3000')
  .setBody().constant('Hello world from Camel K')
  .to('log:info')