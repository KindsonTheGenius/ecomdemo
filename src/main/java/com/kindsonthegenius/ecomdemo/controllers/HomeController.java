package com.kindsonthegenius.ecomdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public String start(Model model){
        String productUrl = "http://localhost:8087/products";
        Object[] objects = restTemplate.getForObject(productUrl, Object[].class);
        List<Object> products = Arrays.asList(objects);
        model.addAttribute("products", products);
        String orderUrl = "http://localhost:8087/orders";
        List<Object> orders = Arrays.asList(restTemplate.getForObject(orderUrl, Object[].class));
        model.addAttribute("orders", orders);
        return "index2";
    }

    @GetMapping("/index")
    public String home(Model model){
        String productUrl = "http://localhost:8087/products";
        Object[] objects = restTemplate.getForObject(productUrl, Object[].class);
        List<Object> products = Arrays.asList(objects);
        model.addAttribute("products", products);
        String orderUrl = "http://localhost:8087/orders";
        List<Object> orders = Arrays.asList(restTemplate.getForObject(orderUrl, Object[].class));
        model.addAttribute("orders", orders);
        return "index";
    }

    @RequestMapping(value = "/newOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String createOrder(String id, String price){

        String url = "http://localhost:8087/createOrder";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("price", price);
        map.add("productid", id);
        map.add("number", "1");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        restTemplate.postForEntity(url, request, String.class);
        return "redirect:/index";
    }
}
