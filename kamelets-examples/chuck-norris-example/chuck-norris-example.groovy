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

//     .choice()
//         .when()
//             .simple('${in.body} != null')
//             .to("direct:joke")

// from("telegram:bots?authorizationToken=<APIkey>")
//     .choice()
//         .when()
//             .simple('${in.body} != null')
//             .to("direct:response1") //We have a text received from Telegram

// from("direct:response1")
//     .log('Incoming message from Telegram is:  \"${in.body}\"')
//         .choice()
//         .when(simple('${bodyAs(java.lang.String)} contains "Tell me a joke"'))
//         .setBody()
//             .simple('${direct:joke}')
//             .to("telegram:bots?authorizationToken=<APIkey>")

// from("direct:response1")
//     .log('Hehe')
//     .from('kamelet:chuck-norris-source')
//         .choice()
//         .when(simple('${bodyAs(java.lang.String)} contains "Tell me a joke"'))
//         .to("telegram:bots?authorizationToken=<APIkey>")
