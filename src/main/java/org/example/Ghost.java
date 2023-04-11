package org.example;

import java.util.Random;

public class Ghost {
    public int health;
    public int atk;
    public int def;

    public boolean canSpeak = false;
    public boolean isTransparent = false;

    public Ghost (int health, int atk, int def){
        this.health = health;
        this.atk = atk;
        this.def = def;
    };

    // say boo just says boo to the console

    public void learnToSpeak(Player player){
        if(this.canSpeak == true){
            this.sayBoo(player);
        }else {
            System.out.println("the ghost learned to talk from listening to you");
            this.canSpeak = true;
        }
    }
    public void sayBoo(Player player){
        if ( this.canSpeak){
            System.out.println("BOO!!");
            player.loseAtkPower();
        }else{
            System.out.println("ghost can't speak yet");
        }
        return;
    };

    // haunt increases the ghosts health by + 3
    public void haunt(){
        this.health += 3;
        System.out.println("health has increased by 3");
    };

    // ghost does nothing but won't take damage from an attack next turn if the player attacks
    public void becomeTransparent(){
        this.isTransparent = true;
        System.out.println(" the ghost has become transparent, nothing can hurt it next turn");
    }
    public boolean getIsTransparent(){
        return this.isTransparent;
    }

    // player will take the difference of the damage if ghost atk is higher than player def + random
    // random represents the dice roll that will be added to ghosts attack
    public void ghostAttack(Player player){
        System.out.println("the ghost is trying to attack!");
        Random rand = new Random();
        double attackPlusRoll = Math.floor(Math.random() *(4-1 +this.atk) + 1);
        if(this.atk > player.def){
            System.out.print("attacking player for "+attackPlusRoll+ "damage");
            System.out.print("player has"+player.def);
            player.setPlayerHealth(this.atk-player.def);
        }else{
            System.out.println("the attack does no damage: ghost_attack:"+this.atk+"player_defense:"+player.def);
        }
    }

    public int getHealth(){
        return this.health;
    }

    public void setGhostHealth(int val){
        this.health = this.health-val;
    }
}
