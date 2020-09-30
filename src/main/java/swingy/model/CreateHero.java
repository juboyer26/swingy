package swingy.model;


public class CreateHero {

    public static HeroBuilder newBuilder(String name){
        HeroBuilder builder = new HeroBuilder();
        builder.setName(name);
        builder.setExp(0);
        builder.setLevel(0);
        return builder;
    }

    public static Hero createWarrior(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setExp(0);
        heroBuilder.setLevel(0);
        heroBuilder.setName(name);
        heroBuilder.setAttack(60);
        heroBuilder.setDefence(20);
        heroBuilder.setHp(120);
        heroBuilder.setHeroClass("warrior");
        return heroBuilder.getHero();
    }

    public static Hero createArcher(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setExp(0);
        heroBuilder.setLevel(0);
        heroBuilder.setName(name);
        heroBuilder.setAttack(30);
        heroBuilder.setDefence(35);
        heroBuilder.setHp(100);
        heroBuilder.setHeroClass("archer");
        return heroBuilder.getHero();
    }

    public static Hero createAssassin(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setExp(0);
        heroBuilder.setLevel(0);
        heroBuilder.setName(name);
        heroBuilder.setAttack(25);
        heroBuilder.setDefence(25);
        heroBuilder.setHp(90);
        heroBuilder.setHeroClass("assassin");
        return heroBuilder.getHero();
    }

    public static Hero createRanger(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setExp(0);
        heroBuilder.setLevel(0);
        heroBuilder.setName(name);
        heroBuilder.setAttack(40);
        heroBuilder.setDefence(50);
        heroBuilder.setHp(110);
        heroBuilder.setHeroClass("ranger");
        return heroBuilder.getHero();
    }

    public static Hero createMage(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setExp(0);
        heroBuilder.setLevel(0);
        heroBuilder.setName(name);
        heroBuilder.setAttack(45);
        heroBuilder.setDefence(10);
        heroBuilder.setHp(80);
        heroBuilder.setHeroClass("mage");
        return heroBuilder.getHero();
    }

    public static Hero createHunter(String name) {
        HeroBuilder heroBuilder = new HeroBuilder();
        heroBuilder.setExp(0);
        heroBuilder.setLevel(0);
        heroBuilder.setName(name);
        heroBuilder.setAttack(25);
        heroBuilder.setDefence(20);
        heroBuilder.setHp(110);
        heroBuilder.setHeroClass("hunter");
        return heroBuilder.getHero();
    }
}