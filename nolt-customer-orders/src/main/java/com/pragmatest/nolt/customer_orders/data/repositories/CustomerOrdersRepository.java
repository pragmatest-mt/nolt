package com.pragmatest.nolt.customer_orders.data.repositories;

import com.pragmatest.nolt.customer_orders.data.entities.CustomerOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrdersRepository extends JpaRepository<CustomerOrderEntity, String> {

}
