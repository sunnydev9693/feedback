package com.feedback.feedback_analytics;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		
		String input = "hello world";
		List<Integer> intList = List.of(825,25,25,25,58,89,88,58,44,335,56);
		List<String> stringList = Arrays.asList("test","test1","test","test1","test54321","test4321","test321","test21",null);
		List<List<String>> flatList = List.of(List.of("test1","test","test1"),
				List.of("test","test1","test"),List.of("test54321","test4321","test321"));
		
		List<Character> result = input.chars().mapToObj(e -> (char)e).distinct().collect(Collectors.toList());
		System.out.println(result);
		
		
	}
}



