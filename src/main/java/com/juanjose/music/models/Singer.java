package com.juanjose.music.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String country;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songs = new ArrayList<>();

    public Singer(){}

    public Singer(String name, String country, Genre genre) {
        this.name = name;
        this.country = country;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        songs.forEach(e -> e.setSinger(this));
        this.songs = songs;
    }

    @Override
    public String toString() {
        String songTitles = songs.stream()
                .map(Song::getTitle)
                .limit(3)
                .collect(Collectors.joining(", "));
        return "Singer: " +
                "name:'" + name + '\'' +
                ", country:'" + country + '\'' +
                ", genre:" + genre +
                ", songs:[" + songTitles + "]";
    }
}
