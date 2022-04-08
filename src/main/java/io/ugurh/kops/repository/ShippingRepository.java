package io.ugurh.kops.repository;

import io.ugurh.kops.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    long countByCity(String city);
}