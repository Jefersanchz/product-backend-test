package ProductBackendTest.service.Impl;

import ProductBackendTest.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderServiceImpl {

   
    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto saveOrder(OrderDto orderDto);

    void deleteOrder(Long id);

    List<OrderDto> getOrdersByDate(LocalDateTime date);

    List<OrderDto> getOrdersByRangeDate(LocalDateTime startDate, LocalDateTime endDate);

    List<OrderDto> getOrdersByProduct(Long productId);

    List<OrderDto> getOrdersWithQuantityGreaterThan(Integer minimumQuantity);

    Double getTotalSalesInDateRange(LocalDateTime startDate, LocalDateTime endDate);
}
