from('kamelet:chuck-norris-source')
    //.to("telegram:bots?authorizationToken=<YOUR_TOKEN_AND_CHATID>")     
    .to('log:info')