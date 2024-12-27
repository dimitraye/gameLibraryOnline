package com.gameLibraryOnline.rest;

import com.gameLibraryOnline.rest.entity.*;
import com.gameLibraryOnline.rest.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GameLibraryOnlineApplication {
	UserService userService;

	/**public static void main(String[] args) {
		SpringApplication.run(GameLibraryOnlineApplication.class, args);

	}**/

	public static void main(String[] args) {
		// Démarrage de l'application Spring et récupération du contexte
		ApplicationContext context = SpringApplication.run(GameLibraryOnlineApplication.class, args);

		// Récupération du service UserService à partir du contexte Spring
		UserService userService = context.getBean(UserService.class);
		GameService gameService = context.getBean(GameService.class);
		CommentaryService commentaryService = context.getBean(CommentaryService.class);
		ProgressionService progressionService = context.getBean(ProgressionService.class);
		SuccessService successService = context.getBean(SuccessService.class);
		// Création d'une instance de GameLibraryOnlineApplication pour accéder à la méthode generate5Users
		GameLibraryOnlineApplication app = context.getBean(GameLibraryOnlineApplication.class);

		// Génération des utilisateurs
		List<User> users = app.generate5Users();

		// Sauvegarde des utilisateurs dans la base de données
		users.forEach(userService::save);

		System.out.println(users.size() + " utilisateurs ont été ajoutés dans la base de données.");

		// Génération des jeux
		List<Game> games = app.generate5Games(users);

		// Sauvegarde des jeux dans la base de données
		games.forEach(gameService::save);

		System.out.println(games.size() + " jeux vidéo ont été ajoutés dans la base de données.");
		// Génération des commentaires pour les jeux
		List<Commentary> comments = app.generateCommentsForGames(games, users);

		// Sauvegarde des commentaires dans la base de données
		comments.forEach(commentaryService::save);
		System.out.println(comments.size() + " commentaires ont été ajoutés dans la base de données.");

		// Génération des progressions pour les jeux
		List<Progression> progressions = app.generateProgressionsForGames(games, users);

		// Sauvegarde des progressions dans la base de données
		progressions.forEach(progressionService::save);
		System.out.println(progressions.size() + " progressions ont été ajoutées dans la base de données.");

		// Génération des succès pour les jeux
		List<Success> successes = app.generateSuccessesForGames(games);

		// Sauvegarde des succès dans la base de données
		successes.forEach(successService::save);
		System.out.println(successes.size() + " succès ont été ajoutés dans la base de données.");

	}

	//--------------------GENERATE 5 USERS---------------//
	public List<User> generate5Users()  {
		//Liste
		List<User> users = new ArrayList<>();

		User user1 = new User();
		user1.setUsername("TheBatman");
		user1.setPassword("securePass123!");
		user1.setFirstname("Bruce");
		user1.setLastname("Wayne");
		user1.setBirthDate("1985-02-19");
		user1.setGender(true);
		user1.setAdress("Wayne Manor");
		user1.setPostCode("75001");
		user1.setCity("Gotham");
		user1.setCountry("USA");
		user1.setPhoneNumber("+1123456789");
		user1.setEmail("bruce.wayne@example.com");
		user1.setRegistrationDate("2024-01-01");
		user1.setRole(Role.ADMIN);
		users.add(user1);

		User user2 = new User();
		user2.setUsername("IronMan");
		user2.setPassword("StarkSecure2024!");
		user2.setFirstname("Tony");
		user2.setLastname("Stark");
		user2.setBirthDate("1975-05-29");
		user2.setGender(true);
		user2.setAdress("Avengers Tower");
		user2.setPostCode("90265");
		user2.setCity("New York");
		user2.setCountry("USA");
		user2.setPhoneNumber("+14085551234");
		user2.setEmail("tony.stark@example.com");
		user2.setRegistrationDate("2024-01-10");
		user2.setRole(Role.CLIENT);
		users.add(user2);

		User user3 = new User();
		user3.setUsername("CaptainMarvel");
		user3.setPassword("secureMarvel2024!");
		user3.setFirstname("Carol");
		user3.setLastname("Danvers");
		user3.setBirthDate("1980-06-01");
		user3.setGender(false);
		user3.setAdress("Star Base");
		user3.setPostCode("75002");
		user3.setCity("Los Angeles");
		user3.setCountry("USA");
		user3.setPhoneNumber("+1123456790");
		user3.setEmail("carol.danvers@example.com");
		user3.setRegistrationDate("2024-01-15");
		user3.setRole(Role.CLIENT);
		users.add(user3);

		User user4 = new User();
		user4.setUsername("SpiderMan");
		user4.setPassword("webSecure2024!");
		user4.setFirstname("Peter");
		user4.setLastname("Parker");
		user4.setBirthDate("2001-08-10");
		user4.setGender(true);
		user4.setAdress("Queens");
		user4.setPostCode("75003");
		user4.setCity("New York");
		user4.setCountry("USA");
		user4.setPhoneNumber("+1123456791");
		user4.setEmail("peter.parker@example.com");
		user4.setRegistrationDate("2024-02-01");
		user4.setRole(Role.CLIENT);
		users.add(user4);

		User user5 = new User();
		user5.setUsername("WonderWoman");
		user5.setPassword("amazonSecure2024!");
		user5.setFirstname("Diana");
		user5.setLastname("Prince");
		user5.setBirthDate("1988-03-08");
		user5.setGender(false);
		user5.setAdress("Themyscira");
		user5.setPostCode("75004");
		user5.setCity("Athens");
		user5.setCountry("Greece");
		user5.setPhoneNumber("+3023456789");
		user5.setEmail("diana.prince@example.com");
		user5.setRegistrationDate("2024-03-01");
		user5.setRole(Role.CLIENT);
		users.add(user5);

		return users;
	}


	//--------------------GENERATE 5 GAMES---------------//
	public List<Game> generate5Games(List<User> users) {
		// Liste des jeux
		List<Game> games = new ArrayList<>();

		Game game1 = new Game();
		game1.setTitle("The Legend of Zelda");
		game1.setPlatform(Platform.NINTENDO_SWITCH);
		game1.setVideoGameGenre(VideoGameGenre.JRPG);
		game1.setDatePurchase(new Date());
		game1.setPlayHours(120);
		game1.setState(GameStatus.COMPLETED);
		game1.setPicture("zelda.jpg");
		game1.setUser(users.get(0)); // Assigné à "TheBatman"
		games.add(game1);

		Game game2 = new Game();
		game2.setTitle("Halo Infinite");
		game2.setPlatform(Platform.MICROSOFT_XBOX_SERIES_X);
		game2.setVideoGameGenre(VideoGameGenre.TPS);
		game2.setDatePurchase(new Date());
		game2.setPlayHours(45);
		game2.setState(GameStatus.IN_PROGRESS);
		game2.setPicture("halo.jpg");
		game2.setUser(users.get(1)); // Assigné à "IronMan"
		games.add(game2);

		Game game3 = new Game();
		game3.setTitle("Final Fantasy VII");
		game3.setPlatform(Platform.SONY_PLAYSTATION_1);
		game3.setVideoGameGenre(VideoGameGenre.JRPG);
		game3.setDatePurchase(new Date());
		game3.setPlayHours(80);
		game3.setState(GameStatus.COMPLETED);
		game3.setPicture("ff7.jpg");
		game3.setUser(users.get(2)); // Assigné à "CaptainMarvel"
		games.add(game3);

		Game game4 = new Game();
		game4.setTitle("Minecraft");
		game4.setPlatform(Platform.WINDOWS);
		game4.setVideoGameGenre(VideoGameGenre.SURVIVAL);
		game4.setDatePurchase(new Date());
		game4.setPlayHours(200);
		game4.setState(GameStatus.IN_PROGRESS);
		game4.setPicture("minecraft.jpg");
		game4.setUser(users.get(3)); // Assigné à "SpiderMan"
		games.add(game4);

		Game game5 = new Game();
		game5.setTitle("The Witcher 3");
		game5.setPlatform(Platform.WINDOWS);
		game5.setVideoGameGenre(VideoGameGenre.ACTION_ADVENTURE);
		game5.setDatePurchase(new Date());
		game5.setPlayHours(150);
		game5.setState(GameStatus.COMPLETED);
		game5.setPicture("witcher3.jpg");
		game5.setUser(users.get(4)); // Assigné à "WonderWoman"
		games.add(game5);

		return games;
	}

	//---------------------GENERATE COMMENTS------------ //
	public List<Commentary> generateCommentsForGames(List<Game> games, List<User> users) {
		List<Commentary> comments = new ArrayList<>();

		for (int i = 0; i < games.size(); i++) {
			Commentary comment = new Commentary();
			comment.setCommentary("This game is amazing! Highly recommended.");
			comment.setCreationDate(new Date());
			comment.setGame(games.get(i)); // Associe le commentaire à un jeu
			comment.setUser(users.get(i)); // Associe le commentaire à un utilisateur
			comments.add(comment);
		}

		return comments;
	}

	//-----------------GENERATE PROGRESSION--------------//
	public List<Progression> generateProgressionsForGames(List<Game> games, List<User> users) {
		List<Progression> progressions = new ArrayList<>();

		for (int i = 0; i < games.size(); i++) {
			Progression progression = new Progression();
			progression.setDetailsProgression("Currently exploring level 3. Great storyline!");
			progression.setPercentageCompletion((i + 1) * 20); // Ex : 20%, 40%, etc.
			progression.setGame(games.get(i)); // Associe la progression à un jeu
			progression.setUser(users.get(i)); // Associe la progression à un utilisateur
			progressions.add(progression);
		}

		return progressions;
	}

	//---------------------GENERATE SUCCESS--------------//
	public List<Success> generateSuccessesForGames(List<Game> games) {
		List<Success> successes = new ArrayList<>();

		for (int i = 0; i < games.size(); i++) {
			Success success = new Success();
			success.setDescription("Achievement " + (i + 1) + ": Complete level " + (i + 1) + " without losing a life.");
			success.setOwned(false); // Par défaut, le succès n'est pas débloqué
			success.setGame(games.get(i)); // Associe le succès à un jeu
			successes.add(success);
		}

		return successes;
	}
}
