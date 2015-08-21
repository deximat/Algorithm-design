package com.codlex.util;

import java.util.Iterator;

public class IterableUtils {
	private static final char LIST_START_CHARACTER = '[';
	private static final char LIST_END_CHARACTER = ']';
	private static final String SEPARATOR = ", ";

	public static String toString(final Iterable<?> iterable) {
		StringBuilder builder = new StringBuilder();
		builder.append(LIST_START_CHARACTER);

		final Iterator<?> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			final Object object = iterator.next();

			if (object instanceof Iterable) {
				builder.append(toString((Iterable<?>) object));
			}

			builder.append(object.toString());

			if (iterator.hasNext()) {
				builder.append(SEPARATOR);
			}
		}

		builder.append(LIST_END_CHARACTER);

		return builder.toString();
	}
}
