package com.rihongo.map.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtil {

    private final MessageSource messageSource;

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
