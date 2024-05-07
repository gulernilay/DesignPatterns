package Adapter.Example5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
interface PrimeFinder {
    List<Integer> sievePrimes(int limit);
}
*/

class SievePrime implements PrimeFinder {
    public List<Integer> sievePrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();

        for (int number = 2; number * number <= limit; number++) {
            if (isPrime[number]) {
                for (int multiple = number * number; multiple <= limit; multiple += number) {
                    isPrime[multiple] = false;
                }
            }
        }

        for (int number = 2; number <= limit; number++) {
            if (isPrime[number]) {
                primes.add(number);
            }
        }

        return primes;
    }
}
