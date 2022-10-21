package com.auto.page;

import com.logigear.statics.Selaium;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class PageFactory {
    private static final Logger logger = LoggerFactory.getLogger(PageFactory.class);
    private static final Map<String, Map<String, Class<?>>> pages = new HashMap<>();

    @SuppressWarnings("unchecked")
    private static <T> T get(String pageName) {
        String platform = Selaium.config().getPlatform().toLowerCase();
        if (StringUtils.isBlank(platform)) {
            throw new IllegalArgumentException("Platform must be configure");
        }
        Class<?> clazz = pages.get(pageName).get(platform);
        if (clazz == null) {
            throw new IllegalArgumentException(String.format("%s for %s is not implemented", pageName, platform));
        }
        try {
            return (T) clazz.newInstance();
        } catch (Exception exception) {
            logger.error("Cannot initial page object", exception);
        }
        throw new RuntimeException("Cannot initial page object for " + pageName);
    }

    public static ILoginPage getLoginPage() {
        return get(Page.Login);
    }


    public static IHomePage getHomePage() {
        return get(Page.Home);
    }


    private static class Page {
        private static final String Login = "LoginPage";
        private static final String Home = "HomePage";
    }
}
