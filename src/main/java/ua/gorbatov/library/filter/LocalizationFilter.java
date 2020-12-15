package ua.gorbatov.library.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import org.apache.log4j.Logger;

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
            httpServletRequest.getSession().setAttribute(LOCALE, defaultLocale);

        }
        filterChain.doFilter(servletRequest,servletResponse);

        //        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
//        String localeParameter = servletRequest.getParameter("locale");
//
//        httpRequest.getSession().setAttribute("locale",localeParameter);
//
//        if(httpRequest.getParameter("language")!= null) {
//            httpRequest.getSession().setAttribute("language", new Locale((httpRequest).getParameter("language")));
//        }
//        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isNotBlank(String locale) {
        return (locale != null && !locale.isEmpty());
    }
}
