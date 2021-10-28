package com.kindsonthegenius.ecomdemo.controllers;

import com.kindsonthegenius.ecomdemo.api.CreateOrderCommand;
import com.kindsonthegenius.ecomdemo.query.GetOrdersQuery;
import com.kindsonthegenius.ecomdemo.read_model.OrderSummary;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderController {

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public OrderController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/createOrder")
    public void handle(@RequestParam MultiValueMap summary){
        CreateOrderCommand cmd = new CreateOrderCommand(
                UUID.randomUUID(),
                Double.valueOf(summary.getFirst("price").toString()),
                Integer.parseInt(summary.getFirst("number").toString()),
                summary.getFirst("productid").toString()
        );

        commandGateway.send(cmd);
    }

    @GetMapping("/orders")
    public CompletableFuture<List<OrderSummary>> getOrders(){
        return queryGateway.query(new GetOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderSummary.class));
    }

}
