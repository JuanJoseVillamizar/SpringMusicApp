package com.juanjose.music.repository;

import com.juanjose.music.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository <Song, Long> {

}
