package it.antoniomallia.mediatoolkit.war_game.battleArea;

import it.antoniomallia.mediatoolkit.war_game.armies.Bomb;
import it.antoniomallia.mediatoolkit.war_game.armies.Soldier;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.Getter;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;

public class Row {

	@Getter
	private Cell[] cells;

	protected Row(Integer size) {
		cells = Stream.generate(() -> {
			return new Cell();
		}).limit(size).toArray(s -> new Cell[s]);
	}

	protected Cell getRandomCell() {
		SecureRandom random = new SecureRandom();
		Integer randomPosition = random.nextInt(cells.length);
		return cells[randomPosition];
	}

	protected void combact(Optional<Row> above, Optional<Row> below) {
		int i = 0;
		for (Cell cell : cells) {
			MutableTriple<Integer, Integer, Boolean> enemies_companions_bomb = MutableTriple
					.of(0, 0, false);
			if (cell.isFull() && (cell.getUnit() instanceof Soldier)) {
				if (i > 0) {
					if (above.isPresent()) {
						countEnCom(cell, above.get().getCells()[(i - 1)],
								enemies_companions_bomb);
					}

					countEnCom(cell, cells[(i - 1)], enemies_companions_bomb);
					if (below.isPresent()) {
						countEnCom(cell, below.get().getCells()[(i - 1)],
								enemies_companions_bomb);
					}
				}
				if (above.isPresent()) {

					countEnCom(cell, above.get().getCells()[i],
							enemies_companions_bomb);
				}
				if (below.isPresent()) {
					countEnCom(cell, below.get().getCells()[i],
							enemies_companions_bomb);
				}

				if (i < this.cells.length - 1) {
					if (above.isPresent()) {
						countEnCom(cell, above.get().getCells()[(i + 1)],
								enemies_companions_bomb);
					}
					countEnCom(cell, cells[(i + 1)], enemies_companions_bomb);

					if (below.isPresent()) {
						countEnCom(cell, below.get().getCells()[(i + 1)],
								enemies_companions_bomb);
					}
				}
				if (enemies_companions_bomb.getRight()) {
					((Soldier) cell.getUnit()).setAlive(false);
					continue;
				}
				if ((enemies_companions_bomb.getLeft() == 0 && enemies_companions_bomb
						.getMiddle() == 0)
						|| (enemies_companions_bomb.getLeft() > enemies_companions_bomb
								.getMiddle())) {
					((Soldier) cell.getUnit()).setAlive(false);

				}
			}
			i++;
		}
	}

	public void countEnCom(Cell cell1, Cell cell2,
			MutableTriple<Integer, Integer, Boolean> enemies_companions_bomb) {
		if (cell1.isFull() && cell2.isFull()) {
			if (cell2.getUnit() instanceof Soldier) {
				if (((Soldier) cell1.getUnit()).getArmy() == ((Soldier) cell2
						.getUnit()).getArmy()) {
					enemies_companions_bomb.setMiddle(enemies_companions_bomb
							.getMiddle() + 1);
				} else {
					enemies_companions_bomb.setLeft(enemies_companions_bomb
							.getLeft() + 1);
				}
			} else if (cell2.getUnit() instanceof Bomb) {
				enemies_companions_bomb.right = true;
			}
		}
	}

	public MutablePair<Integer, Integer> count() {
		MutablePair<Integer, Integer> black_red = MutablePair.of(0, 0);
		for (Cell cell : cells) {
			if (cell.isFull() && (cell.getUnit() instanceof Soldier)) {
				Soldier soldier = (Soldier) cell.getUnit();
				if (soldier.getAlive()) {
					if (soldier.isBlack()) {
						black_red.left++;
					} else if (((Soldier) cell.getUnit()).isRed()) {
						black_red.right++;
					}
				}
			}
		}
		return black_red;
	}
}
