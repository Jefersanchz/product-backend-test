package ProductBackendTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProductBackendTest.dto.OrderDto;
import ProductBackendTest.entity.OrderEntity;
import ProductBackendTest.entity.ProductEntity;
import ProductBackendTest.repository.OrderRepository;
import ProductBackendTest.repository.ProductRepository;
import ProductBackendTest.service.Impl.OrderServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import static ProductBackendTest.mapper.OrderMapper.*;
import static ProductBackendTest.mapper.ProductMapper.productDtoToProductEntity;

@Service
public class OrderService implements OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntitiesToOrderDtos(orderEntities);
    }

    @Override
    public OrderDto getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElse(null);
        if (orderEntity == null) {
            throw new IllegalArgumentException("Order with ID: " + id + " not found.");
        }
        return orderEntityToOrderDto(orderEntity);
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        ProductEntity productEntity = productDtoToProductEntity(orderDto.getProduct());
        if (productEntity.getId() == null || !productRepository.existsById(productEntity.getId())) {
            productEntity = productRepository.save(productEntity);
        }

        OrderEntity orderEntity = orderDtoToOrderEntity(orderDto);
        orderEntity.setProduct(productEntity); 

        OrderEntity savedOrder = orderRepository.save(orderEntity);
        return orderEntityToOrderDto(savedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete, order with ID: " + id + " does not exist.");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getOrdersByDate(LocalDateTime date) {
        List<OrderEntity> orderEntities = orderRepository.findByDate(date);
        return orderEntitiesToOrderDtos(orderEntities);
    }

    @Override
    public List<OrderDto> getOrdersByRangeDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderEntity> orderEntities = orderRepository.findByDateBetween(startDate, endDate);
        return orderEntitiesToOrderDtos(orderEntities);
    }

    @Override
    public List<OrderDto> getOrdersByProduct(Long productId) {
        List<OrderEntity> orderEntities = orderRepository.findByProductId(productId);
        return orderEntitiesToOrderDtos(orderEntities);
    }

    @Override
    public List<OrderDto> getOrdersWithQuantityGreaterThan(Integer minimumQuantity) {
        List<OrderEntity> orderEntities = orderRepository.findByQuantityGreaterThan(minimumQuantity);
        return orderEntitiesToOrderDtos(orderEntities);
    }

    @Override
    public Double getTotalSalesInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.getTotalSalesInDateRange(startDate, endDate);
    }
}
