package GradedLab2.Example2;

import java.util.HashMap;
import java.util.Map;

// Singleton Pattern - PaymentProcessingSystem
class PaymentProcessingSystem {
    private static PaymentProcessingSystem instance;
    private Map<String, PaymentGateway> paymentGateways;

    private PaymentProcessingSystem() {
        paymentGateways = new HashMap<>();
    }
    public static synchronized PaymentProcessingSystem getInstance() {
        if (instance == null) {
            instance = new PaymentProcessingSystem();
        }
        return instance;
    }
    public void registerPaymentGateway(String gatewayName, PaymentGateway paymentGateway) {
        paymentGateways.put(gatewayName, paymentGateway);
    }
    public void processPayment(String gatewayName, double amount, String currency) {
        PaymentGateway paymentGateway = paymentGateways.get(gatewayName);
        if (paymentGateway == null) {
            System.out.println("Payment gateway not found: " + gatewayName);
            return;
        }
        paymentGateway.processPayment(amount, currency);
    }
}
// Facade Pattern - PaymentProcessingFacade********************************************************************************************
class PaymentProcessingFacade {
    private final PaymentProcessingSystem paymentProcessingSystem;

    public PaymentProcessingFacade() {
        this.paymentProcessingSystem = PaymentProcessingSystem.getInstance();
    }

    public void initiatePayment(String gatewayName, double amount, String currency) {
        paymentProcessingSystem.processPayment(gatewayName, amount, currency);
    }

    public void handleCallback(String gatewayName, String transactionId, boolean success) {
        // Handle callback logic here
        System.out.println("Callback handled for transaction " + transactionId + " on gateway " + gatewayName);
    }

    public void generatePaymentReport() {
        // Generate payment report logic here
        System.out.println("Payment report generated");
    }
}

// Abstract Factory Pattern - PaymentGatewayFactory****************************************************************************
interface PaymentGatewayFactory {
    PaymentGateway createPaymentGateway(); // Paypal,Stripe , and Square.
}
// Concrete Factory - PayPalGatewayFactory
class PayPalGatewayFactory implements PaymentGatewayFactory {
    @Override
    public PaymentGateway createPaymentGateway() {
        return new PayPalGatewayAdapter(new PayPalAPI());
    }
}
// Abstract Product - PaymentGateway
interface PaymentGateway {
    void processPayment(double amount, String currency);
}
// Concrete Product - PayPalGatewayAdapter
class PayPalGatewayAdapter implements PaymentGateway {
    private PayPalAPI payPalAPI;
    public PayPalGatewayAdapter(PayPalAPI payPalAPI) {
        this.payPalAPI = payPalAPI;
    }
    @Override
    public void processPayment(double amount, String currency) {
        payPalAPI.makePayment(amount, currency);
    }
}
// Existing PayPal API
class PayPalAPI {
    public void makePayment(double amount, String currency) {
        // Logic to process payment using PayPal API
        System.out.println("Payment processed via PayPal: " + amount + " " + currency);
    }
}
public class example {
    public static void main(String[] args) {
        // Register payment gateways with Singleton
        PaymentProcessingSystem.getInstance().registerPaymentGateway("PayPal", new PayPalGatewayFactory().createPaymentGateway());

        // Use Facade to initiate payments and handle callbacks
        PaymentProcessingFacade paymentProcessingFacade = new PaymentProcessingFacade();
        paymentProcessingFacade.initiatePayment("PayPal", 100.0, "USD");
        paymentProcessingFacade.generatePaymentReport();
    }
}


/*
You are tasked with designing a payment processing system for an e-commerce platform in Java. The system should handle payments using various payment gateways such as PayPal, Stripe, and Square. Additionally, it should provide a unified interface for processing payments and generating payment reports.

Adapter Pattern:
Implement an adapter to integrate the existing PayPal payment gateway with the payment processing system. The adapter should adapt the PayPal API to the standard interface used by the system.
Singleton Pattern:
Ensure that the payment processing system follows the Singleton pattern to ensure that there is only one instance of the system available throughout the application's lifecycle.
Facade Pattern:
Design a facade to simplify the process of interacting with the payment processing system. The facade should provide methods for initiating payments, handling callbacks from payment gateways, and generating payment reports.
Abstract Factory Pattern:
Implement an abstract factory to create different types of payment gateways (e.g., PayPalGateway, StripeGateway, SquareGateway). Each factory should be capable of creating gateways that support specific payment methods and currencies.
Your implementation should demonstrate the usage of these design patterns to create a flexible, extensible, and maintainable payment processing system for the e-commerce platform.

Provide the Java code for your implementation along with explanations of how each design pattern is applied in the context of the payment processing system.


 */
