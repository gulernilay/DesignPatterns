package Singleton;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//
//The classes and/or objects participating in this pattern are:
//1. Singleton   (LoadBalancer)
//		Defines an Instance operation that lets clients access its unique
//		instance. Instance is a class operation. Responsible for
//		creating and maintaining its own unique instance

//This is the "Singleton" class.

class LoadBalancer {

    public static LoadBalancer GetLoadBalancer() {
        //
        // Support multithreaded applications through
        // 'Double checked locking' pattern which (once the
        // instance exists) avoids locking each time the method
        // is invoked.
        //
        if (instance == null) {
            lock.lock();
            System.out.println("Acquired lock");
            try {
                if (instance == null)
                    instance = new LoadBalancer();
            } finally {
                lock.unlock();
                System.out.println("Released lock");
            }
        }
        return instance;
    }
    // Simple load balancer
    public String getServer() {
        Random rand = new Random();
        return servers.get(rand.nextInt(servers.size()));
    }

    // Constructor (private).
    private LoadBalancer() {
        // List of available servers
        servers.add("ServerI");
        servers.add("ServerII");
        servers.add("ServerIII");
        servers.add("ServerIV");
        servers.add("ServerV");
    }

    private static LoadBalancer instance = null;
    private ArrayList<String> servers = new ArrayList<String>();
    private static final Lock lock = new ReentrantLock();
} // class LoadBalancer

public class Exercise2 {
    public static void main(String[] args) {

        LoadBalancer lb1 = LoadBalancer.GetLoadBalancer();
        LoadBalancer lb2 = LoadBalancer.GetLoadBalancer();
        LoadBalancer lb3 = LoadBalancer.GetLoadBalancer();

        // Same instance?
        if (lb1 == lb2 && lb2 == lb3) {
            System.out.println("Same instance");
        }

        System.out.println("load balancer 1:  <" + lb1 + ">");
        System.out.println("load balancer 2:  <" + lb2 + ">");
        System.out.println("load balancer 3:  <" + lb3 + ">");

        // All are the same instance. Use lb1 arbitrarily
        // Load balance 15 server requests

        System.out.println("Generating 15 requests...." );
        for (int i = 0; i < 15; i++) {
            System.out.println(lb1.getServer());
        }
    }
}
