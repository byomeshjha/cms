package com.lms.test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class TestTest {
	
	public static class User {
		
		Integer seq;
		String fn;
		String ln;
		Integer age;
		
		public User(Integer seq, String fn, String ln, Integer age) {
			this.seq = seq;
			this.fn = fn;
			this.ln = ln;
			this.age = age;
		}

		public Integer getSeq() {
			return seq;
		}

		public void setSeq(Integer seq) {
			this.seq = seq;
		}

		public String getFn() {
			return fn;
		}

		public void setFn(String fn) {
			this.fn = fn;
		}

		public String getLn() {
			return ln;
		}

		public void setLn(String ln) {
			this.ln = ln;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
	}

	
	
	public static void main(String[] args) {
		
		List<User> users = Arrays.asList(
			      new TestTest.User(1, "Steve", "Vai", 40),
			      new TestTest.User(4, "Joe", "Smith", 32),
			      new TestTest.User(3, "Steve", "Johnson", 57),
			      new TestTest.User(9, "Mike", "Stevens", 18),
			      new TestTest.User(10, "George", "Armstrong", 24),
			      new TestTest.User(2, "Jim", "Smith", 40),
			      new TestTest.User(8, "Chuck", "Schneider", 34),
			      new TestTest.User(5, "Jorje", "Gonzales", 22),
			      new TestTest.User(6, "Jane", "Michaels", 47),
			      new TestTest.User(7, "Kim", "Berlie", 60)
			    );
		
		Integer[] a1 = {1, 5, 8, 0, 12, 1, 1, 7};
		Stream<Integer> s1 = Arrays.stream(a1).filter(i -> i.intValue() > 2);
		
		System.out.println(s1.count());
		
		String[] myArray = { "this", "is", "a", "sentence" };
		String result = Arrays.stream(myArray).reduce("", (a,b) -> a + b);
		System.out.println(result.toUpperCase());
		
		System.out.println("==== Asc =====");
		Collections.sort(users, (p1, p2) -> p1.getLn().compareTo(p2.getLn()));
		for(User p : users) {
			System.out.println(p.getLn());
		}
		System.out.println("==== Desc =====");
		Collections.sort(users, (p1, p2) -> p2.getLn().compareTo(p1.getLn()));
		for(User p : users) {
			System.out.println(p.getLn());
		}
		
		double average = users.parallelStream().mapToInt(u -> u.age).average().getAsDouble();

	    System.out.println("NEWWAY Average User Age: " + average);
	    
	    Calendar c = Calendar.getInstance();
	    System.out.println(c.get(Calendar.DAY_OF_MONTH));
	}

}
