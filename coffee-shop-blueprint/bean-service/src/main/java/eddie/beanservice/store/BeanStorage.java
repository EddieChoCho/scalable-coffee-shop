package eddie.beanservice.store;

import eddie.coffeeshopcomponent.model.events.BeansFetched;
import eddie.coffeeshopcomponent.model.events.BeansStored;
import eddie.coffeeshopcomponent.model.events.CoffeeEvent;
import eddie.coffeeshopcomponent.store.CoffeeApplier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BeanStorage implements CoffeeApplier {

    private Map<String, Integer> beanOrigins = new ConcurrentHashMap<>();

    public Map<String, Integer> getStoredBeans() {
        return Collections.unmodifiableMap(beanOrigins);
    }

    public int getRemainingAmount(final String beanOrigin) {
        return beanOrigins.getOrDefault(beanOrigin, 0);
    }

    @Override
    public void apply(final CoffeeEvent event){
        if(event instanceof BeansStored){
            this.apply((BeansStored)event);
        }else if(event instanceof BeansFetched){
            this.apply((BeansFetched)event);
        }
    }

    public void apply(final BeansStored beansStored) {
        beanOrigins.merge(beansStored.getBeanOrigin(), beansStored.getAmount(), Math::addExact);
    }

    public void apply(final BeansFetched beansFetched) {
        beanOrigins.merge(beansFetched.getBeanOrigin(), 0, (i1, i2) -> i1 - 1);
    }

}
