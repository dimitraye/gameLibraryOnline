package com.gameLibraryOnline.rest;

import com.gameLibraryOnline.rest.entity.*;
import com.gameLibraryOnline.rest.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.*;

@SpringBootApplication
public class GameLibraryOnlineApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GameLibraryOnlineApplication.class, args);

		/*UserService userService = context.getBean(UserService.class);
		GamePublicService gamePublicService = context.getBean(GamePublicService.class);
		UserGameService userGameService = context.getBean(UserGameService.class);
		CommentaryService commentaryService = context.getBean(CommentaryService.class);
		ProgressionService progressionService = context.getBean(ProgressionService.class);
		SuccessService successService = context.getBean(SuccessService.class);

		GameLibraryOnlineApplication app = new GameLibraryOnlineApplication();

		List<User> users = app.generate5Users();
		users.forEach(userService::save);

		System.out.println(users.size() + " utilisateurs ont été ajoutés.");

		List<UserGame> userGames = app.generate5UserGames(users, gamePublicService);
		userGames.forEach(userGameService::save);
		System.out.println(userGames.size() + " jeux utilisateur ajoutés.");

		List<Commentary> comments = app.generateCommentsForUserGames(userGames);
		comments.forEach(commentaryService::save);
		System.out.println(comments.size() + " commentaires ajoutés.");

		List<Progression> progressions = app.generateProgressionsForUserGames(userGames);
		progressions.forEach(progressionService::save);
		System.out.println(progressions.size() + " progressions ajoutées.");

		List<Success> successes = app.generateSuccessesForUserGames(userGames);
		successes.forEach(successService::save);
		System.out.println(successes.size() + " succès ajoutés.");*/
	}

	public List<User> generate5Users() {
		List<User> users = new ArrayList<>();
		users.add(createUser("TheBatman", "Bruce", "Wayne", "bruce.wayne@example.com", true, Role.ADMIN));
		users.add(createUser("IronMan", "Tony", "Stark", "tony.stark@example.com", true, Role.CLIENT));
		users.add(createUser("CaptainMarvel", "Carol", "Danvers", "carol.danvers@example.com", false, Role.CLIENT));
		users.add(createUser("SpiderMan", "Peter", "Parker", "peter.parker@example.com", true, Role.CLIENT));
		users.add(createUser("WonderWoman", "Diana", "Prince", "diana.prince@example.com", false, Role.CLIENT));
		return users;
	}

	private User createUser(String username, String firstname, String lastname, String email, boolean gender, Role role) {
		User user = new User();
		user.setUsername(username);
		user.setPassword("defaultPassword123");
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setBirthDate("1990-01-01");
		user.setGender(gender);
		user.setAdress("Adresse de test");
		user.setPostCode("75000");
		user.setCity("Testville");
		user.setCountry("Testland");
		user.setPhoneNumber("+1234567890");
		user.setEmail(email);
		user.setRegistrationDate("2024-06-01");
		user.setRole(role);
		return user;
	}

	public List<UserGame> generate5UserGames(List<User> users, GamePublicService gamePublicService) {
		List<UserGame> userGames = new ArrayList<>();

		userGames.add(createUserGame(users.get(0), "The Legend of Zelda", List.of(Platform.NINTENDO_SWITCH), List.of(VideoGameGenre.JRPG), "zelda.jpg", 120, GameStatus.COMPLETED, gamePublicService));
		userGames.add(createUserGame(users.get(1), "Halo Infinite", List.of(Platform.MICROSOFT_XBOX_SERIES_X), List.of(VideoGameGenre.TPS), "halo.jpg", 45, GameStatus.IN_PROGRESS, gamePublicService));
		userGames.add(createUserGame(users.get(2), "Final Fantasy VII", List.of(Platform.SONY_PLAYSTATION_1), List.of(VideoGameGenre.JRPG), "ff7.jpg", 80, GameStatus.COMPLETED, gamePublicService));
		userGames.add(createUserGame(users.get(3), "Minecraft", List.of(Platform.WINDOWS), List.of(VideoGameGenre.SURVIVAL), "minecraft.jpg", 200, GameStatus.IN_PROGRESS, gamePublicService));
		userGames.add(createUserGame(users.get(4), "The Witcher 3", List.of(Platform.WINDOWS), List.of(VideoGameGenre.ACTION_ADVENTURE), "witcher3.jpg", 150, GameStatus.COMPLETED, gamePublicService));

		return userGames;
	}

	private UserGame createUserGame(User user, String title, List<Platform> platforms, List<VideoGameGenre> genres, String picture, int playHours, GameStatus state, GamePublicService gamePublicService) {
		GamePublic game = new GamePublic();
		game.setTitle(title);
		game.setPlatforms(platforms);
		game.setGenres(genres);
		game.setPicture(picture);
		GamePublic publicGame = gamePublicService.findOrCreate(game);

		UserGame userGame = new UserGame();
		userGame.setUser(user);
		userGame.setGamePublic(publicGame);
		userGame.setDatePurchase(new Date());
		userGame.setPlayHours(playHours);
		userGame.setState(state);
		return userGame;
	}

	public List<Commentary> generateCommentsForUserGames(List<UserGame> userGames) {
		List<Commentary> comments = new ArrayList<>();
		for (UserGame userGame : userGames) {
			Commentary comment = new Commentary();
			comment.setCommentary("Ce jeu est incroyable !");
			comment.setCreationDate(new Date());
			comment.setUser(userGame.getUser());
			comment.setUserGame(userGame);
			comments.add(comment);
		}
		return comments;
	}

	public List<Progression> generateProgressionsForUserGames(List<UserGame> userGames) {
		List<Progression> progressions = new ArrayList<>();
		int i = 0;
		for (UserGame userGame : userGames) {
			Progression progression = new Progression();
			progression.setDetailsProgression("Exploration du niveau " + (i + 1));
			progression.setPercentageCompletion((i + 1) * 20);
			progression.setUser(userGame.getUser());
			progression.setUserGame(userGame);
			progressions.add(progression);
			i++;
		}
		return progressions;
	}

	public List<Success> generateSuccessesForUserGames(List<UserGame> userGames) {
		List<Success> successes = new ArrayList<>();
		int i = 0;
		for (UserGame userGame : userGames) {
			Success success = new Success();
			success.setDescription("Succès " + (i + 1) + ": terminer le niveau " + (i + 1));
			success.setOwned(false);
			success.setUserGame(userGame);
			successes.add(success);
			i++;
		}
		return successes;
	}
}
