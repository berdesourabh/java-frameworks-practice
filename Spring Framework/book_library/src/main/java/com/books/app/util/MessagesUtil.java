package com.books.app.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessagesUtil implements ApplicationContextAware {

    private static ApplicationContext appContext;
    private static ReloadableResourceBundleMessageSource messageSource;

    @Override
    public void setApplicationContext(ApplicationContext appContext) {
        MessagesUtil.appContext = appContext;
    }

    public static String getMessage(String key) {
        if (messageSource == null)
            messageSource = appContext.getBean(ReloadableResourceBundleMessageSource.class);
        Locale locale = LocaleContextHolder.getLocale();
        if (!locale.getLanguage().equals("en"))
            messageSource.setDefaultEncoding("UTF-8");
        return messageSource.getMessage(key, null, locale);
    }

}
