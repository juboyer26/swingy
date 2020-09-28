package swingy.model;

import swingy.model.artifact.Weapon;
import swingy.model.artifact.Helm;
import swingy.model.artifact.Armor;

public class HeroBuilder {
    private int attack;
    private int defence;
    private int hp;
    private String heroClass;
    private int level;
    private int exp;
    private String name;
    private int id;
    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    public Hero getHero() {
        return new Hero(name, attack, defence, hp, heroClass, level, id , exp, weapon, helm, armor);
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    public void setDefence(int defence){
        this.defence = defence;
    }
    public void setHeroClass(String heroClass){
        this.heroClass = heroClass;
    }
    public void setExp(int exp){
        this.exp = exp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public void setLevel(int level){
        this.level = level;
    }
}