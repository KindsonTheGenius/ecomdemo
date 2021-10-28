package com.kindsonthegenius.ecomdemo.controllers;

import com.kindsonthegenius.ecomdemo.api.AddProductCommand;
import com.kindsonthegenius.ecomdemo.query.GetProductsQuery;
import com.kindsonthegenius.ecomdemo.read_model.ProductSummary;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProductController {

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public ProductController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("addProduct")
    public void handle(@RequestBody  ProductSummary summary){
        AddProductCommand cmd = new AddProductCommand(
                summary.getId(),
                summary.getPrice(),
                summary.getStock(),
                summary.getDescription()
        );
        commandGateway.sendAndWait(cmd);
    }

    @GetMapping("/products")
    public CompletableFuture<List<ProductSummary>> getProducts(){
        return  queryGateway.query(new GetProductsQuery(), ResponseTypes.multipleInstancesOf(ProductSummary.class));
    }
}
