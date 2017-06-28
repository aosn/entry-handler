/*
 * Copyright (c) 2017 Alice on Sunday Nights Workshop Participants. All rights reserved.
 */

package ws.aosn.entryhandler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.extern.log4j.Log4j;
import ws.aosn.entryhandler.mail.MailSender;
import ws.aosn.entryhandler.request.EntryEvent;

/**
 * The handler class for AWS Lambda.
 *
 * @author mikan
 * @since 0.1
 */
@Log4j
public class LambdaHandler implements RequestHandler<EntryEvent, EntryEvent> {

    private final MailSender mailSender;

    public LambdaHandler() {
        mailSender = new MailSender(Regions.US_EAST_1);
    }

    LambdaHandler(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public EntryEvent handleRequest(EntryEvent entryEvent, Context context) {
        log.info("Entry received: " + entryEvent);
        mailSender.sendToReception(entryEvent);
        return entryEvent;
    }
}
