package it.antoniomallia.mediatoolkit.war_game.battleArea;

import it.antoniomallia.mediatoolkit.war_game.armies.IUnit;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Cell {

	@Getter
	@Setter(AccessLevel.PROTECTED)
	private IUnit unit;

	public Boolean isFull() {
		return Optional.ofNullable(unit).isPresent();
	}

	public Boolean isEmpty() {
		return !isFull();
	}

}
