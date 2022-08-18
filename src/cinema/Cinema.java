package cinema;
import java.util.Scanner;

public class Cinema {
    private final char[][] table;
    public int rows;
    public int seats;
    static int purchasedTickets;
    static double percentage;
    static int currentIncome;
    static int totalIncome;

    
    private Cinema(int rows, int seats) {
        this.table = new char[rows][seats];
        for (int r = 0; r < rows; r++) {
            for (int s = 0; s < seats; s++) {
                this.table[r][s] = 'S';
            }
        }
    }

      {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        Cinema hall = new Cinema(rows, seats);

        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int command = sc.nextInt();

        while (command != 0) {
            switch (command) {
                case 1:
                    hall.printCinema(rows, seats);
                    break;
                case 2:
                    System.out.println("Enter a row number:");
                    int choosenRow = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int choosenSeatNumber = sc.nextInt();
                    while (!hall.takePlace(choosenRow, choosenSeatNumber, seats, rows)) {
                        System.out.println("Enter a row number:");
                        choosenRow = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        choosenSeatNumber = sc.nextInt();
                    }
                    System.out.println("Ticket price: $" + countTicketPrice(rows, seats, choosenRow));
                    break;
                case 3:
                    System.out.printf("Number of purchased tickets: %d %n", purchasedTickets);
                    System.out.printf("Percentage: %.2f%% %n", getPercentage(rows, seats));
                    System.out.printf("Current income: $%d %n", currentIncome);
                    System.out.printf("Total income: $%d %n", countIncome(rows, seats));
                    break;
            }
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            command = sc.nextInt();
        }
    }

    private void printCinema(int rows, int seats) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int n = 1; n < seats + 1; n++) {
            System.out.print(n + " ");
        }
        System.out.println();
        for (int r = 1; r <= rows; r++) {
            for (int s = 0; s <= seats; s++) {
                if (s == 0) {
                    System.out.print(r + " ");
                } else {
                    System.out.print(table[r - 1][s - 1] + " ");
                }
            }
            System.out.println();
        }
    }

    private static int countTicketPrice(int rows, int seats, int choosenRow) {
        if (rows * seats < 60) {
            return 10;
        } else {
            if (choosenRow <= rows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    private boolean takePlace(int row, int seat, int seats, int rows) {
        if (seat > seats || row > rows) {
            System.out.println("Wrong input!");
            return false;
        } else if (table[row - 1][seat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        } else {
            table[row - 1][seat - 1] = 'B';
            purchasedTickets += 1;
            currentIncome += countTicketPrice(rows, seats, row);
            return true;
        }
    }

    private static double getPercentage(int rows, int seats) {
        int hall = rows * seats;
        return (double) purchasedTickets * 100 / hall;
    }

    public static int countIncome(int rows, int seats) {
        if (rows * seats < 60) {
            totalIncome = rows * seats * 10;
        } else {
            if (rows % 2 == 0) {
                totalIncome = rows / 2 * seats * 10 + rows / 2 * seats * 8;
            } else {
                totalIncome = rows / 2 * seats * 10 + (((1 + rows / 2) * seats) * 8);
            }
        }
        return totalIncome;
    }
}
