package eddie.coffeeshopblueprint.service;

import eddie.coffeeshopblueprint.events.BeansFetched;
import eddie.coffeeshopblueprint.events.BeansStored;
import eddie.coffeeshopblueprint.events.OrderBeansReserved;
import eddie.coffeeshopblueprint.events.OrderFailedBeansNotAvailable;
import eddie.coffeeshopblueprint.store.BeanStorage;
import eddie.coffeeshopblueprint.store.EventStore;

import java.util.UUID;

public class BeanCommandService {

    private EventStore eventStore;
    private BeanStorage beanStorage;

    public BeanCommandService(final EventStore eventStore, final BeanStorage beanStorage){
        this.eventStore = eventStore;
        this.beanStorage = beanStorage;
    }

    public void storeBeans(final String beanOrigin, final int amount) {
        eventStore.publish(new BeansStored(beanOrigin, amount));
    }

    public void reserveBeans(final String beanOrigin, final UUID orderId) {
        if (beanStorage.getRemainingAmount(beanOrigin) > 0)
            eventStore.publish(new OrderBeansReserved(orderId), new BeansFetched(beanOrigin));
        else
            eventStore.publish(new OrderFailedBeansNotAvailable(orderId));
    }

}
