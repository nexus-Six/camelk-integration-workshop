from("telegram:bots?authorizationToken=<your-telegram-API-token>")
    .choice()
        .when()
            .simple('${in.body} != null')
            .to("direct:response1")

from("direct:response1")
    .log('Incoming message from Telegram is:  \"${in.body}\"')
        .choice()
        .when(simple('${bodyAs(java.lang.String)} contains "#funny"'))
        .setBody()
            .simple('Did anyone said something #funny? ;)')
            .to("telegram:bots?authorizationToken=<your-telegram-API-token>")
