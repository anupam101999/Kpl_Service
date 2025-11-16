package com.kpl.registration.logging;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Log the API endpoint information
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Method: " + request.getMethod());
        // You can also log other relevant information such as request parameters, headers, etc.
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // This method is called after the handler method has been invoked but before the view is rendered (if applicable).
        // You can perform additional logging or processing here if needed.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        // This method is called after the view has been rendered (if applicable) and after any exception handling.
        // You can perform additional logging or processing here if needed.
    }
}
