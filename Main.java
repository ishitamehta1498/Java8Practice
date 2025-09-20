
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Most repeated element");
        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        String mostRepeated = items.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Most Repeated: " + mostRepeated);

        System.out.println("2nd longest word in string");
        String longest1="this is an example string with some very long words like extraordinary";
        String s4 = Arrays.stream(longest1.split("\\s+"))
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed())
              //  .sorted((a,b)->b.length()-a.length())
              //  .sorted((w1,w2)->w1.length()>w2.length()?-1:w1.length()<w2.length()?1:0)
                .skip(1).findFirst().orElse(null);
        System.out.println(s4);

        System.out.println("If words have the same length, sort in reverse alphabetical order.");
        longest1="happy world";
        String secondLongest = Arrays.stream(longest1.split("\\s+"))
                .distinct()
                .sorted(Comparator.comparingInt(String::length).reversed()
                        .thenComparing(Comparator.reverseOrder()))
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println(secondLongest);

        System.out.println("count occurrence of each character");
        String str = "ilovejavaa";
        Map<String, Long> collect = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);

        //or
        Map<Character, Long> freqMap = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(freqMap);

        System.out.println("additionally sort by character-alphabetically");
        freqMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry-> System.out.println(entry.getKey()+"="+entry.getValue()));

        System.out.println("additionally sort by frequency-descending");
        freqMap.entrySet().stream()
                .sorted(Map.Entry.<Character,Long>comparingByValue().reversed())
                .forEach(entry-> System.out.println(entry.getKey()+"="+entry.getValue()));

        System.out.println("additionally sort by frequency-descending and then sort by character-alphabetically");
        freqMap.entrySet().stream()
                .sorted(Map.Entry.<Character,Long>comparingByValue().reversed()
                        .thenComparing(Map.Entry.comparingByKey()))
                .forEach(entry-> System.out.println(entry.getKey()+"="+entry.getValue()));
        
        //freq of each word
        String ss = "  this is the tour of the place called Paris and place called Rome and Place Tokyo";
        Map<String, Long> freq = Arrays.stream(ss.trim().split("\\s+"))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.println(freq);

        System.out.println("find all duplicate characters in java string");
        List<String> duplicates = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey) //.map(x->x.getKey())
                .collect(Collectors.toList());
        System.out.println(duplicates);
        //or
        Set<Character> set1 = new HashSet<>();
        Set<Character> duplicates3 = str.chars()
                .mapToObj(c->(char)c)
                .filter(x -> !set1.add(x)).collect(Collectors.toSet());
        System.out.println(duplicates3);

        System.out.println("find all unique elements in java string");
        List<Character> unique = str.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(unique);

        System.out.println("first non-repeating character from given string");
        Character firstNonRepeating = str.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First non-repeating char: " + firstNonRepeating);

        System.out.println("first repeating character from given string");
        String str4 = "ilovejava";
        Character firstRepeating = str.chars()
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("First repeating char: " + firstRepeating);

        System.out.println("find second highest number in array");
        int[] nums = {5, 9, 2, 8, 11, 21, 1};
        Integer secondLargest = Arrays.stream(nums).boxed()
                .distinct()
                //.sorted((a,b)->b-a)
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().orElse(null);
        System.out.println("second largest: " + secondLargest);

        System.out.println("find second smallest number in array");
        int[] num = {5, 9, 2, 8, 11, 21, 1, 1};
        Integer secondSmallest = Arrays.stream(num).boxed()
                .distinct()
                .sorted()
                .skip(1)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("There is no second smallest element"));
        System.out.println("second smallest: " + secondSmallest);

        System.out.println("find longest string from given array");
        String[] str1 = {"Hello", "Hi", "Ishita", "nice day", "Good"};
        String longest = Arrays.stream(str1)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get();
        System.out.println("longest string: " + longest);
        //or
        String s23 = Arrays.stream(str1).sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst().orElse(null);
        System.out.println(s23);
        //or
        String s33 = Arrays.stream(str1).max(Comparator.comparingInt(String::length)).orElse(null);
        System.out.println(s33);

        System.out.println("find length of longest string in given array");
        int maxlength = Arrays.stream(str1)
                .mapToInt(String::length)
                .max()
                .orElse(0);
        System.out.println("maxlength: " + maxlength);
        //or
        System.out.println(Arrays.stream(str1)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get()
                .length());

        System.out.println("find all array elements starting with 1");
        List<String> startWith1 = Arrays.stream(nums).boxed()
                //.map(x -> x + "")
                .map(String::valueOf)
                .filter(x -> x.startsWith("1"))
                .toList();
        System.out.println("Elements starting with 1:" + startWith1);

        System.out.println("print numbers divisible by 3");
        List<String> list = Arrays.asList("3", "6", "8", "14", "15");
        list.stream().mapToInt(Integer::parseInt)
                .filter(nu -> nu % 3 == 0)
                .forEach(System.out::println);

        System.out.println("String.join()");
        List<String> str3 = Arrays.asList("1", "2", "3", "4");
        String re = String.join("-", str3);
        System.out.println(re);

        System.out.println("skip & limit - print 2-9 from given list having 1-10");
        //skip - for eg if you are reading csv file, u will have to skip header
        List<Integer> collection = IntStream.rangeClosed(1, 10).boxed()
                .skip(1).limit(8).collect(Collectors.toList());
        System.out.println(collection);

        System.out.println("given 2 array of integers, find common elements between them");
        int[] arr1 = {1, 2, 6, 3, 7};
        int[] arr2 = {5, 2, 7, 8, 1};
        List<Integer> common = Arrays.stream(arr1)
                .filter(num1 -> Arrays.stream(arr2).anyMatch(num2 -> num2 == num1)).boxed()
                .toList();
        System.out.println("Common elements: " + common);

        System.out.println("Find common elements between two lists");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);
        List<Integer> commonElements = list1.stream()
                .filter(list2::contains)
                .toList();
        System.out.println(commonElements);


        System.out.println("reverse array in-place");
        int[] num1 = {1, 6, 3, 2, 9, 0};
        IntStream.range(0, num1.length / 2)
                .forEach(i -> {
            int temp = num1[i];
            num1[i] = num1[num1.length - i - 1];
            num1[num1.length - i - 1] = temp;
        });
        System.out.println(Arrays.toString(num1));

        System.out.println("sum of all elements");
        int sum = Arrays.stream(num1).reduce(0, (sums, i) -> sums + i);
        System.out.println("Sum: " + sum);
        //or
        List<Integer> list9 = Arrays.asList(1, 2, 7, 3, 5, 4);
        int sum1 = list9.stream()
                .mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum1);

        System.out.println("max element in list");
        Integer max = list9.stream().max(Comparator.naturalOrder()).get();
        System.out.println("Maximum: " + max);
        //or
        Integer max11 = list9.stream().sorted(Comparator.reverseOrder()).findFirst().get();
        System.out.println(max11);

        System.out.println("count number of strings starting with character c");
        List<String> fruits = Arrays.asList("cherry", "berry", "banana", "apple", "coconut");
        long total = fruits.stream().filter(s -> s.startsWith("c")).count();
        System.out.println(total);

        System.out.println("convert list to uppercase");
        List<String> fruitList = fruits.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(fruitList);

        System.out.println("filter even numbers");
        List<Integer> even = list9.stream().filter(x -> x % 2 == 0).toList();
        System.out.println(even);

        System.out.println("avg of floating numbers");
        List<Double> nums3 = Arrays.asList(1.1, 1.2, 1.3, 1.4);
        double average = nums3.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        System.out.println("Average: " + average);

        System.out.println("concatenate strings");
        String collect1 = fruits.stream().collect(Collectors.joining("-","[","]"));
        System.out.println(collect1);

        System.out.println("remove duplicate elements");
        List<Integer> list11 = Arrays.asList(1, 2, 7, 1, 3, 5, 4, 4, 0);
        List<Integer> distinct = list11.stream().distinct().toList();
        System.out.println(distinct);

        System.out.println("Given a list of integers, separate odd and even numbers");
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
        Map<Boolean, List<Integer>> oddEvenNumbersMap =
                listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        List<Integer> evenN = oddEvenNumbersMap.get(true);
        List<Integer> oddN=oddEvenNumbersMap.get(false);
        System.out.println(evenN);
        System.out.println(oddN);

        System.out.println("find the most repeated element in an array");
        List<String> listOfStrings = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil",
                "Pen", "Note Book", "Pencil");
        Map<String, Long> freqmap = listOfStrings.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        String keymax= freqmap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst()
                //  .max(Map.Entry.comparingByValue())
                //  .max(Comparator.comparingLong(Map.Entry::getValue))
                .get().getKey();
        System.out.println(keymax);

        System.out.println("Given a list of strings, find out those strings which start with a number?");
        List<String> listOfStrings1 = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
        listOfStrings1.stream().filter(a -> Character.isDigit(a.charAt(0))).forEach(System.out::println);

        System.out.println("Print duplicate characters in a string");
        String inputString = "Java Concept Of The Day";
        Set<String> set2=new HashSet<>();
        Set<String> re1 = Arrays.stream(inputString.toLowerCase().replaceAll("\\s+", "")
                        .split(""))
                .filter(el -> !set2.add(el))
                .collect(Collectors.toSet());
        System.out.println(re1);

        System.out.println("Reverse each word of a string using Java 8 streams");
        String stri = "Java Concept Of The Day";
        String reversedStr = Arrays.stream(stri.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println(reversedStr);

        System.out.println("check if two strings are anagrams or not");
        String s01 = "RaceCar";
        String s02 = "CarRace";
        s01 = Arrays.stream(s01.toLowerCase().split("")).sorted().collect(Collectors.joining());
        s02 = Arrays.stream(s02.toLowerCase().split("")).sorted().collect(Collectors.joining());
        if (s01.equals(s02)) {
            System.out.println("Two strings are anagrams");
        }
        else {
            System.out.println("Two strings are not anagrams");
        }
        //or
        boolean isAnagram = s01.chars().sorted().boxed().toList()
                .equals(s02.chars().sorted().boxed().toList());
        System.out.println(isAnagram);

        System.out.println("sum of all digits");
        int i = 15623;
        Integer sumOfDigits = Arrays.stream(String.valueOf(i).split(""))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println(sumOfDigits);

        System.out.println("check string is palindrome");
        String palindrome="madam";
        boolean isPalindrome=IntStream.range(0,palindrome.length()/2)
                .allMatch(ii->palindrome.charAt(ii)==palindrome.charAt(palindrome.length()-1-ii));
        System.out.println(isPalindrome);

        System.out.println("concatenate all strings");
        List<String> strs=Arrays.asList("Hello"," ","World","!");
        String collect2 = strs.stream().collect(Collectors.joining());
        System.out.println(collect2);
        //or
        String collect21 = String.join("", strs);
        System.out.println(collect21);

        System.out.println("count occurrence of a character in list of strings");
        List<String> fruitsList =Arrays.asList("apple","banana ","cherry","strawberry");
        char target='a';
        long count1 = fruitsList.stream()
                .flatMapToInt(String::chars) //// Convert each string to an IntStream of character codes
                .mapToObj(t->(char)t)
                .filter(t -> t == target).count();
        System.out.println("count of "+target+": "+count1);

        System.out.println("factorial of 5");
        int reduce = IntStream.rangeClosed(1, 5)
                .reduce(1, (fact, numi) -> fact * numi);
        System.out.println(reduce);

        System.out.println("frequency of each word in list of strings");
        List<String> list3 = Arrays.asList("apple banana apple", "banana cherry", "Apple banana cherry");
        Map<String, Long> collect3 = list3.stream()
                .flatMap(st -> Arrays.stream(st.split("\\s+")))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.println(collect3);

        System.out.println("distinct characters");
        List<String> strs2=Arrays.asList("apple","banana","cherry","strawberry");
        List<Character> collect4= strs2.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .keySet().stream()
                .toList();
        System.out.println(collect4);

        //or
        Set<Character> collect5 = strs2.stream().flatMapToInt(String::chars)
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toSet());
        System.out.println(collect5);

        System.out.println("generate list from list<list<Integer>>");
        List<List<Integer>> number = new ArrayList<>();
        number.add(Arrays.asList(1, 2));
        number.add(Arrays.asList(3, 4, 5));
        number.add(Arrays.asList(6));
        number.add(Arrays.asList(7, 8));
        List<Integer> flatList = number.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatList);

        System.out.println("Get the 1st Character of all the Strings present in a List<List<Strings>> " +
                "and returning the result in form of a stream.");
        List<List<String>> nestedStrings = Arrays.asList(
                Arrays.asList("apple", "banana", "dates", "cherry"),
                Arrays.asList("mango", "elderberry", "fig", ""),
                Arrays.asList("papaya", "grape", "kiwi")
        );
        List<Character> firstChar = nestedStrings.stream()
                .flatMap(List::stream)
                .filter(s0 -> s0 != null && !s0.isEmpty())
                .map(fruit -> fruit.charAt(0))
                .collect(Collectors.toList());
        System.out.println(firstChar);

        System.out.println("check if all elements matches the condition - say all are even");
        List<Integer> list22 = Arrays.asList(8, 2, 4, 4, 0);
        boolean allEven = list22.stream().allMatch(x -> x % 2 == 0);
        System.out.println("All elements are even: " + allEven);

        System.out.println("find max and min in list of integers");
        List<Integer> li = Arrays.asList(1, 5, 6, 3, 2, 8, 4, 7, 4);
        Integer min1 = li.stream().min(Integer::compareTo).get();
        System.out.println("Minimum: " + min1);
        //or
        Integer i3 = li.stream().min((a, b) -> a - b).get();
        System.out.println("Minimum: " + i3);
        //or
        Integer min3 = li.stream().reduce((i1, i2) -> i1 < i2 ? i1 : i2).get();
        System.out.println("Minimum: " + min3);
        //or
        int min2 = li.stream().mapToInt(Integer::intValue).min().getAsInt();
        System.out.println("Minimum: " + min2);

        Integer max1 = li.stream().max(Integer::compareTo).get();
        System.out.println("Maximum: " + max1);

        System.out.println("find max in array");
        int[] arr5 = {1, 4, 2, 1};
        Arrays.stream(arr5).max().ifPresent(System.out::println);

        System.out.println("sort elements in list in desc order");
        List<Integer> sorted = li.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println(sorted);

        System.out.println("sort elements in list in asc order");
        List<Integer> sorted1 = li.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted1);

        System.out.println("check array contains duplicate value/not");
        int[] arr4 = {1, 4, 2, 1};
        if (Arrays.stream(arr4).distinct().count() == arr4.length)
            System.out.println("no duplicates");
        else System.out.println("contain duplicates");

        System.out.println("square elements of list and filter numbers greater than 50");
        List<Integer> li1 = Arrays.asList(1, 5, 6, 3, 2, 8, 4, 7, 11, 10);
        List<Integer> squares = li1.stream().map(x -> x * x).filter(x -> x > 50).toList();
        System.out.println(squares);

        System.out.println("concat 2 streams");
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(5, 8);
        Stream.concat(s1, s2).forEach(System.out::println);

        System.out.println("print 10 random numbers");
        Random random = new Random();
        System.out.println(Stream.generate(random::nextInt)
                .limit(10)
                .toList());

        System.out.println("count occurrence of each duplicate elements");
        List<String> strList = Arrays.asList("a", "f", "o", "s", "o", "a", "a");
        Map<String, Long> collect6 = strList.stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect6);
        //.forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));

        System.out.println("group them by the first letter and count no. of elements in each group");
        List<String> names = Arrays.asList("Bob", "Alice", "Charlie", "Amy", "Ben", "Bella");
        Map<Character, Long> freqFirstLetter = names.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));
        System.out.println(freqFirstLetter);

        System.out.println("remove duplicates from array");
        int[] arr7 = {1, 4, 2, 1, 1, 6, 5, 4, 3, 2};
        int[] uniquearray = Arrays.stream(arr7).distinct().toArray();
        System.out.println(Arrays.toString(uniquearray));

        System.out.println("print palindrome");
        List<String> words = Arrays.asList("deed", "world", "radar", "hello", "level");
        List<String> pallindrome = words.stream()
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .toList();
        System.out.println(pallindrome);

        System.out.println("merge 2 sorted arrays to single sorted array");
        int[] a1 = {1, 3, 5, 7};
        int[] a2 = {2, 4, 6, 8};
        int[] sortedarray = IntStream.concat(Arrays.stream(a1), Arrays.stream(a2))
                .sorted().toArray();
        System.out.println(Arrays.toString(sortedarray));

        System.out.println("concat 2 list and remove duplicates and sort in desc order");
        List<String> words1 = Arrays.asList("deed", "world", "radar", "hello");
        List<String> words2 = Arrays.asList("deed", "hello", "level", "this", "hi");
        List<String> concatResult = Stream.concat(words1.stream(), words2.stream())
                .distinct().sorted(Comparator.reverseOrder())
                .toList();
        System.out.println(concatResult);

        System.out.println("sort strings by their length in desc order");
        List<String> words3 = Arrays.asList("umbrella", "hell", "world", "let", "test", "be");
        List<String> output = words3.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
        System.out.println(output);
        //or
        List<String> output9=words3.stream()
                .sorted((w1,w2)-> w1.length()>w2.length()?-1:w1.length()<w2.length()?1:0)
                .collect(Collectors.toList());
        System.out.println(output9);

        System.out.println("sorting array based on last character");
        String[] arr0 = {"happy", "smile", "cry", "laugh"};
        Stream.of(arr0)
                .sorted((st1, st2) -> Character.compare(st1.charAt(st1.length() - 1), st2.charAt(st2.length() - 1)))
                .forEach(System.out::println);
        //or
        Arrays.stream(arr0)
                .sorted(Comparator.comparingInt(st -> st.charAt(st.length() - 1)))
                .forEach(System.out::println);

        System.out.println("merging and removing duplicates from the 2 arrays.");
        int[] arr11 = {3, 2, 1, 4, 5, 6, 8, 7, 9};
        int[] arr22 = {8, 9, 10, 11, 12, 13, 15, 14, 15, 14, 16, 17};
        int[] array = IntStream.concat(Arrays.stream(arr11), Arrays.stream(arr22))
                .distinct()
                .toArray();
        System.out.println(Arrays.toString(array));

        System.out.println("print count of each vowel");
        String strr=" My name is Ishita Mehta";
        String sss="aeiou";
        Map<Character, Long> collect99 = strr.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .filter(c -> sss.indexOf(c) != -1)
                .collect(Collectors.groupingBy(c->c, Collectors.counting()));
        System.out.println(collect99);
        //or
        Map<String, Long> collect8 = Arrays.stream(strr.toLowerCase().replaceAll("\\s+", "").split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getKey().equals("a") || x.getKey().equals("e")
                        || x.getKey().equals("i") || x.getKey().equals("o") || x.getKey().equals("u"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect8);
        //  .forEach(x->System.out.println(x.getKey()+":"+x.getValue()));
        System.out.println("----------------------------------------------------------------------------");

        //sort objects based on specific attribute
        List<Person> personList = Arrays.asList(
                new Person("Bob", 11),
                new Person("Alice", 5),
                new Person("Peter", 9),
                new Person("Jade", 7)
        );
        Collections.sort(personList, Comparator.comparingInt(Person::getAge));
        System.out.println(personList);
        //or
        List<Person> sortedlist = personList.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        System.out.println(sortedlist);
        System.out.println("--------------------------------------------------------------------");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        System.out.println("How many male and female employees are there in the organization?");
        Map<String, Long> employees = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(employees);

        System.out.println("Print the name of all departments in the organization?");
        List<String> departments = employeeList.stream().map(Employee::getDepartment)
                .distinct().collect(Collectors.toList());
        System.out.println(departments);

        System.out.println("What is the average age of male and female employees?");
        Map<String, Double> avgAge = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgAge);

        System.out.println("Get the details of highest paid employee in the organization?");
        Employee maxsalaryemployee = employeeList.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);
        System.out.println(maxsalaryemployee);

        System.out.println("Get the names of all employees who have joined after 2015?");
        List<String> emp2015 = employeeList.stream()
                .filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(emp2015);

        System.out.println("Count the number of employees in each department?");
        Map<String, Long> empInEachDep = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        for (Map.Entry<String, Long> mp : empInEachDep.entrySet()) {
            System.out.println(mp.getKey() + ":" + mp.getValue());
        }

        System.out.println("What is the average salary of each department?");
        Map<String, Double> avgSalaryOfDepartments =
                employeeList.stream().
                        collect(Collectors.groupingBy(Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryOfDepartments);

        System.out.println("Get the details of youngest male employee in the product development department?");
        Employee youngestMale = employeeList.stream()
                .filter(x -> x.getGender().equals("Male") && x.getDepartment().equals("Product Development"))
                .sorted(Comparator.comparingInt(Employee::getAge)).findFirst().orElse(null);
        System.out.println(youngestMale);
        //or
        Employee youngestMale1 = employeeList.stream()
                .filter(x -> x.getGender().equals("Male") && x.getDepartment().equals("Product Development"))
                .min(Comparator.comparingInt(Employee::getAge)).orElse(null);
        System.out.println(youngestMale1);

        System.out.println("Who has the most working experience in the organization?");
        Employee seniorMostEmployee = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst().orElse(null);
        System.out.println(seniorMostEmployee);
        //or
        Employee seniorMostEmployee1 = employeeList.stream()
                .min(Comparator.comparing(Employee::getYearOfJoining)).orElse(null);
        System.out.println(seniorMostEmployee1);

        System.out.println("List down the names of all employees in each department?");
        Map<String, List<String>> employeeListByDepartment = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())));
        System.out.println(employeeListByDepartment);

        System.out.println("What is the average salary and total salary of the whole organization?");
        DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(employeeSalaryStatistics.getAverage());
        System.out.println(employeeSalaryStatistics.getSum());
        //or
        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).sum());
        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).average().getAsDouble());

        System.out.println("Group employee into 2 groups, having salary more than 20k and less than 20k.");
        Map<Boolean, List<Employee>> groupedBySalary = employeeList.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 20000));
        List<Employee> moreThan20k = groupedBySalary.get(true);
        List<Employee> lessThanOrEqual20k = groupedBySalary.get(false);
        System.out.println("Employees with salary more than 20k:");
        moreThan20k.forEach(System.out::println);
        System.out.println("\nEmployees with salary less than or equal to 20k:");
        lessThanOrEqual20k.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------");

        System.out.println("print date and time");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println("change date format to dd-MM-yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(dateTimeFormatter.format(localDateTime));

        //check if list is empty using optional and iterate if not null
        List<Integer> li2=Arrays.asList(7,11,10);
        //List<Integer> li2 = null;
        Optional<List<Integer>> optionalList = Optional.ofNullable(li2);
        optionalList.ifPresentOrElse(System.out::println, () -> System.out.println("Empty list"));

        System.out.println("*************************************************************************");

        System.out.println("Convert ArrayList to HashMap in Java 8 using a Lambda Expression");
        List<IntegerList> intList = new ArrayList<>();
        intList.add(new IntegerList(1, 11));
        intList.add(new IntegerList(2, 12));
        intList.add(new IntegerList(3, 13));
        HashMap<Integer, Integer> integerHashMap = new HashMap<>();

        intList.stream().forEach(x -> integerHashMap.put(x.getKey(), x.getValue()));
        System.out.println(integerHashMap);

        String email = "abc@gmail.com";
        //String email=null;
        Optional<String> optionalString = Optional.ofNullable(email);
        if (optionalString.isPresent()) {
            System.out.println(optionalString.get());
        }
        System.out.println(optionalString.orElse("default@gmail.com"));
        System.out.println(optionalString.map(String::toUpperCase).orElseGet(() -> "defaultemail@gmail.com"));
        System.out.println(optionalString
                .orElseThrow(() -> new IllegalArgumentException("email cannot be null")));
        System.out.println("***********************************************************************************");
        
        System.out.println("Comparable example");
        Movie krish = new Movie("Krish", 4, 2000);
        Movie humgama = new Movie("Hungama", 3, 2004);
        Movie stree = new Movie("Stree", 4.5, 2020);
        List<Movie> movies = new ArrayList<>();
        movies.add(humgama);
        movies.add(stree);
        movies.add(krish);
        System.out.println("Before sorting");
        System.out.println(movies);
        Collections.sort(movies);
        System.out.println("After sorting");
        System.out.println(movies);
        System.out.println("***********************************************************************************");

        System.out.println("Comparator example");
        ArrayList<EnglishMovie> englishMovieslist = new ArrayList<>();
        englishMovieslist.add(new EnglishMovie("XXX", 8.3, 2017));
        englishMovieslist.add(new EnglishMovie("Star Wars", 8.7, 1977));
        englishMovieslist.add(new EnglishMovie("Force Awakens", 8.3, 2015));
        englishMovieslist.add(new EnglishMovie("Empire Strikes Back", 8.8, 1980));
        englishMovieslist.add(new EnglishMovie("Return of the Jedi", 8.4, 1983));

        System.out.println("Before sorting");
        System.out.println(englishMovieslist);

        RatingCompare ratingCompare = new RatingCompare();
        Collections.sort(englishMovieslist, ratingCompare);
        System.out.println("After sorting using year of release");
        System.out.println(englishMovieslist);

        NameCompare nameCompare = new NameCompare();
        Collections.sort(englishMovieslist, nameCompare);
        System.out.println("After sorting using movie name");
        System.out.println(englishMovieslist);

        RatingAndNameCompare ratingAndNameCompare = new RatingAndNameCompare();
        Collections.sort(englishMovieslist, ratingAndNameCompare);
        System.out.println("After sorting using rating and movie name");
        System.out.println(englishMovieslist);
        System.out.println("***********************************************************************************");

        ArrayList<Integer> al = new ArrayList<>();
        al.add(205);
        al.add(102);
        al.add(98);
        al.add(275);
        al.add(203);
        System.out.println("Elements of the ArrayList before sorting : " + al);
        // using lambda expression in place of comparator object
        Collections.sort(al, (o1, o2) -> o1 < o2 ? -1 : o1 > o2 ? 1 : 0);
        System.out.println("Elements of the ArrayList after sorting : " + al);

        //naturally sorts in asc order, change to desc order
        TreeSet<Integer> h = new TreeSet<>(
                (o1, o2) -> o1 < o2 ? 1 : o1 > o2 ? -1 : 0);
        h.add(850);
        h.add(235);
        h.add(1080);
        h.add(15);
        h.add(5);
        System.out.println("Elements of the TreeSet after sorting are: " + h);

        TreeMap<Integer, String> m = new TreeMap<>(
                (o1, o2) -> (o1 > o2) ? -1 : (o1 < o2) ? 1 : 0);
        m.put(1, "Apple");
        m.put(4, "Mango");
        m.put(5, "Orange");
        m.put(2, "Banana");
        m.put(3, "Grapes");
        System.out.println("Elements of the TreeMap after sorting are : " + m);

        TreeSet<String> ts = new TreeSet<>((aStr, bStr) -> bStr.compareTo(aStr));
        ts.add("A");
        ts.add("B");
        ts.add("C");
        ts.add("D");
        ts.add("E");
        ts.add("F");
        ts.add("G");
        System.out.println("Elements of the TreeMap after sorting are : " + ts);
        System.out.println("----------------------------------------------------------------------------");

        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getMonth());
        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getHour());
        System.out.println(localDateTime1.getMinute());
        System.out.println(localDateTime1.getSecond());
        System.out.println(localDateTime1.plusDays(2));
        System.out.println(localDateTime1.minusMonths(1));
        System.out.println(LocalDateTime.of(2024, 07, 14, 17, 59, 16));
        System.out.println(LocalDateTime.now().plusDays(1).plusMonths(2).plusYears(1));
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("Current Date and Time with Time Zone: " + now);
        ZonedDateTime specificZone = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Date and Time in New York: " + specificZone);
        System.out.println("***********************************************************************");

        LimitClass obj1 = LimitClass.getLimInstance();
        LimitClass obj2 = LimitClass.getLimInstance();
        LimitClass obj3 = LimitClass.getLimInstance();
        LimitClass obj4 = LimitClass.getLimInstance();
        LimitClass obj5 = LimitClass.getLimInstance();
        LimitClass obj6 = LimitClass.getLimInstance();

        System.out.println(obj1);
        System.out.println(obj2);
        System.out.println(obj3);
        System.out.println(obj4);
        System.out.println(obj5);
        System.out.println(obj6);

        HashMap<Integer,String> map1=new HashMap<>();
        map1.put(1,"hello");
        map1.put(null,null);
        String s3 = map1.get(null);
     //   System.out.println(s3.length());

        Square s = (side) -> (side * side);
        System.out.println(s.area(5));

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Thread started");
//            }
//        }).start();
//
//        new Thread(()->{
//            System.out.println("New thread");
//        }).start();

        Consumer<Integer> consumer = (value) -> System.out.println(value);
        consumer.accept(5);
        IntConsumer intConsumer = (val) -> System.out.println(val);
        intConsumer.accept(8);

        Predicate<String> predicate = t1 -> t1 != null;
        System.out.println(predicate.test(null));
        BiPredicate<Integer, Integer> biPredicate = (z1, z2) -> z1 > z2 ? true : false;
        System.out.println(biPredicate.test(10, 8));

        Supplier<String> supplier = () -> "Hello";
        System.out.println(supplier.get());

        FibonacciSupplier fibonacciSupplier = new FibonacciSupplier();
        Stream.generate(fibonacciSupplier).limit(10).forEach(System.out::println);

        Function<Integer, Integer> function = (v1) -> v1 + 10;
        System.out.println("Function: " + function.apply(5));

        System.out.println("**************************************************************************8");
    }
}