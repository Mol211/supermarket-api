package com.moldev.supermarket.repository;

import com.moldev.supermarket.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("""
            SELECT sd.product.id
                        FROM SaleDetail sd
                                    GROUP BY sd.product.id
                                                ORDER BY SUM(sd.productQuantity) DESC
            """)
    List<Long> findTopProductsIds();
}
