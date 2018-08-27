package com.carl.auth.sso.client.demo.utils;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;

import java.util.regex.Pattern;

public final class RegexUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {
    private Pattern pattern;

    public RegexUrlPatternMatcherStrategy() {}

    public RegexUrlPatternMatcherStrategy(final String pattern) {
        this.setPattern(pattern);
    }

    public boolean matches(final String url) {
        return this.pattern.matcher(url).find();
    }

    public void setPattern(final String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

}