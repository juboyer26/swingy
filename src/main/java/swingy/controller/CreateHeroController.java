package swingy.controller;

import swingy.model.*;
import swingy.view.create.*;
import swingy.util.*;

public class CreateHeroController {

    private Gameplay gamePlay;
    private CreateView view;

    public CreateHeroController(CreateView view) {
        this.view = view;
        gamePlay = Gameplay.getInstance();
    }

    public void createHero(String name, int heroClass) {
            Hero newHero = null;
            try {
                newHero = HeroFactory.newHero(name, heroClass);
                newHero.heroValidation();
            } catch (HeroValidationException | IllegalArgumentException e) {
                view.showInfo(e.getMessage());
                view.userInput();
                return;
            }
            gamePlay.initTheGame(newHero);
            view.openGame();
    }
}