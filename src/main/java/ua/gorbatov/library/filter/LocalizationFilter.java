package ua.gorbatov.library.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocalizationFilter implements Filter {
    private String defaultLocale;
    public static final String LOCALE = "locale";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultLocale = filterConfig.getInitParameter(LOCALE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String localeParameter = httpServletRequest.getParameter(LOCALE);

        if (isNotBlank(localeParameter)) {
            httpServletRequest.getSession().setAttribute(LOCALE, localeParameter);

        } else {
            String sessionLocale = (String) httpServletRequest.getSession().getAttribute(LOCALE);
            if(isBlank(sessionLocale)){
                httpServletRequest.getSession().setAttribute(LOCALE, defaultLocale);
            }
        }
        httpServletRequest.getSession().getAttribute(LOCALE);
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }

    private boolean isBlank(String locale) {
        return locale == null || locale.isEmpty();
    }

    private boolean isNotBlank(String locale) {
        return !isBlank(locale);
    }
}
