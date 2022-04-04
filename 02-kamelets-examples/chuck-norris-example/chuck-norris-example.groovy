from("telegram:bots?authorizationToken=<APIkey>")
    .choice()
        .when()
            .simple('${in.body} != null')
            .to("direct:response1") //We have a text received from Telegram


from('kamelet:chuck-norris-source')
    .choice()
        .when(simple('${bodyAs(java.lang.String)} contains "Tell me a joke"'))
        .when(response1.isEqualTo("Tell me a joke"))
            .to("telegram:bots?authorizationToken=<APIkey>")
        .when(not(simple('${bodyAs(java.lang.String)} contains "Tell me a joke"')))
            .to('log:info')
        .otherwise()
            .log("Otherwise clause")
