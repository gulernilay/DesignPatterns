package Template;

//============================================================================
//Name        : TemplateMethod.jav
//
//The classes and/or objects participating in this pattern are:
//1. AbstractClass  (CheckBackground)
//			Defines abstract primitive operations that concrete subclasses
//			define to implement steps of an algorithm implements a template
//			method defining the skeleton of an algorithm. The template method
//			calls primitive operations as well as operations defined in
//			AbstractClass or those of other objects.
//2. ConcreteClass  (MortgageLoanApp, EquityLoanApp )
//			implements the primitive operations ot carry out subclass-specific
//			steps of the algorithm
//============================================================================

class Data {
    static public int getIncome() {return 50000;};
    static public int getCreditScore() { return 650;};
}

//This is the AbstractClass class.

abstract class CheckBackground {
    //	CheckBackground(String name) {_name = name;}
    public String getName() { return _name;}
    // This is our template method.
    public void check() {
        prepareApplication ();
        Boolean status = checkBank() && checkCredit() &&
                checkLoan() && checkStock() &&
                checkIncome();
        finalizeApplication(status);
    }
    // These are our concrete template operations.
    protected void prepareApplication () {
        System.out.println("Prepared paperwork");
    }
    protected void finalizeApplication(Boolean status) {
        if ( status)
            System.out.println("Application Accepted");
        else
            System.out.println("Application Rejected");
    }
    // These are Primitive Operations which will be overridden
    // by the subclasses. They are all abstract.
    protected abstract Boolean checkBank();
    protected abstract Boolean checkCredit();
    protected abstract Boolean checkLoan();
    protected abstract Boolean checkStock();
    protected abstract Boolean checkIncome();
    protected String _name;
}

//Concrete MortgageLoanApp class which implements the
//primitive operations.

class MortgageLoanApp extends  CheckBackground {
    public	MortgageLoanApp(String name) { _name = name;}
    //other methods
    protected Boolean checkBank() { //check acct, balance
        System.out.println("check bank...");
        return true;
    }
    protected Boolean checkCredit() { //check score from 3 companies
        System.out.println("check credit..." + (Data.getCreditScore() > 700?"GOOD":"BAD"));
        return Data.getCreditScore() > 700?true:false;
    }
    protected Boolean checkLoan() { //check other loan info
        System.out.println("check other loan...");
        return true;
    }
    protected Boolean checkStock() {  //check how many stock values
        System.out.println("check stock values...");;
        return true;
    }
    protected Boolean checkIncome() {  //check how much a family make
        System.out.println("check income...");
        return (Data.getIncome() < 50000?false:true);
    }
}
//Concrete EquityLoanApp class which implements the
//primitive operations. checkIncome and checkCredit
//differs from MortgageLoanApp's corresponding
//methods.

class EquityLoanApp extends CheckBackground {
    public EquityLoanApp(String name) { _name = name;}
    protected Boolean checkBank() { //ck acct, balance
        System.out.println("check bank...");
        return true;
    }
    protected Boolean checkCredit() { //ck score from 3 companies
        System.out.println("check credit..." + (Data.getCreditScore() > 600?"GOOD":"BAD"));
        return Data.getCreditScore() > 600?true:false;
    }
    protected Boolean checkLoan() { //ck other loan info
        System.out.println("check other loan...");
        return true;
    }
    protected Boolean checkStock() {  //ck how many stock values
        System.out.println("check stock values...");;
        return true;
    }
    protected Boolean checkIncome() {  //ck how much a family make
        System.out.println("check income...");
        return (Data.getIncome() < 40000?false:true);
    }
};

//This is our test program.
public class Main1{

    public static void main(String[] args) {

        CheckBackground p = new MortgageLoanApp("Ahmet");
        System.out.println("Check client " + p.getName());
        System.out.println(" mortgage loan application. ");
        p.check();

        p = new EquityLoanApp("Ahmet");
        System.out.println("Check client " + p.getName());
        System.out.println(" equity loan application. ");
        p.check();
    }
}


