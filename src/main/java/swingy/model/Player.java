package swingy.model;

import java.util.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class Player {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 10, message  = "Name must be between 2 and 10 characters")
    protected String name;

    @Min(value = 0, message = "Attack should not be less than 0")
    protected int attack;

    @Min(value = 0, message = "Defense should not be less than 0")
    protected int defense;

    @Min(value = 1, message = "Hp should not be less than 1")
    protected int hp;

    Random random = new Random();

    public Player(String name, int attack, int defense, int hp) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }

    private void attack(Player opponent) {
        if (this.attack > opponent.defense) {
            opponent.setHp(opponent.getHp() - (this.attack - opponent.defense));
        } else if (random.nextInt(10) <= 2) {
            opponent.setHp(opponent.getHp() - this.attack);
        }
    }

    public boolean fight(Player opponent) {
        while (opponent.getHp() > 0 && this.getHp() > 0) {
            this.attack(opponent);
            opponent.attack(this);
        }
        return this.getHp() > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}