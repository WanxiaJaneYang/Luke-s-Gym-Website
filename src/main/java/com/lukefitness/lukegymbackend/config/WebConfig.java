package com.lukefitness.lukegymbackend.config;

import com.lukefitness.lukegymbackend.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is used to configure the web application.
 * It is used to add interceptors to the application.
 * It is used to add CORS mappings to the application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AdminCheckInterceptor adminCheckInterceptor;

    @Autowired
    private TraineeCheckInterceptor traineeCheckInterceptor;

    @Autowired
    private TrainerCheckInterceptor trainerCheckInterceptor;

    @Autowired
    private SendVerifyEmailInterceptor sendVerifyEmailInterceptor;

    @Autowired
    private RefreshTokenInterceptor refreshTokenInterceptor;

    // add CORS mappings to the application, to allow requests from any origin for any method and any header
    // should be used only for development purposes
    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }


    // add interceptors to the application to check if the user is an admin, trainee, or trainer
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(adminCheckInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(traineeCheckInterceptor).addPathPatterns("/trainee/**").excludePathPatterns("/trainee/{traineeId}/token-refresh");
        registry.addInterceptor(trainerCheckInterceptor).addPathPatterns("/trainer/**").excludePathPatterns("/trainer/{trainerId}/token-refresh");
        registry.addInterceptor(sendVerifyEmailInterceptor).addPathPatterns("/send-verify-email/**");
        registry.addInterceptor(refreshTokenInterceptor).addPathPatterns("/trainee/{traineeId}/token-refresh").addPathPatterns("/trainer/{trainerId}/token-refresh");
    }
}
