package org.example;
import java.util.Random;


public class Player {
    public String name;
    public static int health;
    public int def;
    public int atk;
    private String[] domains = {"hauntings","findings","solutions","busters","slayers","defense","protection","powers","scary"};

    public Player(String name, int health, int atk, int def) {
        this.name = name;
        this.health = health;
        this.atk = atk;
        this.def = def;
    }

    // player will make an http request for a random site trying to find anything related to ghosts
    // if the response is 200 we assume the player has learned something new about ghosts and increased their knowledge
    // this in turn increases player atk power

    public void loseHealth(int amount){
        this.health = this.health-amount;
        System.out.println(this.name+" now has "+this.health+" health");
    }
   public void searchWebForAnswers () {
       Random random = new Random();
        int randomIndex = random.nextInt(this.domains.length);
        String randomUrl = domains[randomIndex];

        // if this website exists we will increase atk by 1
        if( randomUrl.equals("defense") || randomUrl.equals("busters")){
            double newAtk = Math.floor(Math.random() *(4-1 +this.atk) + 1);
            this.atk +=newAtk;
        }
    }

    public void attack(Ghost ghost){
        Random rand = new Random();
        double attackPlusRoll = Math.floor(Math.random() *(4-1 +this.atk) + 1);
        if(this.atk > ghost.def && ghost.getIsTransparent() == false){
            System.out.print("attacking ghost for "+(int)attackPlusRoll+ " damage");
            System.out.print("ghost has "+ghost.def+" defense");

            ghost.setGhostHealth(this.atk-ghost.def);
        }else{
            System.out.println("the attack does no damage: player_atk:"+this.atk+"ghost_defense:"+ghost.def);
        }
    }

    public void loseAtkPower(){
        if(this.atk < 1){
            return;
        }
        this.atk = this.atk-1;
    }

    public int getHealth(){
        return this.health;
    }
    public void setPlayerHealth(int val){
        this.health = this.health-val;
    }
}
