package com.carbonit.test;

public class EntryPoint {

	static Serializer serializer = new Serializer();

	public static void begin() throws TreasureMapException {

		serializer.readFile("C:\\Users\\cauch\\eclipse-workspace\\treasuremap\\src\\main\\resources\\map1.txt");

		int numberMaxOfMovements = 0;
		for (Adventurer adventurer : serializer.getAdventurers()) {
			if (numberMaxOfMovements < adventurer.getMovements().length()) {
				numberMaxOfMovements = adventurer.getMovements().length();
			}
		}

		while (numberMaxOfMovements > 0) {
			for (Adventurer adventurer : serializer.getAdventurers()) {
				if (serializer.getTreasureMap().isMovementPossible(adventurer) && serializer.getTreasureMap()
						.isPassable(adventurer.nextSquare(), serializer.getAdventurers())) {
					adventurer.executeMove();
				} else {
					adventurer.nextMove();
				}
			}
			numberMaxOfMovements--;
		}
	}

	public static void main(String[] args) throws TreasureMapException {
		begin();
		System.out.println("Width: " + serializer.getTreasureMap().getWidth());
		System.out.println("Height: " + serializer.getTreasureMap().getHeight());
		System.out.println("Montagne: " + serializer.getTreasureMap().getSquares()[1][0].getType());
		System.out.println("Montagne: " + serializer.getTreasureMap().getSquares()[2][1].getType());
		System.out.println("Trésors en 0,3: " + serializer.getTreasureMap().getSquares()[0][3].getNumberOfTreasures());
		System.out.println("Trésors en 1,3: " + serializer.getTreasureMap().getSquares()[1][3].getNumberOfTreasures());
		System.out.println("Aventurier 1 en: " + serializer.getAdventurers().get(0).getPositionX() + ", "
				+ serializer.getAdventurers().get(0).getPositionX());
		System.out.println("Aventurier 2 en: " + serializer.getAdventurers().get(1).getPositionX() + ", "
				+ serializer.getAdventurers().get(1).getPositionX());
		System.out.println("Aventurier 3 en: " + serializer.getAdventurers().get(2).getPositionX() + ", "
				+ serializer.getAdventurers().get(2).getPositionX() + " orienté "
				+ serializer.getAdventurers().get(2).getOrientation());
	}

}
