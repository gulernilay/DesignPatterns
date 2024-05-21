package Template;

import java.util.Scanner;

abstract class BreadRecipe {
    public abstract void mixIngredients();

    public abstract void knead();

    public abstract void bake();

    // Optional hooks
    public void hook1() {
    }

    public void hook2() {
    }

    // Template method
    public final void makeBread() {
        mixIngredients();
        hook1();
        knead();
        hook2();
        bake();
    }
}

class WhiteLoaf extends BreadRecipe {
        @Override
        public void mixIngredients() {
            System.out.println("WHITE LOAF BREAD RECIPE");
            System.out.println("Mix 500g strong white flour, 2 tsp salt and a 7g sachet of fast-action yeast in a large bowl.");
            System.out.println("Make a well in the centre, then add 3 tbsp olive oil and 300ml water, and mix well.");
        }

        @Override
        public void knead() {
            System.out.println("Tip the white loaf dough onto a lightly floured work surface and knead for around 10 mins.");
        }

        @Override
        public void hook2() {
            giveShape();
        }

        public void giveShape() {
            System.out.println("Knock back the white loaf dough (punch the air out and pull the dough in on itself) then gently mould the dough into a ball.");
        }

        @Override
        public void bake() {
            System.out.println("Bake for 25-30 mins until golden brown and the white loaf sounds hollow when tapped underneath.");
        }
    }

class NutsSeeds extends BreadRecipe {
        @Override
        public void mixIngredients() {
            System.out.println("NUTS-SEEDS BREAD RECIPE");
            System.out.println("Mix together some almond flour, coconut flour, baking soda, and salt.");
            System.out.println("Add eggs, olive oil, apple cider vinegar, and honey.");
        }

        @Override
        public void hook1() {
            addNuts();
        }

        public void addNuts() {
            System.out.println("Add in the flax seeds, chia seeds, and a large amount of nuts of your choice.");
        }

        @Override
        public void knead() {
            System.out.println("Tip the mixed dough onto a lightly floured work surface and knead for around 10 mins.");
        }

        @Override
        public void hook2() {
            seedsOnTop();
        }

        public void seedsOnTop() {
            System.out.println("Top your bread with a generous amount of extra seeds such as sunflower seeds.");
        }

        @Override
        public void bake() {
            System.out.println("Transfer the dough to a loaf pan, using a spatula to make sure it’s evenly spread across the pan.");
            System.out.println("Bake your bread for about 38-41 minutes or until a toothpick comes out clean.");
        }
    }


    public class Lab {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to the recipe app.\nPlease choose which recipe you want to see (int):\n1) White Loaf Bread\n2) Nuts-Seeds Bread");
            int choice = scan.nextInt();

            BreadRecipe recipe;
            switch (choice) {
                case 1:
                    recipe = new WhiteLoaf();
                    break;
                case 2:
                    recipe = new NutsSeeds();
                    break;
                default:
                    System.out.println("Wrong choice...");
                    return;
            }

            recipe.makeBread();
        }
    }

