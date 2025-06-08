package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.order.OrderResponseDTO;
import br.com.condomineolite.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderResponseDTO>> findAll(Pageable pageable) {
        Page<OrderResponseDTO> orders = orderService.findAll(pageable);
        return ResponseEntity.ok(orders);
    }
}
