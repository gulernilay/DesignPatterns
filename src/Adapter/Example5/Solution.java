package Adapter.Example5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// PrimeFinder interface
interface PrimeFinder {
    List<Integer> sievePrimes(int limit);
}

// SimplePrimeFinder class (Adaptee)

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

// Class Adapter for SimplePrimeFinder :SimplePrimeFinder sınıfının findPrimes metodunu, PrimeFinder arayüzündeki sievePrimes metoduna uydurmak için kullanılıyor.Yani, PrimeFinder arayüzü üzerinden kullanılabilen sievePrimes metodunu kullanmak için SimplePrimeFinder sınıfı adapte ediliyor
class PrimeFinderAdapter implements PrimeFinder {
    private SimplePrimeFinder simplePrimeFinder;

    public PrimeFinderAdapter(SimplePrimeFinder simplePrimeFinder) {
        this.simplePrimeFinder = simplePrimeFinder;
    }

    @Override
    public List<Integer> sievePrimes(int limit) {
        List<Integer> primes = new ArrayList<>();
        String primesString = simplePrimeFinder.findPrimes(limit);
        String[] primesArray = primesString.split("\\s+");
        for (String prime : primesArray) {
            primes.add(Integer.parseInt(prime));
        }
        return primes;
    }
}

// Testing the prime finders
public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        SimplePrimeFinder simplePrimeFinder = new SimplePrimeFinder();
        PrimeFinderAdapter adapter = new PrimeFinderAdapter(simplePrimeFinder); // Adapt SimplePrimeFinder to PrimeFinder
        System.out.println("Please enter a number to find prime numbers from 0 to your number: ");
        int limit = scan.nextInt();
        System.out.println("Primes found by Simple Prime Finder (using Adapter): " + adapter.sievePrimes(limit));
    }
}