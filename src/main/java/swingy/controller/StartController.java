package swingy.controller;

import swingy.view.start.InitializeGame;

public class StartController {
    private InitializeGame view;

    public StartController(InitializeGame view) {
        this.view = view;
    }

    public void createHero() {
        view.createHero();
    }

    public void selectHero() {
        view.selectHero();
    }
}
