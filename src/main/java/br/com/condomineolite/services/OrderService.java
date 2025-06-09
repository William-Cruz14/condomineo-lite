package br.com.condomineolite.services;

import br.com.condomineolite.dtos.order.OrderRequestDTO;
import br.com.condomineolite.dtos.order.OrderResponseDTO;
import br.com.condomineolite.models.Order;
import br.com.condomineolite.models.Resident;
import br.com.condomineolite.repositories.OrderRepository;
import br.com.condomineolite.repositories.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Transactional(readOnly = true)
    public Page<OrderResponseDTO> findAll(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.map(OrderResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<OrderResponseDTO> findById(Long id) {
        return orderRepository.findById(id)
                .map(OrderResponseDTO::new);
    }

    @Transactional
    public OrderResponseDTO create(OrderRequestDTO dto) {
        Order order = new Order();
        order.setCode(dto.getCode());
        order.setDescription(dto.getDescription());
        order.setReceived(dto.getReceived());
        order.setDelivered(dto.getDelivered());

        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado: " + dto.getResidentId()));

        order.setResident(resident);

        order = orderRepository.save(order);

        return new OrderResponseDTO(order);
    }

    @Transactional
    public Optional<OrderResponseDTO> update(Long id, OrderRequestDTO dto) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            return Optional.empty();
        }
        Order order = orderOpt.get();
        order.setCode(dto.getCode());
        order.setDescription(dto.getDescription());
        order.setReceived(dto.getReceived());
        order.setDelivered(dto.getDelivered());

        Resident resident = residentRepository.findById(dto.getResidentId())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado: " + dto.getResidentId()));
        order.setResident(resident);

        Order updatedOrder = orderRepository.save(order);
        return Optional.of(new OrderResponseDTO(updatedOrder));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!orderRepository.existsById(id)) {
            return false;
        }
        orderRepository.deleteById(id);
        return true;
    }
}
