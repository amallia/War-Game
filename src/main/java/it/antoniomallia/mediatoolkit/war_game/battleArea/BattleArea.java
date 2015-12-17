package it.antoniomallia.mediatoolkit.war_game.battleArea;

import it.antoniomallia.mediatoolkit.war_game.armies.Army;
import it.antoniomallia.mediatoolkit.war_game.armies.Bomb;
import it.antoniomallia.mediatoolkit.war_game.armies.Soldier;

import java.util.Optional;

import lombok.Getter;

import org.apache.commons.lang3.tuple.MutablePair;

public class BattleArea {

	@Getter
	Matrix map;

	public BattleArea(Integer size) {
		map = new Matrix(size);
	}

	public void placeBombs(Integer bombs) {
		Integer placedBombs = 0;
		while (placedBombs < bombs) {
			Cell randomCell = map.getRandomRow().getRandomCell();
			if (randomCell.isEmpty()) {
				randomCell.setUnit(new Bomb());
				placedBombs++;
			}
		}
	}

	public void fillWithSoldiers(Integer soldiers) {
		for (Army army : Army.values()) {
			Integer placedSoldiers = 0;
			while (placedSoldiers < soldiers) {
				Cell randomCell = map.getRandomRow().getRandomCell();
				if (randomCell.isEmpty()) {
					randomCell.setUnit(new Soldier(army));
					placedSoldiers++;
				}
			}
		}
	}

	public void combat() {
		for (int i = 0; i < map.getMatrix().length; i++) {
			if (i == 0) {
				map.getMatrix()[i].combact(Optional.empty(),
						Optional.ofNullable(map.getMatrix()[(i + 1)]));

			} else if (i == map.getMatrix().length - 1) {
				map.getMatrix()[i].combact(
						Optional.ofNullable(map.getMatrix()[(i - 1)]),
						Optional.empty());

			} else {
				map.getMatrix()[i].combact(
						Optional.ofNullable(map.getMatrix()[(i - 1)]),
						Optional.ofNullable(map.getMatrix()[(i + 1)]));

			}
		}
	}

	public String result() {
		MutablePair<Integer, Integer> black_red = MutablePair.of(0, 0);
		for (Row row : map.getMatrix()) {
			black_red.left += row.count().left;
			black_red.right += row.count().right;
		}
		if (black_red.left == 0 && black_red.right == 0) {
			return "Draw. Everyone is dead.";
		} else if (black_red.left > black_red.right) {
			return String
					.format("Black won. Black soldiers alive: %d, Red soldiers alive: %d",
							black_red.left, black_red.right);

		} else if (black_red.left < black_red.right) {
			return String
					.format("Red won. Black soldiers alive: %d, Red soldiers alive: %d",
							black_red.left, black_red.right);
		} else {
			return String.format(
					"Draw. Black soldiers alive: %d, Red soldiers alive: %d",
					black_red.left, black_red.right);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.getMatrix().length; i++) {
			for (int j = 0; j < map.getMatrix()[i].getCells().length; j++) {
				Cell c = map.getMatrix()[i].getCells()[j];
				if (c.isEmpty()) {
					sb.append(" 0 ");
				} else if (c.isFull()) {
					if (c.getUnit() instanceof Soldier) {
						if (((Soldier) c.getUnit()).getAlive()) {
							if (((Soldier) c.getUnit()).isBlack()) {
								sb.append(" B ");
							} else if (((Soldier) c.getUnit()).isRed()) {
								sb.append(" R ");
							}
						} else {
							sb.append(" X ");
						}
					} else if (c.getUnit() instanceof Bomb) {
						sb.append(" Q ");

					}
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		sb.append("Legend: R -> Red Soldier, B -> Black Soldier, Q -> Bomb");
		return sb.toString();
	}
}
