package com.juanjose.music.main;

import com.juanjose.music.models.Singer;
import com.juanjose.music.repository.SingerRepository;
import com.juanjose.music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class ConsoleMenu {
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private SingerRepository singerRepository;
    private SongRepository songRepository;

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
            switch (option) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    findSinger();
                    break;

                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    public void findSinger() {
        System.out.println("Enter the name of the singer");
        String name = scanner.nextLine().trim().toLowerCase();
        Optional<Singer> singer = singerRepository.findByNameIgnoreCase(name);
        if(singer.isPresent()){
            singer.get();
        }else {
            System.out.println("Singer not found");
        }
    }
}
