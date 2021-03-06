# The CQRS Event Sourcing Coffee Shop Service  

## Reference
* [Greg Young - CQRS and Event Sourcing - Code on the Beach 2014](https://youtu.be/JHGkaShoyNs)
* [Sebastian Daschner: Video Course — Event Sourcing, Distributed Systems & CQRS](https://blog.sebastian-daschner.com/entries/event_sourcing_cqrs_video_course)
* [Microsoft: CQRS Journey](https://docs.microsoft.com/en-us/previous-versions/msp-n-p/jj554200(v=pandp.10))
* [Patterns & Practices Symposium Online 2012 A Journey into CQRS](https://youtu.be/MSof4jl6cNg)

## Kafka

### Producer and Consumer
* Using KafkaTemplate as message producer.

* Using @KafkaListener annotation to mark a method to be the target of a Kafka message listener.

### How to Run Kafka in Windows

https://dzone.com/articles/running-apache-kafka-on-windows-os

## Todo
- [x] Replace EventStore with message queue
- [x] Modify event listener
- [x] Create Multiple Service for Different Domain
- [ ] Separate command and query modules
- [ ] Cooperate with database
 
 ## Sequence Diagram
![alt text](https://raw.githubusercontent.com/EddieChoCho/scalable-coffee-shop/master/coffee-shop-blueprint/src/main/resources/images/The%20Scalable%20Coffee%20Shop.png)
