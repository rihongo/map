package com.rihongo.map.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String messageKey) {
        return getMessage(messageKey, null, Locale.getDefault());
    }

    public String getMessage(String messageKey, Object[] args) {
        return messageSource.getMessage(messageKey, args, Locale.getDefault());
    }

    public String getMessage(String messageKey, Object[] args, Locale locale) {
        return messageSource.getMessage(messageKey, args, locale);
    }
}
