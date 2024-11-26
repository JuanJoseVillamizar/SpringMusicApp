package com.juanjose.music;

import com.juanjose.music.main.ConsoleMenu;
import com.juanjose.music.repository.SingerRepository;
import com.juanjose.music.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicApplication implements CommandLineRunner {

	@Autowired
	private SongRepository songRepository;
	@Autowired
	private SingerRepository singerRepository;
	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsoleMenu main = new ConsoleMenu(songRepository,singerRepository);
		main.start();
	}
}
