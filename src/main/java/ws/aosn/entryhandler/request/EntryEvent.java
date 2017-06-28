/*
 * Copyright (c) 2017 Alice on Sunday Nights Workshop Participants. All rights reserved.
 */

package ws.aosn.entryhandler.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EntryEvent request entity.
 *
 * @author mikan
 * @see <a href="http://docs.aws.amazon.com/ja_jp/lambda/latest/dg/java-handler-io-type-pojo.html">Developer Guide</a>
 * @since 0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryEvent {
    private String id; // GitHub ID
    private String name; // Real Name
    private String email; // Email Address
    private String part; // EntryEvent part ("a", "b", "ab" or empty)
    private String comment; // Comment field

    public boolean isValid() {
        return id != null && !id.isEmpty() && name != null && !name.isEmpty() && email != null && !email.isEmpty();
    }
}
