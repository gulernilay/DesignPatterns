package Template;

import java.util.Scanner;

abstract class GameTemplate{
    abstract void initialize(); // different in each so it is hook
    abstract void end(); // different in each so it is hook
    protected void start() {
        System.out.println("Default start implementation");
    }

    protected void playLoop() {
        System.out.println("Default game loop");
    }
    public final void play() {
        initialize();
        start();
        playLoop();
        end();
    }

}
class StrategyGame extends GameTemplate{
    protected void initialize() {
        System.out.println("Initializing strategy game environment.");
    }

    protected void start() {
        System.out.println("Default start implementation");
    }

    protected void playLoop() {
        System.out.println("Default game loop");
    }
    @Override
    protected void end() {
        System.out.println("Cleaning up strategy game setup.");
    }

}
class ActionGame extends GameTemplate{

    protected void start() {
        System.out.println("Default start implementation");
    }
    @Override
    protected void initialize() {
        System.out.println("Setting up action game environment.");
    }
    @Override
    protected void end() {
        System.out.println("Closing action game session.");
    }
    protected void playLoop() {
        System.out.println("Default game loop");
    }

}


public class GameQuestion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the game you want to play:");
        System.out.println("1: Strategy Game");
        System.out.println("2: Action Game");

        int choice = scanner.nextInt();

        GameTemplate game;

        switch (choice) {
            case 1:
                game = new StrategyGame();
                break;
            case 2:
                game = new ActionGame();
                break;
            default:
                System.out.println("Invalid selection.");
                return; // Exit the program if the selection is invalid
        }

        game.play(); // Play the selected game using the Template Method
    }
}






/*
The main stages of setting up a game are initializing the game environment, starting the game, playing the actual game loop, and then ending the game.
However, the details of initializing and ending the game can vary depending on whether it's a strategy game or an action game.

Requirements:

Define an abstract base class Game that outlines the template method play() which consists of a series of steps for playing the game.
Implement the invariant parts of the algorithm within the Game class.
Allow the variant parts of the game setup to be customized by subclasses through methods that are designed to be overridden.
Create two subclasses StrategyGame and ActionGame that implement their specific variations of the game initialization and ending procedures.


 */