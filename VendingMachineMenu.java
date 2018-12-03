import java.util.Scanner;

/**
 * A menu from the vending machine.
 */
class VendingMachineMenu {
	private Scanner in;
	private Operator currentOperator;

	/**
	 * Constructs a VendingMachineMenu object
	 */
	public VendingMachineMenu() {
		in = new Scanner(System.in);
	}

	/**
	 * Runs the vending machine system.
	 *
	 * @param machine the vending machine
	 */
	public void run(VendingMachine machine) {
		boolean more = true;
		Coin[] coins = machine.getAcceptableCoins();
		while (more) {
			System.out.println("S)how products I)nsert coin B)uy L)ogin Q)uit");
			String command = in.nextLine().toUpperCase();
			if (command.equals("S")) {
				System.out.println(machine.showProducts());
			} else if (command.equals("I")) { //allows one coin be inserted at a time
				machine.addCoin((Coin) getChoice(coins));
			} else if (command.equals("L")) { //allows one coin be inserted at a time
				login(machine);
			} else if (command.equals("B")) {
				try {
					Product p = (Product) getChoice(machine.getProductTypes());
					machine.buyProduct(p);
					System.out.println("Purchased: " + p);
				} catch (VendingException ex) {
					System.out.println(ex.getMessage());
				} catch (StringIndexOutOfBoundsException ex) {
					System.out.println("No products available");
				}
			} else if (command.equals("Q")) {
				machine.saveAllToFiles();
				more = false;
			}
		}
	}

	private void adminMenu(VendingMachine machine) {
		boolean more = true;
		while (more) {
			System.out.println("S)how products " + (currentOperator.canAddProduct() ? "A)dd product " : "") +
					(currentOperator.canRemove() ? "R)emove coins " : "") +
					(currentOperator.canCreateAccount() ? "C)reate account " : "") +
					"B)ack");
			String command = in.nextLine().toUpperCase();
			if (command.equals("S")) {
				System.out.println(machine.showProducts());
			} else if (command.equals("R") && currentOperator.canRemove()) {
				System.out.println("Removed: " + machine.removeMoney());
			} else if (command.equals("A") && currentOperator.canAddProduct()) {
				System.out.println("Description:");
				String description = in.nextLine();
				System.out.println("Price:");
				double price = in.nextDouble();
				System.out.println("Quantity:");
				int quantity = in.nextInt();
				in.nextLine(); // read the new-line character
				machine.addProduct(new Product(description, price), quantity);
			} else if (command.equals("C") && currentOperator.canCreateAccount()) {
				//todo create account stuff
				String permissions = "";
				System.out.println("Type: ");
				String type = in.nextLine();
				System.out.println("Code: ");
				String code = in.nextLine();
				System.out.println("Can they create accounts? Y)es / N)o");
				String tempCommand = in.nextLine().toUpperCase();
				if(tempCommand.equals("Y"))
					permissions += 1;
				else
					permissions += 0;

				System.out.println("Can they add products accounts? Y)es / N)o");
				tempCommand = in.nextLine().toUpperCase();
				if(tempCommand.equals("Y"))
					permissions += 1;
				else
					permissions += 0;

				System.out.println("Can they remove money? Y)es / N)o");
				tempCommand = in.nextLine().toUpperCase();
				if(tempCommand.equals("Y"))
					permissions += 1;
				else
					permissions += 0;

				in.nextLine(); // read the new-line character
				machine.addOperator(type, code, permissions);
			} else if (command.equals("B")) {
				more = false;
			}
		}
	}

	private void login(VendingMachine machine) {
		System.out.println("User: ");
		String type = in.nextLine();
		System.out.println("Code: ");
		String code = in.nextLine();
		Operator op = Validation.loginCheck(machine, type, code);

		if (op == null) {
			System.out.println("Incorrect login details");
		} else {
			currentOperator = op;
			System.out.println("Successfully logged in as: " + type);
			adminMenu(machine);
		}
	}

	private Object getChoice(Object[] choices) throws StringIndexOutOfBoundsException {
		while (true) {
			char c = 'A';
			for (Object choice : choices) {
				System.out.println(c + ") " + choice);
				c++;
			}
			String input = in.nextLine();
			int n = input.toUpperCase().charAt(0) - 'A';
			if (0 <= n && n < choices.length)
				return choices[n];
		}
	}


}