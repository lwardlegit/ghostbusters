package org.example;

import java.util.Arrays;
import java.util.Random;

public class Ghost {
    public int health;
    public int atk;
    public int def;

    public static int[] ghostBooksArray = {5, 1, 9, 3, 7, 6, 4, 8, 2};

    public boolean canSpeak = false;
    public boolean isTransparent = false;

    public Ghost (int health, int atk, int def){
        this.health = health;
        this.atk = atk;
        this.def = def;
    };

    // say boo just says boo to the console

    public String learnToSpeak(Player player){
        if(this.canSpeak == true){
            this.sayBoo(player);
        }
            this.canSpeak = true;
            return "the ghost learned to talk from listening to you";
    }
    public String sayBoo(Player player){
        if ( this.canSpeak){
            player.loseAtkPower();
            return "BOO!!";
        }
        return "ghost can't speak yet";


    };

    public void discoverBook(){
        System.out.println("the ghost found one of its old books, maybe the ghost will read it twice?");
        System.out.println("the player has mixed up the ghosts books so they look like this: "+ Arrays.toString(Ghost.ghostBooksArray)+" the ghost will sort them again");
        Ghost.quickSortBooks(Ghost.ghostBooksArray, 0, 8);
        System.out.println("here are the books reordered: "+ Arrays.toString(ghostBooksArray));
    }

    // haunt increases the ghosts health by + 3
    public String haunt(){
        this.health += 3;
        return("health has increased by 3");
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
    public String ghostAttack(Player player){
        System.out.println("the ghost is trying to attack!");
        Random rand = new Random();
        double attackPlusRoll = Math.floor(Math.random() *(4-1 +this.atk) + 1);
        if(this.atk > player.def){
            player.setPlayerHealth(this.atk-player.def);
            return "attacking player for "+attackPlusRoll+ "damage"+"player has"+player.def;
        }else{
            return "the attack does no damage: ghost_attack:"+this.atk+"player_defense:"+player.def;
        }
    }

    public int getHealth(){
        return this.health;
    }

    public void setGhostHealth(int val){
        this.health = this.health-val;
    }

    // ghost will resort its books if the player takes the books out of order
    // this method uses quicksort as the base algorithm
    public static int [] quickSortBooks(int [] ghostBooksArray, int left, int right){
        if(left < right){
            int pIndex = arrPartition(ghostBooksArray, left, right);
            quickSortBooks(ghostBooksArray, left, pIndex -1);
            quickSortBooks(ghostBooksArray, pIndex + 1, right);
        }
        return ghostBooksArray;
    }

    private static int arrPartition(int[] arr, int left, int right){
        int pivotValue = arr[right];
        int i = left -1;

        for (int j = left; j < right; j++){
            if(arr[j] <= pivotValue){
                i++;
                switchBooks(arr, i, j);
            }
        }
        switchBooks(arr,i +1, right);
        return i + 1;
    }

    // helper method to switch i and j after the pivot is established
    // will be private since only this class uses this method
    private static void switchBooks(int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
