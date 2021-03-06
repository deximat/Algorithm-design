package com.codlex.algorithms.client;

import java.util.Arrays;

public class Command {

	public static Command produce(String commandLine) {
		final String[] commandSplitted = commandLine.split(SEPARATOR);

		if (commandSplitted.length == 0) {
			return null;
		}

		return new Command(extractKey(commandSplitted),
				extractParams(commandSplitted));
	}

	public Command(String key, String[] params) {
		this.key = key;
		this.params = params;
	}

	public String getKey() {
		return this.key;
	}

	public String getParam(int index) {
		return this.params[index];
	}

	private static final String SEPARATOR = " ";

	private final String key;
	private final String[] params;

	private static String[] extractParams(String[] commandSplitted) {
		final String[] params;
		if (commandSplitted.length >= 2) {
			params = Arrays.copyOfRange(commandSplitted, 1,
					commandSplitted.length);
		} else {
			params = null;
		}
		return params;
	}

	private static String extractKey(String[] commandSplitted) {
		return commandSplitted[0];
	}

}
