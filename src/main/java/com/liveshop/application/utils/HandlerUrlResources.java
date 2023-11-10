package com.liveshop.application.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;


@RequiredArgsConstructor
@Slf4j
@Component
public class HandlerUrlResources {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private Set<String> restAPIResources;

    @Bean(name = "restAPIResources")
    public Set<String> getSet() {
        if(restAPIResources != null){
            return restAPIResources;
        }
        restAPIResources = new HashSet<>();

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> requestMappingInfoHandlerMethodEntry : handlerMethods.entrySet()) {
            String baseUrl = requestMappingInfoHandlerMethodEntry.getKey().getActivePatternsCondition().toString();
             List<String> resources = Arrays.stream(baseUrl.substring(2, baseUrl.length() - 1)
                     .split("/"))
                     .filter(s -> !s.contains("{") || !s.contains("}"))
                     .toList();
             restAPIResources.addAll(resources);
        }

        log.debug(String.valueOf(restAPIResources));

        restAPIResources = Collections.unmodifiableSet(restAPIResources);

        return restAPIResources;
    }
}
