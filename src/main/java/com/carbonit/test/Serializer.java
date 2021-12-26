package com.carbonit.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Serializer {

	private TreasureMap treasureMap;
	private ArrayList<Adventurer> adventurers;

	public TreasureMap getTreasureMap() {
		return treasureMap;
	}

	public ArrayList<Adventurer> getAdventurers() {
		return adventurers;
	}

	public void readFile(String path) throws TreasureMapException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
			String[] lineArray;
			adventurers = new ArrayList<Adventurer>();
			Square[][] squares = null;

			while ((line = br.readLine()) != null) {
				lineArray = line.split(" - ");
				String firstChar = lineArray[0];

				switch (firstChar) {
				case "C":
					int width = Integer.parseInt(lineArray[1]);
					int height = Integer.parseInt(lineArray[2]);
					treasureMap = new TreasureMap(width, height);
					squares = new Square[width][height];
					for (int indexW = 0; indexW < width; indexW++) {
						for (int indexH = 0; indexH < height; indexH++) {
							squares[indexW][indexH] = new Square("P", indexW, indexH);
						}
					}
					break;

				case "M":
					if (squares == null) {
						throw new TreasureMapException(
								"Veuillez indiquer les dimensions de la carte en première ligne de fichier.");
					}
					squares[Integer.parseInt(lineArray[1])][Integer.parseInt(lineArray[2])].setType("M");
					treasureMap.getMountains()
							.add(squares[Integer.parseInt(lineArray[1])][Integer.parseInt(lineArray[2])]);
					break;

				case "T":
					squares[Integer.parseInt(lineArray[1])][Integer.parseInt(lineArray[2])]
							.setNumberOfTreasures(Integer.parseInt(lineArray[3]));
					treasureMap.getTreasures()
							.add(squares[Integer.parseInt(lineArray[1])][Integer.parseInt(lineArray[2])]);
					break;

				case "A":
					Adventurer adventurer = new Adventurer(lineArray[1], Integer.parseInt(lineArray[2]),
							Integer.parseInt(lineArray[3]), Orientation.valueOf(lineArray[4]), lineArray[5]);
					adventurers.add(adventurer);
					break;
				}
			}
			treasureMap.setSquares(squares);
			treasureMap.setAdventurers(adventurers);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
