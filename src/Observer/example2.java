package Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Analyst {
    void notify(String stockSymbol, float newPrice);
}

class StockMarket {
    private Map<String, Float> stockPrices = new HashMap<>();
    private List<Analyst> analysts = new ArrayList<>();

    public void addStock(String symbol, float price) {
        stockPrices.put(symbol, price);
    }

    public void updateStockPrice(String symbol, float price) {
        stockPrices.put(symbol, price);
        notifyAnalysts(symbol, price);
    }

    public void registerAnalyst(Analyst analyst) {
        analysts.add(analyst);
    }

    public void deregisterAnalyst(Analyst analyst) {
        analysts.remove(analyst);
    }

    private void notifyAnalysts(String stockSymbol, float newPrice) {
        for (Analyst analyst : analysts) {
            analyst.notify(stockSymbol, newPrice);
        }
    }
}

class StockAnalyst implements Analyst {
    private String name;

    public StockAnalyst(String name) {
        this.name = name;
    }

    @Override
    public void notify(String stockSymbol, float newPrice) {
        System.out.println("Analyst " + name + ": Stock " + stockSymbol + " is now priced at " + newPrice);
        // Additional logic for recommendations can be added here
    }
}

public class example2 {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        market.addStock("AAPL", 150.50f);
        market.addStock("GOOGL", 2750.75f);

        StockAnalyst analyst1 = new StockAnalyst("John Doe");
        StockAnalyst analyst2 = new StockAnalyst("Jane Smith");

        market.registerAnalyst(analyst1);
        market.registerAnalyst(analyst2);

        market.updateStockPrice("AAPL", 152.45f);  // Both analysts will be notified of the price change.
        market.updateStockPrice("GOOGL", 2775.00f);  // Both analysts will be notified of the price change.
    }
}



/*
Question: Implement the Observer pattern to simulate a stock market where multiple investment analysts (observers) watch the changes in stock prices of various companies. Whenever a stock price changes, the analysts should be notified to adjust their recommendations accordingly.

Requirements:

Define a StockMarket class that can register, deregister, and notify analysts when stock prices change.
Create an Analyst interface with a notify method.
Implement a StockAnalyst class that observes changes in the stock market and updates its investment strategy based on these changes.
Use the Observer pattern to ensure the analysts are informed of price changes in real-time.
 */