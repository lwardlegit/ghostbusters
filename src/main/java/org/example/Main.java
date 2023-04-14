package org.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String[] playerMethods = {"attack", "searchWebForAnswers"};
        String[] ghostMethods = {"learnToSpeak", "sayBoo", "haunt","becomeTransparent","ghostAttack"};
        System.out.println("BEGIN THE HAUNT!");

        /*
         player will go first, then ghost
         a random action will be taken by whoever goes
         the turns continue until either the ghost is exorcised or the player dies
         */

        // static methods like this one don't need an instance of a class in order to be called, they can be called from anywhere the class is available
        GameActions.lookSomethingUpOnline();

        Player player = new Player("Luther", 10, 1, 3);
        Ghost ghost = new Ghost(20, 0, 3);
        ghost.discoverBook();
        AllBooks.pageFlip();

            while (player.getHealth() > 0 && ghost.getHealth() > 0) {
                Random random = new Random();

                // a random method is chosen each turn
                // this does not include methods like getHealth or methods used by the game to determine state

                    System.out.println("player is taking their turn");
                    int randomMethodIndex = random.nextInt(playerMethods.length);
                    String randomMethodName = playerMethods[randomMethodIndex];
                    System.out.println("player will use "+randomMethodName);

                    if( randomMethodName.equals("attack")) {
                        Method method = Player.class.getMethod(randomMethodName, Ghost.class);
                        method.invoke(player, ghost);
                    }else{
                        Method method = Player.class.getMethod(randomMethodName);
                        method.invoke(player);
                    }



                    System.out.println("ghost is taking their turn");
                    int randomMethodIndex2 = random.nextInt(playerMethods.length);
                    String randomMethodName2 = ghostMethods[randomMethodIndex];
                    System.out.println("ghost will use "+randomMethodName2);

                    if( randomMethodName2.equals("ghostAttack") || randomMethodName2.equals("sayBoo") || randomMethodName2.equals("learnToSpeak") ){
                        Method method2 = Ghost.class.getMethod(randomMethodName2, Player.class);
                        method2.invoke(ghost, player);
                    }else {
                        Method method2 = Ghost.class.getMethod(randomMethodName2);
                        method2.invoke(ghost);
                    }

            }
        if(player.getHealth() == 0){
            System.out.println("player has died");
            System.out.println(player.health);
        }else{
            System.out.println("congratulations you've exorcised the ghost");
            System.out.println("player "+player.health);
            System.out.println("ghost "+ghost.health);
        }
    }
};