package com.ywt.laike.hotel.config;

import com.ywt.laike.hotel.web.interceptor.ActivationInterceptor;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @author ywt
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    @Bean
    public ActivationInterceptor getActivationInterceptor() {
        return new ActivationInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getActivationInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/index.html");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/static/dist/");
        super.addResourceHandlers(registry);
    }

//    /**
//     * 配置默认错误页面（仅用于内嵌tomcat启动时）
//     * 使用这种方式，在打包为war后不起作用
//     *
//     * @return
//     */
//    @Bean
//    public EmbeddedServletContainerCustomizer containerCustomizer() {
//        return container -> {
//            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
//            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
//            container.addErrorPages(error404Page, error500Page);
//        };

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
//        return factory -> {
//            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
//            factory.addErrorPages(error404Page);
//        };
//    }
    }
