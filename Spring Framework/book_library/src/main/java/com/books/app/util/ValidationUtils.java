package com.books.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ValidationUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    public static boolean rejectIfNullOrEmpty(Object inputValue) {
        boolean rejected = false;
        if (StringUtils.isEmpty(inputValue)) {
            LOGGER.info("Bad Request");
            rejected = true;
        }
        return rejected;
    }
}
