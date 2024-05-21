package GradedLab2.Example3;
import java.util.HashMap;
import java.util.Map;

// Singleton Pattern - MessagingApplication
class MessagingApplication {
    private static MessagingApplication instance;
    private Map<String, MessageSender> messageSenders;
    private Map<String, Logger> loggers;

    private MessagingApplication() {
        messageSenders = new HashMap<>();
        loggers = new HashMap<>();
    }

    public static synchronized MessagingApplication getInstance() {
        if (instance == null) {
            instance = new MessagingApplication();
        }
        return instance;
    }

    public void registerMessageSender(String senderType, MessageSender messageSender) {
        messageSenders.put(senderType, messageSender);
    }

    public void registerLogger(String loggerType, Logger logger) {
        loggers.put(loggerType, logger);
    }

    public void sendMessage(String senderType, String message) {
        MessageSender messageSender = messageSenders.get(senderType);
        if (messageSender == null) {
            System.out.println("Message sender not found: " + senderType);
            return;
        }
        messageSender.sendMessage(message);
    }

    public void logMessage(String loggerType, String message) {
        Logger logger = loggers.get(loggerType);
        if (logger == null) {
            System.out.println("Logger not found: " + loggerType);
            return;
        }
        logger.log(message);
    }
}

// Facade Pattern - MessagingFacade
class MessagingFacade {
    private final MessagingApplication messagingApplication;

    public MessagingFacade() {
        this.messagingApplication = MessagingApplication.getInstance();
    }

    public void sendMessage(String senderType, String message) {
        messagingApplication.sendMessage(senderType, message);
    }

    public void logMessage(String loggerType, String message) {
        messagingApplication.logMessage(loggerType, message);
    }
}

// Abstract Factory Pattern - MessagingFactory
interface MessagingFactory {
    MessageSender createMessageSender();
    Logger createLogger();
}

// Concrete Factories
class EmailMessagingFactory implements MessagingFactory {
    @Override
    public MessageSender createMessageSender() {
        return new EmailSender();
    }

    @Override
    public Logger createLogger() {
        return new FileLogger();
    }
}

class SMSMessagingFactory implements MessagingFactory {
    @Override
    public MessageSender createMessageSender() {
        return new SMSSender();
    }

    @Override
    public Logger createLogger() {
        return new ConsoleLogger();
    }
}

 class PushNotificationMessagingFactory implements MessagingFactory {
    @Override
    public MessageSender createMessageSender() {
        // Adapting push notifications to SMS sender
        return new PushNotificationAdapter(new SMSSender());
    }

    @Override
    public Logger createLogger() {
        return new DatabaseLogger();
    }
}

// Abstract Product - MessageSender
interface MessageSender {
    void sendMessage(String message);
}

// Concrete Products
class EmailSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        // Logic to send email message
        System.out.println("Email sent: " + message);
    }
}

class SMSSender implements MessageSender {
    @Override
    public void sendMessage(String message) {
        // Logic to send SMS message
        System.out.println("SMS sent: " + message);
    }
}

// Adapter Pattern
class PushNotificationAdapter implements MessageSender {
    private SMSSender smsSender;

    public PushNotificationAdapter(SMSSender smsSender) {
        this.smsSender = smsSender;
    }

    @Override
    public void sendMessage(String message) {
        // Adapt push notification to SMS sending functionality
        System.out.println("Push notification sent (via SMS): " + message);
        smsSender.sendMessage(message);
    }
}

// Abstract Product - Logger
interface Logger {
    void log(String message);
}

// Concrete Products
class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        // Log message to console
        System.out.println("Logged to console: " + message);
    }
}

class FileLogger implements Logger {
    @Override
    public void log(String message) {
        // Log message to file
        System.out.println("Logged to file: " + message);
    }
}

class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        // Log message to database
        System.out.println("Logged to database: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        // Register factories with singleton instance of MessagingApplication
        MessagingApplication.getInstance().registerMessageSender("Email", new EmailMessagingFactory().createMessageSender());
        MessagingApplication.getInstance().registerMessageSender("SMS", new SMSMessagingFactory().createMessageSender());
        MessagingApplication.getInstance().registerMessageSender("PushNotification", new PushNotificationMessagingFactory().createMessageSender());
        MessagingApplication.getInstance().registerLogger("Console", new SMSMessagingFactory().createLogger());
        MessagingApplication.getInstance().registerLogger("File", new EmailMessagingFactory().createLogger());
        MessagingApplication.getInstance().registerLogger("Database", new PushNotificationMessagingFactory().createLogger());

        // Use facade to send messages and log them
        MessagingFacade messagingFacade = new MessagingFacade();
        messagingFacade.sendMessage("Email", "Hello from email");
        messagingFacade.sendMessage("SMS", "Hello from SMS");
        messagingFacade.sendMessage("PushNotification", "Hello from push notification");
        messagingFacade.logMessage("Console", "Logging to console");
        messagingFacade.logMessage("File", "Logging to file");
        messagingFacade.logMessage("Database", "Logging to database");
    }
}


/*
You are working on a messaging application in Java that supports sending messages through various communication channels such as email, SMS, and push notifications.
Additionally, the application provides a feature to log messages to different destinations such as the console, file, and database.

Adapter Pattern:
Implement an adapter to enable the messaging application to send messages via push notifications using the existing SMS sending functionality. The adapter should adapt the push notification API to the standard interface used by the messaging application.
Singleton Pattern:
Ensure that the messaging application follows the Singleton pattern to guarantee that there is only one instance of the application available throughout the application's lifecycle.
Facade Pattern:
Design a facade to simplify the process of sending messages and logging them to various destinations. The facade should provide methods for sending messages via different channels (email, SMS, push notifications) and logging messages to different destinations (console, file, database).
Abstract Factory Pattern:
Implement an abstract factory to create different types of message senders (e.g., EmailSender, SMSSender, PushNotificationSender) and loggers (e.g., ConsoleLogger, FileLogger, DatabaseLogger). Each factory should be capable of creating senders and loggers that support specific communication channels and logging destinations.
Your implementation should demonstrate the usage of these design patterns to create a flexible and extensible messaging application that can handle message sending and logging efficiently.

Provide the Java code for your implementation along with explanations of how each design pattern is applied in the context of the messaging application.
 */
