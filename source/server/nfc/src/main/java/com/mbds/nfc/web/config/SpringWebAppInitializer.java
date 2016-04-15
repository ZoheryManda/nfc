package com.mbds.nfc.web.config;

import com.mbds.nfc.config.AppConfig;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class, RepositoryRestMvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {

        return new String[]{"/rest/*"};
    }

    //Instead we used SecurityWebApplicationInitializer
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                //new DelegatingFilterProxy("springSecurityFilterChain")
                new OpenEntityManagerInViewFilter()
        };
    }

}
