package swingy.model;

public class HeroFactory {

    public static Hero newHero(String name, int heroNum) {
        switch (heroNum) {
            case 1:
                return CreateHero.createWarrior(name);
            case 2:
                return CreateHero.createArcher(name);
            case 3:
                return CreateHero.createAssassin(name);
            case 4:
                return CreateHero.createRanger(name);
            case 5:
                return CreateHero.createMage(name);
            case 6:
                return CreateHero.createHunter(name);
            default:
                throw new IllegalArgumentException("Invalid hero class: " + heroNum);
        }
    }
}