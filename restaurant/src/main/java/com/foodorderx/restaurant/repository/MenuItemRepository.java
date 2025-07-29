package com.foodorderx.restaurant.repository;

import com.foodorderx.restaurant.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id = :restaurantId")
    List<MenuItem> findMenuItemsByRestaurantId(@Param("restaurantId") Long restaurantId);
}
