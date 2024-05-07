
package Facade.Example1;

// The classes and/or objects participating in this pattern are:
// 1. Facade  (Mortgage)
//		- knows which subsystem classes are responsible for a request.
//		- delegates client requests to appropriate subsystem objects.
// 2. Subsystem classes   (Bank, Credit, Loan)
//		- implement subsystem functionality.
//		- handle work assigned by the Facade object.
//		- have no knowledge of the facade and keep no reference to it.

// Client class. "Customer"    :
class Customer {
    private	String name;
    public Customer(String name) {this.name = name; }
    public String getName() {return name;}
}
// Subsystem Class 1. "Bank"
class Bank {
    public Boolean HasSufficientSavings(Customer c, int amount) {
        System.out.println("Check bank balance of " + c.getName() +
                " for the amount " + amount);
        return true;
    }
}
// Subsystem Class 2. "Credit"
class Credit {
    public Boolean HasGoodCredit(Customer c) {
        System.out.println("Check credit for " + c.getName());
        return true;
    }
}
// Subsystem Class 3. "Loan"
class Loan {
    public Boolean HasNoBadLoans(Customer c) {
        System.out.println("Check outstanding loans for " + c.getName());
        return true;
    }
}



// Facade. "Mortgage"
class Mortgage {
    private	Bank bank;
    private Loan loan;
    private Credit credit;
    public	Mortgage() {
        bank = new Bank();
        loan = new Loan();
        credit = new Credit();
    }

    public Boolean IsEligible(Customer cust, int amount) {
        System.out.println(cust.getName() + " applies for " + amount + "TL loan");
        Boolean eligible = true;

        if (!bank.HasSufficientSavings(cust, amount)) {
            eligible = false;
        }

        else if (!loan.HasNoBadLoans(cust)) {
            eligible = false;
        }

        else if (!credit.HasGoodCredit(cust)) {
            eligible = false;
        }
        return eligible;
    }
}


public class Exercise1 {
    public static void main(String[] args) {
        // Facade
        Mortgage mortgage = new Mortgage();
        // Evaluate mortgage eligibility for the customer.
        Customer customer = new Customer("Ufuk Celikkan");
        Boolean eligable = mortgage.IsEligible(customer,100000);
        System.out.print(customer.getName());
        System.out.println(" has been " +
                (eligable ? "approved." : "rejected."));
    }
}