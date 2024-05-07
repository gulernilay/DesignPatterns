
package Facade.Example1;

// The classes and/or objects participating in this pattern are:
// 1. Facade  (Mortgage)
//		- knows which subsystem classes are responsible for a request.
//		- delegates client requests to appropriate subsystem objects.
// 2. Subsystem classes   (Bank, Credit, Loan)
//		- implement subsystem functionality.
//		- handle work assigned by the Facade object.
//		- have no knowledge of the facade and keep no reference to it.
//Mortgage sınıfının kredi uygunluğunu değerlendirmek için kullanılan alt sistemlerdir.




// Client class. "Customer"    :
class Customer {
    private	String name;
    public Customer(String name) {this.name = name; }
    public String getName() {return name;}
}
// Subsystem Class 1. "Bank"
class Bank { // Check the money in the bank account of customer
    public Boolean HasSufficientSavings(Customer c, int amount) {
        System.out.println("Check bank balance of " + c.getName() +
                " for the amount " + amount);
        return true;
    }
}
// Subsystem Class 2. "Credit" :müşterinin kredi geçmişini kontrol eder. Müşterinin kredi skorunu ve geçmişini analiz eder ve mevcut durumuyla uygun olup olmadığını belirler.
class Credit {
    public Boolean HasGoodCredit(Customer c) {
        System.out.println("Check credit for " + c.getName());
        return true;
    }
}
// Subsystem Class 3. "Loan" :Bu metod, müşterinin kötü kredi geçmişini kontrol eder. Yani, müşterinin daha önce almış olduğu kötü kredileri araştırır ve mevcut durumuyla uygun olup olmadığını belirler.
class Loan {
    public Boolean HasNoBadLoans(Customer c) {
        System.out.println("Check outstanding loans for " + c.getName());
        return true;
    }
}



// Facade. "Mortgage" :Mortgage sınıfı, müşterinin kredi uygunluğunu belirlemek için Bank, Loan ve Credit alt sistemlerini kullanır.
class Mortgage {
    //Bu alt sınıflar, Mortgage sınıfının içinde yaratılır.
    private	Bank bank;
    private Loan loan;
    private Credit credit;
    public	Mortgage() {
        bank = new Bank();
        loan = new Loan();
        credit = new Credit();
    }
    //bir müşterinin kredi uygunluğunu belirler. Müşterinin adı ve kredi talep edilen miktarı parametre olarak alır. Ardından, müşterinin kredi uygunluğunu değerlendirmek için alt sistemlerle etkileşime girer.
    public Boolean IsEligible(Customer cust, int amount) {
        System.out.println(cust.getName() + " applies for " + amount + "TL loan");
        Boolean eligible = true;

        // Check creditworthiness of applicant :Banka alt sistemine müşterinin hesap bakiyesi ve talep edilen kredi miktarını gönderir. Banka, müşterinin yeterli bir tasarrufunun olup olmadığını kontrol eder.
        if (!bank.HasSufficientSavings(cust, amount)) {
            eligible = false;
        }
        //Loan alt sistemine müşteriyi gönderir ve müşterinin kötü kredi geçmişinin olup olmadığını kontrol eder.
        else if (!loan.HasNoBadLoans(cust)) {
            eligible = false;
        }
        //Credit alt sistemine müşteriyi gönderir ve müşterinin iyi bir kredi geçmişine sahip olup olmadığını kontrol eder.
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