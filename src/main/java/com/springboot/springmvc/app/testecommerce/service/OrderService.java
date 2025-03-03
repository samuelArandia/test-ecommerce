package com.springboot.springmvc.app.testecommerce.service;

import com.springboot.springmvc.app.testecommerce.exceptions.ClientNotFoundException;
import com.springboot.springmvc.app.testecommerce.models.Client;
import com.springboot.springmvc.app.testecommerce.models.Order;
import com.springboot.springmvc.app.testecommerce.repositories.ClientRepository;
import com.springboot.springmvc.app.testecommerce.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository){
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Order> findByClient(Long clientId) {
        // Primero verifica que el cliente existe
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not found", "CLIENT_NOT_FOUND"));

        return orderRepository.findByClientId(client.getId());
    }

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Object findByClient(Client client){
        return orderRepository.findByClientId(client.getId());
    }


    public Order updateOrderStatus(Long id, String newStatus){
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public void cancelOrder(Long id){
        orderRepository.deleteById(id);
    }


}
