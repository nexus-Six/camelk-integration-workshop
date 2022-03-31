from('kamelet:timer-source?message=Hello+World&period=1000')
    .log('${body}')
