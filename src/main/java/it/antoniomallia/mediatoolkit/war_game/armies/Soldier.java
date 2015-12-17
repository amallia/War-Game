package it.antoniomallia.mediatoolkit.war_game.armies;

import lombok.Getter;
import lombok.Setter;

public class Soldier implements IUnit {
	@Getter
	@Setter
	private Boolean alive;

	@Getter
	private Army army;
	
	public Soldier(Army army) {
		alive=true;
		this.army = army;
	}
	
	public Boolean isRed() {
		return army.equals(Army.RED);

	}
	public Boolean isBlack() {
		return army.equals(Army.BLACK);
	}

	
	
}
