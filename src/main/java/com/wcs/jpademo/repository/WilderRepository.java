package com.wcs.jpademo.repository;

import com.wcs.jpademo.entity.Wilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WilderRepository extends JpaRepository<Wilder, Integer> {
    Wilder findByEmail(String email);
    List<Wilder> findByNameContaining(String name);
}
