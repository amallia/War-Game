package it.antoniomallia.mediatoolkit.war_game.battleArea;

import org.junit.Test;

import it.antoniomallia.mediatoolkit.war_game.armies.Soldier;
import static org.junit.Assert.assertEquals;

public class BattleAreaTest {
@Test
	public void testFillWithSoldiers() {
		BattleArea battleArea = new BattleArea(5);
		battleArea.fillWithSoldiers(5);
		int red = 0, black = 0;
		for (Row row : battleArea.map.getMatrix()) {
			for (Cell cell : row.getCells()) {
				if (cell.isFull() && cell.getUnit() instanceof Soldier) {
					if (((Soldier) cell.getUnit()).isBlack()) {
						black++;
					} else {
						red++;
					}
				}
			}
		}
		assertEquals(red,black);
		assertEquals(red, 5);
	}
}
