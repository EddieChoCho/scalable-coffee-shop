package eddie.coffeeshopblueprint.store;

import eddie.coffeeshopblueprint.events.BeansFetched;
import eddie.coffeeshopblueprint.events.BeansStored;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanStorage {

    private Map<String, Integer> beanOrigins = new ConcurrentHashMap<>();

    public Map<String, Integer> getStoredBeans() {
        return Collections.unmodifiableMap(beanOrigins);
    }

    public int getRemainingAmount(final String beanOrigin) {
        return beanOrigins.getOrDefault(beanOrigin, 0);
    }

    public void apply(BeansStored beansStored) {
        beanOrigins.merge(beansStored.getBeanOrigin(), beansStored.getAmount(), Math::addExact);
    }

    public void apply(BeansFetched beansFetched) {
        beanOrigins.merge(beansFetched.getBeanOrigin(), 0, (i1, i2) -> i1 - 1);
    }

}
