package com.library.library_management;

import java.util.*;
import java.util.stream.Collectors;

public class LLLL {
    public static void main(String ar[]) {
        Integer[] n = {1, 2, 3, 4, 5, 6, 7};

        //partition wrt to predicate
        Map<Boolean, List<Integer>> collect = Arrays.stream(n).collect(Collectors.partitioningBy(i -> Integer.valueOf(i) % 2 == 0));

        //group by remainder by 4
        Map<Integer, List<Integer>> collect1 = Arrays.stream(n).collect(Collectors.groupingBy(i -> i % 4));

        Optional<Integer> first = Arrays.stream(n).sorted().skip(99).findFirst();
        if (first.isEmpty()) {
            System.out.println("Not an answer");
        } else {
            System.out.println(first.get());
        }
        class Transaction {
            String date;
            int s;

            public Transaction(String date, int s) {
                this.date = date;
                this.s = s;
            }
        }

        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300),
                new Transaction("2022-01-02", 400),
                new Transaction("2022-01-03", 500)
        );

        transactions.stream().collect(Collectors.groupingBy(transaction -> transaction.date, Collectors.summingInt(transaction -> transaction.s)));


        //remove duplicate
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 6, 5);
        numbersWithDuplicates.stream().collect(Collectors.toSet());


        class Person {
            String s;
            int age;

            public Person(String s, int age) {
                this.s = s;
                this.age = age;
            }
        }
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );

        persons.stream().collect(Collectors.summingInt(person -> person.age));


        //50 questions


        List<Integer> integers = List.of(2, 4, 1, 5, 2, 7);

        //        Filter Even Numbers
        integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());

//        Square of Each Element:
        integers.stream().map(i -> i * i).collect(Collectors.toList());

//        Count Vowels:
        List<String> strings = List.of("dqeaef", "sve", "vrv", "vrvrf4wt");
        strings.stream().collect(Collectors.toMap(s -> s, s -> s.chars().filter(c -> "aeiou".indexOf(c) != -1).count()));

//        Concatenate Strings: Create a method that takes a list of strings and returns a single string that is the concatenation of all the strings, separated by comma
        strings.stream().collect(Collectors.joining(","));

//        Find Longest String: Write a method that finds the longest string in a list of strings using streams.
        Optional<String> longest = strings.stream().reduce((s1, s2) -> s1.length() >= s2.length() ? s1 : s2);

//    Sum of Odd Numbers: Given a list of integers, return the sum of all odd numbers using streams.
        integers.stream()
                .filter(f -> f % 2 != 0)  // Keep only odd numbers
                .reduce(0, Integer::sum);  // Sum them

//        FlatMap Example: Given a list of lists of integers, write a method that flattens the structure into a single list of integers.
        List<List<Integer>> lists = new ArrayList<>();
        lists.stream().flatMap(List::stream).collect(Collectors.toList());

//        Convert to Uppercase: Write a method that converts a list of strings to uppercase using streams
        strings.stream().map(s->s.toUpperCase()).collect(Collectors.toList());

//        Map to Lengths: Write a method that takes a list of strings and returns a list of their lengths using streams.
        List<Integer> collect2 = strings.stream().map(s -> s.length()).collect(Collectors.toList());

//        Count Unique Characters: Given a string, write a method that counts the number of unique characters using streams.
        strings.stream().collect(Collectors.toMap(a->a, s->s.chars().map(c->c).distinct().count()));

//        s = "Hello World High"
        String s = "Hello World High";
        String collect3 = Arrays.stream(s.split(" ")).map(t -> reverse(t)).collect(Collectors.joining(" "));
        System.out.println(collect3);

//        Find First Matching Element: Write a method that finds the first integer greater than a specified value in a list using streams.
        integers.stream().sorted();

//        . Implement a method to calculate the Fibonacci sequence using Java streams:
        
    }

    static  String reverse(String s){
        StringBuffer stringBuffer = new StringBuffer(s);
        return stringBuffer.reverse().toString();

    }
}
