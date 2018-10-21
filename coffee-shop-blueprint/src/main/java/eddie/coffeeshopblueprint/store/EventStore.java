package eddie.coffeeshopblueprint.store;

import eddie.coffeeshopblueprint.events.*;
import eddie.coffeeshopblueprint.handler.BaristaEventHandler;
import eddie.coffeeshopblueprint.handler.BeanEventHandler;
import eddie.coffeeshopblueprint.handler.OrderEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

//TODO: Replace this with message Queue
public class EventStore {
    private static final Logger log = LoggerFactory.getLogger(EventStore.class);
    private List<CoffeeEvent> events ;
    private BaristaEventHandler baristaEventHandler;
    private BeanEventHandler beanEventHandler;
    private OrderEventHandler orderEventHandler;
    private CoffeeOrders coffeeOrders;
    private CoffeeBrews coffeeBrews;
    private BeanStorage beanStorage;

    public EventStore(){
        events = new LinkedList<>();
    }

    /** Step 0**/
    public void publish(final BeansStored beansStored){
        log.info("BeansStored: " + beansStored.toString());
        events.add(beansStored);
        beanStorage.apply(beansStored);
    }

    /** Step 1**/
    public void publish(final OrderPlaced orderPlaced){
        log.info("OrderPlaced: " + orderPlaced.toString());
        events.add(orderPlaced);
        coffeeOrders.apply(orderPlaced);
        beanEventHandler.handle(orderPlaced);
    }

    /** Step 2 - 1**/
    public void publish(final OrderBeansReserved orderBeansReserved, final BeansFetched beansFetched){
        log.info("OrderPlaced: " + orderBeansReserved.toString() + ", BeansFetched: " + beansFetched.toString());
        events.add(orderBeansReserved);
        events.add(beansFetched);
        beanStorage.apply(beansFetched);
        orderEventHandler.handle(orderBeansReserved);
    }

    /** Step 3**/
    public void publish(final OrderAccepted orderAccepted){
        log.info("OrderAccepted: " + orderAccepted.toString());
        events.add(orderAccepted);
        coffeeOrders.apply(orderAccepted);
        baristaEventHandler.handle(orderAccepted);
    }

    /** Step 4**/
    public void publish(final CoffeeBrewStarted coffeeBrewStarted){
        log.info("CoffeeBrewStarted: " + coffeeBrewStarted.toString());
        events.add(coffeeBrewStarted);
        coffeeBrews.apply(coffeeBrewStarted);
        orderEventHandler.handle(coffeeBrewStarted);
    }

    /** Step 5**/
    public void publish(final OrderStarted orderStarted){
        log.info("OrderStarted: " + orderStarted.toString());
        events.add(orderStarted);
        coffeeOrders.apply(orderStarted);
    }

    /** Step 6**/
    public void publish(final CoffeeBrewFinished coffeeBrewFinished){
        log.info("CoffeeBrewFinished: " + coffeeBrewFinished.toString());
        events.add(coffeeBrewFinished);
        coffeeBrews.apply(coffeeBrewFinished);
        orderEventHandler.handle(coffeeBrewFinished);
    }

    /** Step 7**/
    public void publish(final OrderFinished orderFinished){
        log.info("OrderFinished: " + orderFinished.toString());
        events.add(orderFinished);
        coffeeOrders.apply(orderFinished);
    }

    /** Step 8**/
    public void publish(final CoffeeDelivered coffeeDelivered){
        log.info("CoffeeDelivered: " + coffeeDelivered.toString());
        events.add(coffeeDelivered);
        coffeeBrews.apply(coffeeDelivered);
        orderEventHandler.handle(coffeeDelivered);
    }

    /** Step 9 end **/
    public void publish(final OrderDelivered orderDelivered){
        log.info("OrderDelivered: " + orderDelivered.toString());
        events.add(orderDelivered);
        coffeeOrders.apply(orderDelivered);
    }

    /** Step 2 - 2**/
    public void publish(final OrderFailedBeansNotAvailable orderFailedBeansNotAvailable){
        log.info("OrderFailedBeansNotAvailable: " + orderFailedBeansNotAvailable.toString());
        events.add(orderFailedBeansNotAvailable);
        orderEventHandler.handle(orderFailedBeansNotAvailable);
    }

    /** Step 3 - 2 end **/
    public void publish(final OrderCancelled orderCancelled){
        log.info("OrderCancelled: " + orderCancelled.toString());
        events.add(orderCancelled);
        coffeeOrders.apply(orderCancelled);
    }

    public List<CoffeeEvent> getEvents(){
        return events;
    }

    public void setBaristaEventHandler(BaristaEventHandler baristaEventHandler) {
        this.baristaEventHandler = baristaEventHandler;
    }

    public void setBeanEventHandler(BeanEventHandler beanEventHandler) {
        this.beanEventHandler = beanEventHandler;
    }

    public void setOrderEventHandler(OrderEventHandler orderEventHandler) {
        this.orderEventHandler = orderEventHandler;
    }

    public void setCoffeeOrders(CoffeeOrders coffeeOrders) {
        this.coffeeOrders = coffeeOrders;
    }

    public void setCoffeeBrews(CoffeeBrews coffeeBrews) {
        this.coffeeBrews = coffeeBrews;
    }

    public void setBeanStorage(BeanStorage beanStorage) {
        this.beanStorage = beanStorage;
    }
}
