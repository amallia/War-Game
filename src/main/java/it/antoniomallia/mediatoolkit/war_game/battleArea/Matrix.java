package it.antoniomallia.mediatoolkit.war_game.battleArea;

import java.security.SecureRandom;
import java.util.stream.Stream;

import lombok.Getter;

public class Matrix {

	@Getter
	private Row[] matrix;

	public Matrix(Integer size) {
		matrix = Stream.generate(() -> {
			return new Row(size);
		}).limit(size).toArray(s -> new Row[s]);
	}

	public Row getRandomRow() {
		SecureRandom random = new SecureRandom();
		Integer randomPosition = random.nextInt(matrix.length);
		return matrix[randomPosition];
	}

}
