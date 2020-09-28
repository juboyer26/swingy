
package swingy.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import swingy.model.Gameplay;
import swingy.model.Hero;
import swingy.view.select.SelectView;

public class SelectController {

	private SelectView view;
	private Gameplay game;
	HashMap<Integer, String> heroes = new HashMap<Integer, String>();

	public SelectController(SelectView view) {
		this.view = view;
		game = Gameplay.getInstance();
	}

	public HashMap<Integer, String> listHero() {
		int i = 1;
		try {
			final FileInputStream fstream = new FileInputStream("heroes.txt");
			final BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;

			// loop through the file and put all found heroes on the hashmap
			while ((strLine = br.readLine()) != null) {
				if(!strLine.isEmpty())
					heroes.put(i, strLine);
				i++;
			}
			br.close();
		} catch (Exception e) {
			view.showInfo("An error occurred. File not found!");
		}
		return heroes;
	}

	public void createHero(String nameInput, int heroNum) {
		if (!heroes.containsKey(heroNum)) {
			view.showInfo("No such hero try again :-)");
		} else {
			String[] heroData = heroes.get(heroNum).split(" ");
			Hero hero = null;
			try {
				hero = new Hero(nameInput, Integer.parseInt(heroData[0]), Integer.parseInt(heroData[2]),
						Integer.parseInt(heroData[1]), heroData[5], Integer.parseInt(heroData[4]), 0,
						Integer.parseInt(heroData[3]), null, null, null);
				hero.heroValidation();
			} catch (Exception e) {
				view.showInfo(e.getMessage());
				view.userInput();
				return;
			}
			game.initTheGame(hero);
			view.openGame();
		}
	}
}
