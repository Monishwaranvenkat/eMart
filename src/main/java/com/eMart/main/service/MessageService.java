package com.eMart.main.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    static String ACCOUNT_SID="ACdafcfd48fdca4433dc28fbe7320789b4";
    static String AUTH_TOKEN="4861fc43f765c81c716bc729952d2704";
    public void sendSMS()
    {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber("+919442472439"), // to
                        new PhoneNumber("+17754512462"), // from
                        "Where's Wallace?")
                .create();

    }
}
