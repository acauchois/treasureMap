package com.carbonit.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EntryPoint {

	static Serializer serializer = new Serializer();

	public static void begin() throws TreasureMapException {

		serializer.readFile("C:\\Users\\cauch\\eclipse-workspace\\treasuremap\\src\\main\\resources\\map1.txt");
		TreasureMap treasuremap = serializer.getTreasureMap();

		int numberMaxOfMovements = 0;
		for (Adventurer adventurer : serializer.getAdventurers()) {
			if (numberMaxOfMovements < adventurer.getMovements().length()) {
				numberMaxOfMovements = adventurer.getMovements().length();
			}
		}

		while (numberMaxOfMovements > 0) {
			for (Adventurer adventurer : serializer.getAdventurers()) {
				if (adventurer.getPresentMovement().equals("A")) {
					if (treasuremap.isMovementPossible(adventurer) && treasuremap
							.isPassable(treasuremap.nextSquare(adventurer), serializer.getAdventurers())) {
						adventurer.executeMove();
						if (treasuremap.getSquares()[adventurer.getPositionX()][adventurer.getPositionY()]
								.getNumberOfTreasures() > 0) {
							treasuremap.gatherTreasure(
									treasuremap.getSquares()[adventurer.getPositionX()][adventurer.getPositionY()],
									adventurer);
						}
					} else {
						adventurer.nextMove();
					}
				} else {
					adventurer.executeMove();
				}
			}
			numberMaxOfMovements--;
		}
	}

	public static void end() {
		try {
			File endFile = new File(
					"C:\\Users\\cauch\\eclipse-workspace\\treasuremap\\src\\main\\resources\\endFile.txt");
			if (endFile.createNewFile()) {
				System.out.println("File created: " + endFile.getPath());
			} else {
				System.out.println("File already exists.");
			}

			FileWriter myWriter = new FileWriter(endFile.getPath());

			String map = "C - " + serializer.getTreasureMap().getWidth() + " - "
					+ serializer.getTreasureMap().getHeight() + "\n";

			String mountains = "";
			for (Square mountain : serializer.getTreasureMap().getMountains()) {
				mountains += "M - " + mountain.getPositionX() + " - " + mountain.getPositionY() + "\n";
			}

			String treasures = "";
			for (Square treasure : serializer.getTreasureMap().getTreasures()) {
				treasures += "T - " + treasure.getPositionX() + " - " + treasure.getPositionY() + " - "
						+ treasure.getNumberOfTreasures() + "\n";
			}

			String adventurers = "";
			for (Adventurer adventurer : serializer.getAdventurers()) {
				adventurers += "A - " + adventurer.getName() + " - " + adventurer.getPositionX() + " - "
						+ adventurer.getPositionY() + " - " + adventurer.getOrientation() + " - "
						+ adventurer.getNumberOfTreasures() + "\n";
			}

			myWriter.write(map + mountains + treasures + adventurers);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws TreasureMapException {
		begin();
		end();
	}

}
