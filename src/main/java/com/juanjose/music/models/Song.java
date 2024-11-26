package com.juanjose.music.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private int releaseYear;
    @ManyToOne()
    private Singer singer;

    public Song(){}

    public Song(String title, int releaseYear, Singer singer) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.singer = singer;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Song: " +
                "title:'" + title + '\'' +
                ", releaseYear:" + releaseYear +
                ", singer:" + (singer != null ? singer.getName() : "No singer assigned");
    }
}
