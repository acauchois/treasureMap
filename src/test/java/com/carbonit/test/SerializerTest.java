package com.carbonit.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SerializerTest {

	Serializer serializer;
	Square[][] squares;

	@BeforeEach
	void initSerializer() {
		serializer = new Serializer();
	}

	@Test
	void testEmptyMap() throws TreasureMapException {
		squares = new Square[1][1];
		squares[0][0] = new Square("P", 0, 0);

		serializer.readFile("C:\\Users\\cauch\\eclipse-workspace\\treasuremap\\src\\main\\resources\\emptyMap.txt");

		assertThat(serializer.getTreasureMap().getSquares()).hasSameDimensionsAs(squares);
	}

	@Test
	void testMountain() throws TreasureMapException {
		squares = new Square[2][2];
		squares[1][0] = new Square("M", 1, 0);

		serializer.readFile("C:\\Users\\cauch\\eclipse-workspace\\treasuremap\\src\\main\\resources\\mountain.txt");

		assertThat(serializer.getTreasureMap().getSquares()[1][0].getType()).isEqualTo(squares[1][0].getType());
	}

}
