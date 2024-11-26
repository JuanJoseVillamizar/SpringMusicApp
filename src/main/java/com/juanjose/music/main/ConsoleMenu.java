package com.juanjose.music.main;

import com.juanjose.music.models.Genre;
import com.juanjose.music.models.Singer;
import com.juanjose.music.models.Song;
import com.juanjose.music.repository.SingerRepository;
import com.juanjose.music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleMenu {
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private SingerRepository singerRepository;
    Optional<Singer> foundSinger;
    List<Song> songs;

    public ConsoleMenu(SongRepository songRepository, SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
        this.songRepository = songRepository;
    }

    public void start() {
        var option = -1;
        while (option != 9) {
            String menu = """
                    *** Music Application ***
                        1 - Register singer
                        2 - Register song
                        3 - Music list
                        4 - Search songs by singer
                        5 - Search singer data
                    
                        9 - Exit...
                    """;
            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    registerSinger();
                    break;
                case 2:
                    registerSong();
                    break;
                case 3:
                    musicList();
                    break;
                case 4:
                    findSongBySinger();
                    break;
                case 5:
                    findSinger();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void registerSinger() {

        var registrarNuevo = "S";
        while (registrarNuevo.equalsIgnoreCase("s")){
            System.out.println("Enter data of the singer");
            System.out.println("Name: ");
            String name = scanner.nextLine();

            System.out.println("Country: ");
            String country = scanner.nextLine();

            System.out.println("Select a genre: ");
            Genre[] genres = Genre.values();
            for (int i = 0; i < genres.length; i++) {
                System.out.println((i + 1) + "." + genres[i]);
            }
            int genreIndex = scanner.nextInt() - 1;
            scanner.nextLine();
            if (genreIndex < 0 || genreIndex > genres.length) {
                System.out.println("Genre not found");
                return;
            }

            Genre genre = genres[genreIndex];
            Singer singer = new Singer();
            singer.setName(name);
            singer.setCountry(country);
            singer.setGenre(genre);
            singerRepository.save(singer);
            System.out.println("Success register");
            System.out.println("Do you want register another singer? (Y/N)");
            registrarNuevo = scanner.nextLine();
        }



    }

    public void registerSong() {
        System.out.println("Enter data of the song");
        System.out.println("Name: ");
        String title = scanner.nextLine();
        System.out.println("Release year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Autor: ");
        String autor = scanner.nextLine();
        foundSinger = singerRepository.findByNameIgnoreCase(autor);
        if (foundSinger.isPresent()) {
            Song song = new Song();
            song.setSinger(foundSinger.get());
            song.setReleaseYear(releaseYear);
            song.setTitle(title);
            songRepository.save(song);
            System.out.println("Song registered success");
        } else {
            System.out.println("Singer not found");
        }


    }
    public void musicList(){
        songs=songRepository.findAll();
        songs.forEach(s -> System.out.println("title: "+s.getTitle() + ", Autor: " + s.getSinger().getName()));
    }

    public void findSongBySinger(){
        findSinger();
        if (foundSinger.isPresent()){
            Singer singer = foundSinger.get();
            songs = songRepository.findSongsBySinger(singer.getName());
            songs.forEach(System.out::println);
        }
    }


    public void findSinger() {
        System.out.println("Enter the name of the singer");
        String name = scanner.nextLine().trim().toLowerCase();
        foundSinger = singerRepository.findByNameIgnoreCase(name);
        if (foundSinger.isPresent()) {
            System.out.println(foundSinger.get());
        } else {
            System.out.println("Singer not found");
        }
    }
}
