package com.lukefitness.lukegymbackend.config;

import com.lukefitness.lukegymbackend.interceptor.SendVerifyEmailInterceptor;
import com.lukefitness.lukegymbackend.interceptor.TraineeCheckInterceptor;
import com.lukefitness.lukegymbackend.interceptor.TrainerCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.lukefitness.lukegymbackend.interceptor.AdminCheckInterceptor;

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

    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
    }

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(adminCheckInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(traineeCheckInterceptor).addPathPatterns("/trainee/**");
        registry.addInterceptor(trainerCheckInterceptor).addPathPatterns("/trainer/**");
        registry.addInterceptor(sendVerifyEmailInterceptor).addPathPatterns("/send-verify-email/**");
    }
}
