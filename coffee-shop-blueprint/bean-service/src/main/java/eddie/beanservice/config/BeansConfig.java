package eddie.beanservice.config;

import eddie.coffeeshopcomponent.model.serializer.EventDeserializer;
import eddie.coffeeshopcomponent.model.serializer.EventSerializer;
import eddie.coffeeshopcomponent.store.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig{

    @Bean
    public EventSerializer eventSerializer(){
        return new EventSerializer();
    }

    @Bean
    public EventDeserializer eventDeserializer(){
        return new EventDeserializer();
    }

    @Bean
    public EventStore eventStore(){
        return new EventStore();
    }

}
