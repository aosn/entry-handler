/*
 * Copyright (c) 2017 Alice on Sunday Nights Workshop Participants. All rights reserved.
 */

import org.junit.Test;
import ws.aosn.entryhandler.LambdaHandler;

/**
 * @author mikan
 * @since 0.1
 */
public class InvokeFromOutsidePackageTest {

    @Test
    public void testLambdaHandler() {
        new LambdaHandler();
    }
}
