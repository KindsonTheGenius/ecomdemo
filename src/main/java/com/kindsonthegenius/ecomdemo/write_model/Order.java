package com.kindsonthegenius.ecomdemo.write_model;

import com.kindsonthegenius.ecomdemo.api.CreateOrderCommand;
import com.kindsonthegenius.ecomdemo.api.OrderCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Order {

    @AggregateIdentifier
    private UUID orderId;
    private Double price;
    private Integer number;
    private String productId;


    public Order(){
    }

    @CommandHandler
    public Order(CreateOrderCommand cmd){
        apply(
                new OrderCreatedEvent(
                        cmd.getOrderId(), cmd.getPrice(), cmd.getNumber(), cmd.getProductId()
                )
        );
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent evt){
        orderId = evt.getOrderId();
        price = evt.getPrice();
        number = evt.getNumber();
        productId = evt.getProductId();
    }
}
