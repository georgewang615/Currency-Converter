package SOFT2412A1;

import java.util.Date;
import java.util.Scanner;

public class InputHandler {
	private Scanner newScan;

	public InputHandler(Scanner newScan) {
		this.newScan = newScan;
	}

	public int getMenuOption(String message, int numOptions) {
		while (true) {
			System.out.print(message);
			String prompt = newScan.nextLine();
			try{
				int selection = Integer.parseInt(prompt);
				if (selection > 0 && selection <= numOptions) {
					return selection;
				} else {
					System.out.println("Invalid menu selection");
				}
			}
			catch(Exception e){
				System.out.println("Invalid Menu Selection");
			}
		}
	}

	public double getInputDouble(String message) {
		while (true) {
			System.out.print(message);
			try {
				double input = Double.parseDouble(newScan.nextLine());
				return input;
			} catch (Exception e) {
				System.out.println("Please enter a number.\n");
			}
		}
	}

	public Date getInputDate(String message) {
		while (true) {
			System.out.print(message);
			String dateString = this.newScan.nextLine();
			Date date = DateHandler.toDate(dateString);
			if (date != null) {
				return date;
			}
			System.out.println("Please enter a valid date (yyyy-MM-dd).\n");
		}
	}

	public Currency getInputCurrency(String currentDate, String message, ExchangeBook book) {
		while (true) {
			System.out.print(message);
			String currencyString = this.newScan.nextLine();
			Currency curr = book.getCurrency(currentDate, currencyString);
			if (curr != null) {
				return curr;
			}
			System.out.printf("This currency is not in the exchange book for %s, please try again.\n\n", currentDate);
		}
	}

}
