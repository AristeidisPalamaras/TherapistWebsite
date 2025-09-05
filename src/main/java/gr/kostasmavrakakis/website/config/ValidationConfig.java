// package gr.kostasmavrakakis.website.config;

// import org.springframework.context.MessageSource;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

// @Configuration
// public class ValidationConfig {

//     private final MessageSource messageSource;

//     public ValidationConfig(MessageSource messageSource) {
//         this.messageSource = messageSource;
//     }

//     @Bean
//     public LocalValidatorFactoryBean getValidator() {
//         LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//         validator.setValidationMessageSource(messageSource);
//         return validator;
//     }
// }
