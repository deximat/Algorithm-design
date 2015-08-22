package com.codlex.javaWTF;

/**
 * Constructor must be called as first statement. So it is impossible to write
 * condition where it would stop calling constructor, which means constructor
 * recursion is impossible ( Thank God! :) ).
 */
public class RecursiveConstructor {

	public RecursiveConstructor(int count) {
		// this(count--);
	}
}
