package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.entity.CheckoutOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends CrudRepository<CheckoutOrder, Integer> {
    @Query (value = "SELECT SUM (totalHargalCi) FROM CheckoutOrder", nativeQuery = false)
    Long sumQuantities();
}
