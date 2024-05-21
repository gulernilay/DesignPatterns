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
/*
class LoadBalancer {
    // Thread Safe Load Balancer with Double Checked Locking
    public static LoadBalancer GetLoadBalancer() {
        String tname = Thread.currentThread().getName() + ":";
        if (instance == null) { // No need to protect instance READ access only.
            lock.lock();
            System.out.println(tname + " acquired lock");
            try {
                if (instance == null)
                    instance = new LoadBalancer();
            } finally {
                lock.unlock();
                System.out.println(tname + " released lock");
            }
        }
        return instance;
    }

    public static LoadBalancer GetLoadBalancerNoDoubleCheck() {
        String tname = Thread.currentThread().getName() + ":";
        lock.lock();
        System.out.println(tname + " acquired lock");
        try {
            if (instance == null)
                instance = new LoadBalancer();
        } finally {
            lock.unlock();
            System.out.println(tname + " released lock");
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
    public static void initInstance() { instance = null;}
    private static LoadBalancer instance = null;
    private ArrayList<String> servers = new ArrayList<String>();
    private static final Lock lock = new ReentrantLock();
} // class LoadBalancer

class MyRunnable implements Runnable {
    private char type;
    MyRunnable(char type) {	this.type = type; }
    public void run() {
        String tname = Thread.currentThread().getName() + ":";
        LoadBalancer lb = null;
        if (type == 'D') lb = LoadBalancer.GetLoadBalancer();
        if (type == 'N') lb = LoadBalancer.GetLoadBalancerNoDoubleCheck();
        System.out.println(tname + " load balancer  <" + lb + ">");
    }
}
public class Exercise3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("START NO DOUBLE CHECKED LOCKING");
        Thread t1 = new Thread(new MyRunnable('N'), "t1");
        Thread t2 = new Thread(new MyRunnable('N'), "t2");
        Thread t3 = new Thread(new MyRunnable('N'), "t3");
        // We have to create a scenario in which threads are starting
        // sequentially in order show the trade offs of double checking
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();

        LoadBalancer.initInstance(); // For this new test set it to null;
        System.out.println("\nSTART DOUBLE CHECKED LOCKING");
        t1 = new Thread(new MyRunnable('D'), "t1");
        t2 = new Thread(new MyRunnable('D'), "t2");
        t3 = new Thread(new MyRunnable('D'), "t3");
        // We have to create a scenario in which threads are starting sequentially
        // in order show the real advantage of double checking
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
    }
}
*/