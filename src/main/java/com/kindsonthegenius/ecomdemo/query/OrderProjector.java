package com.kindsonthegenius.ecomdemo.query;

import com.kindsonthegenius.ecomdemo.api.OrderCreatedEvent;
import com.kindsonthegenius.ecomdemo.api.StockUpdatedEvent;
import com.kindsonthegenius.ecomdemo.read_model.OrderSummary;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderProjector {

    private EventGateway eventGateway;
    private final OrderSummaryRepository repository;


    public OrderProjector(EventGateway eventGateway, OrderSummaryRepository repository) {
        this.eventGateway = eventGateway;
        this.repository = repository;
    }

    @EventHandler
    public void on(OrderCreatedEvent evt){
        OrderSummary summary = new OrderSummary(
                evt.getOrderId(),
                evt.getPrice(),
                evt.getNumber(),
                evt.getProductId()
        );
        repository.save(summary);

       // Fire a Stock updated event
        StockUpdatedEvent event = new StockUpdatedEvent(evt.getProductId(), evt.getNumber());
        eventGateway.publish(event);
    }

    @QueryHandler
    public List<OrderSummary> hadle(GetOrdersQuery qry) {
        return  repository.findAll();
    }
}
