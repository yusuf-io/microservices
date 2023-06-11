package com.xyz.order_service.controller;

import com.xyz.order_service.dto.OrderRequest;
import com.xyz.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // inventory is the instance name of circuitbreaker defined in application.properties
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Order is placed successfully";
    }

    /**
     * fall back function for failures
     *
     * @param orderRequest     order request which is same args for placeOrder (aka, same method signature)
     * @param runtimeException run time exception
     * @return text that will send back to client when inventory service is problematic
     */
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {

        // NOTE: Event if the args are not used inside this method, are still needed in order to satisfy the method signature

        return "Oops! Something went wrong, please order after some time!";
    }
}
