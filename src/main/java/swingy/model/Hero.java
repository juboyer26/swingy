package swingy.model;

import swingy.model.artifact.*;
import swingy.util.HeroValidationException;

import javax.validation.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.logging.Level;

public class Hero extends Player {

    @Min(value = 0, message="Level should not be less than zero")
    private int level;
    
    @Min(value = 0, message="Experience should not be less than zero")
    private int exp;

    @NotNull(message = "Hero CLass cannot be null")
    private String heroClass;

    private Weapon weapon;
    private Helm helm;
    private Armor armor;

    private int id;

    public Hero(String name, int attack, int defense, int hp, String heroClass, int level, int id, int exp, Weapon weapon, Helm helm, Armor armor) {
        super(name, attack, defense, hp);
        this.id = id;
        this.level = level;
        this.exp = exp;
        this.heroClass = heroClass;
        this.helm = helm;
        this.weapon = weapon;
        this.armor = armor; 
    }
  
    public void equipWeapon(Weapon weapon){

        if(weapon != null && this.hp > 0){
            this.attack += weapon.getPoints();
            this.weapon = weapon;
        }
    }

    public void equipArmor(Armor armor){
        if(armor != null && this.hp > 0){
            this.defense += armor.getPoints();
            this.armor = armor;
        }
    
        }
    public void equipHelm(Helm helm){
        if(helm != null && this.hp > 0){
            this.hp += helm.getPoints();
            this.helm = helm; 
        }
    }
    
    public Weapon getWeapon() {
        return weapon;
    }
    
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor){
        this.armor = armor;
    }

    public Helm getHelm() {
        return helm;
    }

    public void setHelm(Helm helm){
        this.helm = helm;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public void heroValidation() throws HeroValidationException{

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        String errorMessage = "";

        Set<ConstraintViolation<Hero>> violations = validator.validate(this);

        if (violations.size() != 0) {
            for (ConstraintViolation<Hero> violation : violations) {
                //System.err.println(violation.getMessage()); 
                errorMessage = violation.getMessage();
            }
            throw new HeroValidationException(errorMessage);
        }

    }
}