package com.juanjose.music.repository;

import com.juanjose.music.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "Select s FROM Song s")
    List<Song> findAll();

    @Query(value= "SELECT c FROM Singer s JOIN s.songs c WHERE s.name ILIKE %:name%")
    List<Song> findSongsBySinger (String name);
}
