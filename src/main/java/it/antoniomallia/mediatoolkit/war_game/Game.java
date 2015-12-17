package it.antoniomallia.mediatoolkit.war_game;

import it.antoniomallia.mediatoolkit.war_game.battleArea.BattleArea;

import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Game {
	
	private Integer n;
	private static final Integer soldierBomb = 4;
	
	
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter number of soldiers per army: ");
		Integer n = reader.nextInt();
		reader.close();
		if (n>0) {
			
		Game game = new Game(n);
		game.run();
		} else {
			System.out.println("We need at least 1 soldier per army to make a war");	
		}
	}

	private void run(){
		System.out.println("The battle started...");
		Integer mapSize = (int) Math.sqrt(n * 5);
		BattleArea battleArea = new BattleArea(mapSize);
		System.out.println(String.format("Bombs placed: %d", 2*(n/soldierBomb)));
		battleArea.placeBombs(2*(n/soldierBomb));
		System.out.println("Soldiers placed");
		battleArea.fillWithSoldiers(n);
		System.out.println("Battle area:");
		System.out.println(battleArea);
		System.out.println("Combat started...");
		battleArea.combat();
		System.out.println(battleArea.result());
		System.out.println("----------------------------------------------------------------");
		System.out.println("'There never was a good war or a bad peace.' - Benjamin Franklin");
	}


}
