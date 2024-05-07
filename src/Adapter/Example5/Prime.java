package Adapter.Example5;

import java.util.Scanner;

/*
class SimplePrimeFinder {
    public String findPrimes(int limit) {
        String primes = "";
        for (int number = 2; number <= limit; number++) {
            if (isPrime(number)) {
                primes = primes + " " + number;
            }
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
*/


// Testing the prime finders
public class Prime {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SimplePrimeFinder simplePrimeFinder = new SimplePrimeFinder();
        System.out.println("Please enter a number to find prime numbers from 0 to your number: ");
        int limit = scan.nextInt();
        System.out.println("Primes found by Simple Prime Finder: " + simplePrimeFinder.findPrimes(limit));
    }
}