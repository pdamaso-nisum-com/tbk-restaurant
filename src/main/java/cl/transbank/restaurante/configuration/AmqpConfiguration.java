package cl.transbank.restaurante.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class AmqpConfiguration {

    @Bean
    public MessageConverter messageConverter(MappingJackson2HttpMessageConverter customJsonHttpMessageConverter) {
        return new Jackson2JsonMessageConverter(customJsonHttpMessageConverter.getObjectMapper());
    }

    @Bean
    public TopicExchange salesIngressExchange() {
        return new TopicExchange("sales-ingress-exchange", true, false);
    }

    @Bean
    public Queue salesIngressQueue() {
        return new Queue("sales-ingress-queue", true, false, false);
    }

    @Bean
    public Binding salesIngressBinding(Queue salesIngressQueue,
                                       TopicExchange salesIngressExchange) {
        return BindingBuilder.bind(salesIngressQueue)
                .to(salesIngressExchange)
                .with("sales-ingress-rk");
    }
}
