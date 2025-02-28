package ProductBackendTest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ProductBackendTest.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByDate(LocalDateTime date);
    List<OrderEntity> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<OrderEntity> findByProductId(Long productId);
    List<OrderEntity> findByQuantityGreaterThan(Integer minimumQuantity);
    
    @Query("SELECT SUM(p.total) FROM OrderEntity p WHERE p.date BETWEEN :startDate AND :endDate")
    Double getTotalSalesInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
