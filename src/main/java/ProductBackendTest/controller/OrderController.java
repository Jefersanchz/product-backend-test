package ProductBackendTest.controller;

import ProductBackendTest.dto.OrderDto;
import ProductBackendTest.service.Impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static ProductBackendTest.constan.PathGeneric.*;

@RestController
@RequestMapping(value = PATH_API_ORDER)
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtoResponse = orderServiceImpl.getAllOrders();
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @GetMapping(value = PATH_ID)
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto orderDtoResponse = orderServiceImpl.getOrderById(id);
        if (orderDtoResponse != null) {
            return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        OrderDto orderDtoResponse = orderServiceImpl.saveOrder(orderDto);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(value = PATH_ID)
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderServiceImpl.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = PATH_DATE)
    public ResponseEntity<List<OrderDto>> getOrdersByDate(@RequestParam LocalDateTime date) {
        List<OrderDto> orderDtoResponse = orderServiceImpl.getOrdersByDate(date);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @GetMapping(value = PATH_RANGE_DATE)
    public ResponseEntity<List<OrderDto>> getOrdersByRangeDate(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<OrderDto> orderDtoResponse = orderServiceImpl.getOrdersByRangeDate(startDate, endDate);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @GetMapping(value = PATH_PRODUCT_PRODUCT_ID)
    public ResponseEntity<List<OrderDto>> getOrdersByProduct(@PathVariable Long productId) {
        List<OrderDto> orderDtoResponse = orderServiceImpl.getOrdersByProduct(productId);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @GetMapping(value = PATH_QUANTITY)
    public ResponseEntity<List<OrderDto>> getOrdersWithQuantityGreaterThan(@RequestParam Integer minimumQuantity) {
        List<OrderDto> orderDtoResponse = orderServiceImpl.getOrdersWithQuantityGreaterThan(minimumQuantity);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.OK);
    }

    @GetMapping(value = PATH_TOTAL_SALES)
    public ResponseEntity<Double> getTotalSalesInDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        Double totalSales = orderServiceImpl.getTotalSalesInDateRange(startDate, endDate);
        return new ResponseEntity<>(totalSales, HttpStatus.OK);
    }
}
