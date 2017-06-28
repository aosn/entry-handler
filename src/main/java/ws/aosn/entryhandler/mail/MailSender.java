/*
 * Copyright (c) 2017 Alice on Sunday Nights Workshop Participants. All rights reserved.
 */

package ws.aosn.entryhandler.mail;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import lombok.extern.log4j.Log4j;
import ws.aosn.entryhandler.request.EntryEvent;

/**
 * Mail sender implementation for Amazon Simple Mail Service.
 *
 * @author mikan
 * @see <a href="http://docs.aws.amazon.com/ja_jp/ses/latest/DeveloperGuide/send-using-sdk-java.html">Developer Guide</a>
 * @since 0.1
 */
@Log4j
public class MailSender {
    private static final String FROM = "entry@aosn.ws";
    private static final String TO = "entry@aosn.ws";
    private static final String SUBJECT = "【AOSN読書会】参加エントリー";
    private final Regions regions;

    public MailSender(Regions regions) {
        this.regions = regions;
    }

    public void sendToReception(EntryEvent entryEvent) {
        if (!entryEvent.isValid()) {
            throw new IllegalArgumentException("required items are not entered.");
        }
        Destination destination = new Destination().withToAddresses(TO);
        Content subject = new Content().withData(SUBJECT);
        Content textBody = new Content().withData(createBody(entryEvent));
        Body body = new Body().withText(textBody);
        Message message = new Message().withSubject(subject).withBody(body);
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);
        try {
            AmazonSimpleEmailServiceClientBuilder.standard().withRegion(regions).build().sendEmail(request);
            log.info("Email sent!");
        } catch (Exception ex) {
            log.error("The email was not sent.", ex);
        }
    }

    private String createBody(EntryEvent event) {
        return "下記の通り参加エントリーがありました。手続きを行ってください。\n\n" +
                "ID: " + event.getId() + "\n" +
                "Name: " + event.getName() + "\n" +
                "Email: " + event.getEmail() + "\n" +
                "Part: " + event.getPart() + "\n" +
                "Comment: " + event.getComment() + "\n";
    }
}
