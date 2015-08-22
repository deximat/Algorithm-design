package com.codlex.algorithms.client;

import java.util.Scanner;

public abstract class Client<TypeToTest> {
	
	protected TypeToTest object;
	private final Scanner in = new Scanner(System.in);
	
	public Client() {
		this.object = createObject();
	}
	
	protected final void execute() {
		System.out.println("Waiting for next command:");
		while (this.in.hasNextLine()) {
			try {
				final Command command = Command.produce(this.in.nextLine());
				if (command != null) {
					System.out.println(process(this.object, command));
				} else {
					System.out.println("Bad command, try again.");
				}
			} catch (Exception e) {
				System.out.println("Bad command, try again.");
				e.printStackTrace();
			}
		}
	}
	
	public abstract String process(final TypeToTest object, final Command command);

	protected abstract TypeToTest createObject();
}
