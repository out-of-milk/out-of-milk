package com.outofmilk.outofmilk.repositories;

import com.outofmilk.outofmilk.models.Ingredient;
import com.outofmilk.outofmilk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u where u.username = ?1")
    User findByUsername(String username);

    @Query("from User u where u.email = ?1")
    User findByEmail(String email);

    @Modifying
    @Query(value = "delete from pantry_items where user_id = :pantryItemUserId and ingredient_id = :pantryItemIngredientId", nativeQuery = true)
    void deletePantryItemIngredientById(@Param("pantryItemUserId") Long pantryItemUserId,
                                        @Param("pantryItemIngredientId") Long pantryItemIngredientId);

    @Modifying
    @Query(value = "delete from pantry_items where user_id = :pantryItemUserId", nativeQuery = true)
    void deletePantryListById(@Param("pantryItemUserId") Long pantryItemUserId);

    @Modifying
    @Query(value = "delete from grocery_items where user_id = :groceryListUserId and ingredient_id = :groceryListIngredientId", nativeQuery = true)
    void deleteGroceryListIngredientById(@Param("groceryListUserId") Long groceryListUserId,
                                         @Param("groceryListIngredientId") Long groceryListIngredientId);

    @Modifying
    @Query(value = "delete from grocery_items where user_id = :groceryListUserId", nativeQuery = true)
    void deleteGroceryListById(@Param("groceryListUserId") Long groceryListUserId);


}
