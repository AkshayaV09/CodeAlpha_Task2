import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {
    static class Stock {
        String symbol;
        double price;

        // Constructor
        public Stock(String symbol, double price) {
            this.symbol = symbol;
            this.price = price;
        }
    }
    static class Portfolio {
        Map<String, Integer> stockHoldings = new HashMap<>();
        double cashBalance;

        // Constructor
        public Portfolio(double initialCash) {
            this.cashBalance = initialCash;
        }

        // Deposit cash into the portfolio
        public void depositCash(double amount) {
            this.cashBalance += amount;
        }

        // Withdraw cash
        public void withdrawCash(double amount) {
            if (this.cashBalance >= amount) {
                this.cashBalance -= amount;
            } else {
                System.out.println("Not enough funds.");
            }
        }

        // Buy stock
        public void buyStock(String symbol, int quantity, double price) {
            double totalCost = price * quantity;
            if (this.cashBalance >= totalCost) {
                stockHoldings.put(symbol, stockHoldings.getOrDefault(symbol, 0) + quantity);
                withdrawCash(totalCost);
                System.out.println("Bought " + quantity + " shares of " + symbol + ".");
            } else {
                System.out.println("Not enough cash to buy " + quantity + " shares.");
            }
        }

        // Sell stock
        public void sellStock(String symbol, int quantity, double price) {
            if (stockHoldings.containsKey(symbol) && stockHoldings.get(symbol) >= quantity) {
                stockHoldings.put(symbol, stockHoldings.get(symbol) - quantity);
                depositCash(price * quantity);
                System.out.println("Sold " + quantity + " shares of " + symbol + ".");
            } else {
                System.out.println("Not enough shares to sell.");
            }
        }

        // Show portfolio
        public void showPortfolio() {
            System.out.println("\nPortfolio:");
            System.out.println("Cash balance: $" + cashBalance);
            for (String symbol : stockHoldings.keySet()) {
                System.out.println(symbol + ": " + stockHoldings.get(symbol) + " shares");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Market Data
        Map<String, Stock> marketData = new HashMap<>();
        marketData.put("AAPL", new Stock("AAPL", 150.00));
        marketData.put("GOOG", new Stock("GOOG", 2800.00));
        marketData.put("AMZN", new Stock("AMZN", 3400.00));

        // User's Portfolio
        Portfolio portfolio = new Portfolio(10000.00);  // $10,000 starting cash

        while (true) {
            System.out.println("\nCA/MA19495");
            System.out.println("\nStock Trading Platform ");
            portfolio.showPortfolio();
            System.out.println("\nMarket Data:");
            for (Stock stock : marketData.values()) {
                System.out.printf("%s: $%.2f\n", stock.symbol, stock.price);
            }

            System.out.println("\nChoose an action:");
            System.out.println("1. Buy stock");
            System.out.println("2. Sell stock");
            System.out.println("3. View portfolio");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Buy stock
                    System.out.print("Enter stock symbol (AAPL/GOOG/AMZN): ");
                    String buySymbol = scanner.next().toUpperCase();
                    if (!marketData.containsKey(buySymbol)) {
                        System.out.println("Invalid stock symbol.");
                        break;
                    }
                    System.out.print("Enter quantity to buy: ");
                    int buyQuantity = scanner.nextInt();
                    portfolio.buyStock(buySymbol, buyQuantity, marketData.get(buySymbol).price);
                    break;

                case 2:
                    // Sell stock
                    System.out.print("Enter stock symbol (AAPL/GOOG/AMZN): ");
                    String sellSymbol = scanner.next().toUpperCase();
                    if (!marketData.containsKey(sellSymbol)) {
                        System.out.println("Invalid stock symbol.");
                        break;
                    }
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    portfolio.sellStock(sellSymbol, sellQuantity, marketData.get(sellSymbol).price);
                    break;

                case 3:
                    // View portfolio
                    portfolio.showPortfolio();
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting platform...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}


