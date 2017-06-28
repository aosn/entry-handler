/*
 * Copyright (c) 2017 Alice on Sunday Nights Workshop Participants. All rights reserved.
 */

package ws.aosn.entryhandler;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Test;
import ws.aosn.entryhandler.request.EntryEvent;
import ws.aosn.entryhandler.mail.MailSender;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test {@link LambdaHandler}.
 *
 * @author mikan
 * @since 0.1
 */
public class LambdaHandlerTest {

    @Test
    public void testHandleRequest_normalInput() {
        MailSender mailSender = mock(MailSender.class);
        LambdaHandler sut = new LambdaHandler(mailSender);
        EntryEvent entryEvent = new EntryEvent("test", "Test User", "test@test.com", "ab", "comment");
        sut.handleRequest(entryEvent, mock(Context.class));
        verify(mailSender).sendToReception(eq(entryEvent));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleRequest_emptyInput() {
        LambdaHandler sut = new LambdaHandler();
        sut.handleRequest(new EntryEvent(), mock(Context.class));
    }
}
