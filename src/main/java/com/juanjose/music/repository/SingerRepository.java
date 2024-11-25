package com.juanjose.music.repository;

import com.juanjose.music.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SingerRepository extends JpaRepository <Singer, Long> {
    Optional<Singer> findByNameIgnoreCase(String name);
}
