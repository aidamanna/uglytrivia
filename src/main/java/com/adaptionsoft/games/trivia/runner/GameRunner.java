//
//package com.adaptionsoft.games.trivia.runner;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//import com.adaptionsoft.games.uglytrivia.domain.Game;
//import com.adaptionsoft.games.uglytrivia.domain.questions.PopQuestions;
//import com.adaptionsoft.games.uglytrivia.infrastructure.Console;
//
//
//public class GameRunner {
//
//	private static boolean notAWinner;
//	private static List<String> themes = Arrays.asList("PopQuestions", "ScienceQuestions", "SportsQuestions", "RockQuestions");
//
//
//	public static void main(String[] args) {
//		List<PopQuestions> questions = generateGameQuestions();
//
//		Game aGame = new Game(new Console(), questions);
//
//		aGame.add("Chet");
//		aGame.add("Pat");
//		aGame.add("Sue");
//
//		Random rand = new Random();
//
//		do {
//
//			aGame.roll(rand.nextInt(5) + 1);
//
//			if (rand.nextInt(9) == 7) {
//				notAWinner = aGame.wrongAnswer();
//			} else {
//				notAWinner = aGame.correctAnswerIfNotInPenaltyBox();
//			}
//
//
//
//		} while (notAWinner);
//
//	}
//
//	private static List<PopQuestions> generateGameQuestions() {
//		List<PopQuestions> questions = Arrays.asList();
//		for(String theme : themes) {
//			questions.add(new PopQuestions(theme));
//		}
//		return questions;
//	}
//}
