
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        //2nd longest word in string
        String longest1="this is an example string with some very long words like extraordinary";
        String s4 = Arrays.stream(longest1.split("\\s+"))
                .distinct()
              //  .sorted((w1,w2)->w1.length()>w2.length()?-1:w1.length()<w2.length()?1:0)
                .sorted(Comparator.comparing(String::length).reversed())
              //  .sorted((a,b)->b.length()-a.length())
                .skip(1).findFirst().get();
        System.out.println(s4);

        //count occurrence of each character
        String str = "ilovejavaa";
        Map<String, Long> collect = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);

        //freq of each word
        String ss = "  this is the tour of the place called Paris and place called Rome and Place Tokyo";
        Map<String, Long> freq = Arrays.stream(ss.trim().split("\\s+"))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.println(freq);

        //find all duplicate elements in java string
        List<String> duplicates = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey) //.map(x->x.getKey())
                .collect(Collectors.toList());
        System.out.println(duplicates);
        //or
        Set<String> set1 = new HashSet<>();
        Set<String> duplicates3 = Arrays.stream(str.split("")).filter(x -> !set1.add(x)).collect(Collectors.toSet());
        System.out.println(duplicates3);

        //find all unique elements in java string
        List<String> unique = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(unique);

        //first non-repeating character from given string
        String firstNonRepeating = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() == 1)
                .findFirst().get().getKey();
        System.out.println("First non-repeating char: " + firstNonRepeating);

        //find first repeating character in string
        String str4 = "ilovejava";
        String firstRepeating = Arrays.stream(str4.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .findFirst().get().getKey();
        System.out.println("First repeating char: " + firstRepeating);

        //find second highest number in array
        int[] nums = {5, 9, 2, 8, 11, 21, 1};
        Integer secondLargest = Arrays.stream(nums).boxed()
                .distinct().sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst().get();
        System.out.println("second largest: " + secondLargest);

        //find second smallest number in array
        int[] num = {5, 9, 2, 8, 11, 21, 1, 1};
        Integer secondSmallest = Arrays.stream(num).boxed()
                .distinct().sorted()
                .skip(1)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("There is no second smallest element"));
        System.out.println("second smallest: " + secondSmallest);

        //find longest string from given array
        String[] str1 = {"Hello", "Hi", "Ishita", "nice day", "Good"};
        String longest = Arrays.stream(str1)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get();
        System.out.println("longest string: " + longest);
        //or
        String s33 = Arrays.stream(str1).sorted(Comparator.comparingInt(String::length).reversed())
                .findFirst().get();
        System.out.println(s33);

        //find length of longest string in given array
        Integer maxlength = Arrays.stream(str1)
                .mapToInt(x -> x.length())
                .max()
                .orElse(0);
        System.out.println("maxlength: " + maxlength);
        //or
        System.out.println(Arrays.stream(str1)
                .reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get()
                .length());

        //find all array elements starting with 1
        List<String> startWith1 = Arrays.stream(nums).boxed()
                //.map(x -> x + "")
                .map(String::valueOf)
                .filter(x -> x.startsWith("1"))
                .toList();
        System.out.println("Elements starting with 1:" + startWith1);

        //print numbers divisible by 3
        List<String> list = Arrays.asList("3", "6", "8", "14", "15");
        list.stream().mapToInt(nu -> Integer.parseInt(nu))
                .filter(nu -> nu % 3 == 0)
                .forEach(System.out::println);

        //String.join()
        List<String> str3 = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));
        String re = String.join("-", str3);
        System.out.println(re);

        //skip & limit - print 2-9 from given list having 1-10
        //skip - for eg if you are reading csv file, u will have to skip header
        List<Integer> collection = IntStream.rangeClosed(1, 10).boxed()
                .skip(1).limit(8).collect(Collectors.toList());
        System.out.println(collection);

        //given 2 array of integers, find common elements between them
        int[] arr1 = {1, 2, 6, 3, 7};
        int[] arr2 = {5, 2, 7, 8, 1};
        List<Integer> common = Arrays.stream(arr1)
                .filter(num1 -> Arrays.stream(arr2).anyMatch(num2 -> num2 == num1)).boxed()
                .collect(Collectors.toList());
        System.out.println("Common elements: " + common);

        //reverse array in-place
        int[] num1 = {1, 6, 3, 2, 9, 0};
        IntStream.range(0, num1.length / 2)
                .forEach(i -> {
            int temp = num1[i];
            num1[i] = num1[num1.length - i - 1];
            num1[num1.length - i - 1] = temp;
        });
        System.out.println(Arrays.toString(num1));

        //sum of all elements
        int sum = Arrays.stream(num1).reduce(0, (sums, i) -> sums + i);
        System.out.println("Sum: " + sum);
        //or
        List<Integer> list9 = Arrays.asList(1, 2, 7, 3, 5, 4);
        int sum1 = list9.stream()
                .mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum1);

        //max element
        Integer max = list9.stream().max(Comparator.naturalOrder()).get();
        System.out.println("Maximum: " + max);
        //or
        Integer max11 = list9.stream().sorted(Comparator.reverseOrder()).findFirst().get();
        System.out.println(max11);

        //count number of strings starting with character c
        List<String> fruits = Arrays.asList("cherry", "berry", "banana", "apple", "coconut");
        long total = fruits.stream().filter(s -> s.startsWith("c")).count();
        System.out.println(total);

        //convert list to uppercase
        List<String> fruitList = fruits.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(fruitList);

        //filter even numbers
        List<Integer> even = list9.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println(even);

        //avg of floating numbers
        List<Double> nums3 = Arrays.asList(1.1, 1.2, 1.3, 1.4);
        Double average = nums3.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
        System.out.println("Average: " + average);

        //concatenate string
        String collect1 = fruits.stream().collect(Collectors.joining("-","[","]"));
        System.out.println(collect1);

        //remove duplicate elements
        List<Integer> list1 = Arrays.asList(1, 2, 7, 1, 3, 5, 4, 4, 0);
        List<Integer> distinct = list1.stream().distinct().collect(Collectors.toList());
        System.out.println(distinct);

        //Given a list of integers, separate odd and even numbers?
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
        Map<Boolean, List<Integer>> oddEvenNumbersMap =
                listOfIntegers.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
        List<Integer> evenN = oddEvenNumbersMap.get(true);
        List<Integer> oddN=oddEvenNumbersMap.get(false);
        System.out.println(evenN);
        System.out.println(oddN);

        //find the most repeated element in an array
        List<String> listOfStrings = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil",
                "Pen", "Note Book", "Pencil");
        String keymax = listOfStrings.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst()
                //  .max(Map.Entry.comparingByValue())
                //  .max(Comparator.comparingLong(Map.Entry::getValue))
                .get().getKey();
        System.out.println(keymax);

        //Given a list of strings, find out those strings which start with a number?
        List<String> listOfStrings1 = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
        listOfStrings1.stream().filter(a -> Character.isDigit(a.charAt(0))).forEach(System.out::println);

        //Print duplicate characters in a string
        String inputString = "Java Concept Of The Day";
        Set<String> set2=new HashSet<>();
        Set<String> re1 = Arrays.stream(inputString.toLowerCase().replaceAll("\\s+", "")
                        .split(""))
                .filter(el -> !set2.add(el))
                .collect(Collectors.toSet());
        System.out.println(re1);

        //Reverse each word of a string using Java 8 streams?
        String stri = "Java Concept Of The Day";
        String reversedStr = Arrays.stream(stri.split(" "))
                .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println(reversedStr);

        //Java 8 program to check if two strings are anagrams or not
        String s01 = "RaceCar";
        String s02 = "CarRace";
        s01 = Arrays.stream(s01.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        s02 = Arrays.stream(s02.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        if (s01.equals(s02)) {
            System.out.println("Two strings are anagrams");
        }
        else {
            System.out.println("Two strings are not anagrams");
        }

        //sum of all digits
        int i = 15623;
        Integer sumOfDigits = Arrays.stream(String.valueOf(i).split(""))
                .mapToInt(x->Integer.parseInt(x))
                .sum();
        System.out.println(sumOfDigits);

        //check string is pallindrome
        String pallindromeS="madam";
        boolean isPallindrome=IntStream.range(0,pallindromeS.length()/2)
                .allMatch(ii->pallindromeS.charAt(ii)==pallindromeS.charAt(pallindromeS.length()-1-ii));
        System.out.println(isPallindrome);

        //concatenate all strings
        List<String> strs=Arrays.asList("Hello"," ","World","!");
        String collect2 = strs.stream().collect(Collectors.joining());
        System.out.println(collect2);

        //count occurrence of a character in list of strings
        List<String> strs1=Arrays.asList("apple","banana ","cherry","strawberry");
        char target='a';
        long count1 = strs1.stream()
                .flatMapToInt(String::chars) //// Convert each string to an IntStream of character codes
                .mapToObj(t->(char)t)
                .filter(t -> t == target).count();
        System.out.println(count1);

        //factorial of 5
        int reduce = IntStream.rangeClosed(1, 5)
                .reduce(1, (a, b) -> a * b);
        System.out.println(reduce);

        //frequency of each word
        List<String> list3 = Arrays.asList("apple banana apple", "banana cherry", "Apple banana cherry");
        Map<String, Long> collect3 = list3.stream()
                .flatMap(st -> Arrays.stream(st.split("\\s+")))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        System.out.println(collect3);

        //distinct characters
        List<String> strs2=Arrays.asList("apple","banana","cherry","strawberry");
        List<String> collect4= strs2.stream()
                .flatMap(st -> Arrays.stream(st.split("")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(collect4);

        //or
        Set<Character> collect5 = strs2.stream().flatMapToInt(String::chars)
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.toSet());
        System.out.println(collect5);

        //check if all elements matches the condition - say all are even
        List<Integer> list2 = Arrays.asList(8, 2, 4, 4, 0);
        boolean allEven = list2.stream().allMatch(x -> x % 2 == 0);
        System.out.println("All elements are even: " + allEven);

        //duplicate elements in list of integers
        List<Integer> l = new ArrayList<>(Arrays.asList(1, 5, 6, 3, 1, 8, 7, 4, 3));
        List<Integer> duplicates1 = l.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(duplicates1);
        //or
        Set<Integer> set = new HashSet<>();
        List<Integer> duplicates2 = l.stream().filter(x -> !set.add(x)).collect(Collectors.toList());
        System.out.println(duplicates2);

        //find max and min in list of integers
        List<Integer> li = new ArrayList<>(Arrays.asList(1, 5, 6, 3, 2, 8, 4, 7, 4));
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

        //find max in array
        int[] arr5 = {1, 4, 2, 1};
        Arrays.stream(arr5).max().ifPresent(System.out::println);

        //sort elements in list in desc order
        List<Integer> sorted = li.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(sorted);

        //sort elements in list in asc order
        List<Integer> sorted1 = li.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted1);

        //check array contains duplicate value/ not
        int[] arr4 = {1, 4, 2, 1};
        if (Arrays.stream(arr4).distinct().count() == arr4.length)
            System.out.println("no duplicates");
        else System.out.println("contain duplicates");

        //square elements of list and filter numbers greater than 50
        List<Integer> li1 = new ArrayList<>(Arrays.asList(1, 5, 6, 3, 2, 8, 4, 7, 11, 10));
        List<Integer> squares = li1.stream().map(x -> x * x).filter(x -> x > 50).collect(Collectors.toList());
        System.out.println(squares);

        //concat 2 streams
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Integer> s2 = Stream.of(5, 8);
        Stream.concat(s1, s2).forEach(System.out::println);

        //print 10 random numbers
        Random random = new Random();
        System.out.println(Stream.generate(random::nextInt)
                .limit(10)
                .collect(Collectors.toList()));

        //find number of occurrence of particular value
        int[] arr6 = {11, 2, 5, 3, 7, 2, 1, 2};
        int key = 2;
        long count = Arrays.stream(arr6).filter(x -> x == key).count();
        System.out.println(key + " occur " + count + " times.");

        //count occurrence of each duplicate elements
        List<String> strList = Arrays.asList("a", "f", "o", "s", "o", "a", "a");
        Map<String, Long> collect6 = strList.stream()
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                .entrySet().stream()
                .filter(x -> x.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(collect6);
        //.forEach(x -> System.out.println(x.getKey() + ":" + x.getValue()));

        //group them by the first letter and count no. of elements in each group
        List<String> names = Arrays.asList("Bob", "Alice", "Charlie", "Amy", "Ben", "Bella");
        Map<Character, Long> freqFirstLetter = names.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0), Collectors.counting()));
        System.out.println(freqFirstLetter);

        //remove duplicates from array
        int[] arr7 = {1, 4, 2, 1, 1, 6, 5, 4, 3, 2};
        int[] uniquearray = Arrays.stream(arr7).distinct().toArray();
        System.out.println(Arrays.toString(uniquearray));

        //print pallindrome
        List<String> words = Arrays.asList("deed", "world", "radar", "hello", "level");
        List<String> pallindrome = words.stream()
                .filter(s -> s.equals(new StringBuilder(s).reverse().toString()))
                .collect(Collectors.toList());
        System.out.println(pallindrome);

        //merge 2 sorted arrays to single sorted array
        int[] a1 = {1, 3, 5, 7};
        int[] a2 = {2, 4, 6, 8};
        int[] sortedarray = IntStream.concat(Arrays.stream(a1), Arrays.stream(a2))
                .sorted().toArray();
        System.out.println(Arrays.toString(sortedarray));

        //concat 2 list and remove duplicates and sort in desc order
        List<String> words1 = Arrays.asList("deed", "world", "radar", "hello");
        List<String> words2 = Arrays.asList("deed", "hello", "level", "this", "hi");
        List<String> concatResult = Stream.concat(words1.stream(), words2.stream())
                .distinct().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(concatResult);

        //sort strings by their length in desc order
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

        //sorting array based on last character
        String[] arr0 = {"happy", "smile", "cry", "laugh"};
        Stream.of(arr0)
                .sorted((st1, st2) -> Character.compare(st1.charAt(st1.length() - 1), st2.charAt(st2.length() - 1)))
                .forEach(System.out::println);
        //or
        Arrays.stream(arr0)
                .sorted(Comparator.comparingInt(st -> st.charAt(st.length() - 1)))
                .forEach(System.out::println);

        //merging and removing duplicates from the 2 arrays.
        int[] arr11 = {3, 2, 1, 4, 5, 6, 8, 7, 9};
        int[] arr22 = {8, 9, 10, 11, 12, 13, 15, 14, 15, 14, 16, 17};
        int[] array = IntStream.concat(Arrays.stream(arr11), Arrays.stream(arr22))
                .distinct()
                .toArray();
        System.out.println(Arrays.toString(array));

        System.out.println("----------------------------------------------------------------------------");
        //sort objects based on specific attribute
        List<Person> personList = Arrays.asList(
                new Person("Bob", 11),
                new Person("Alice", 5),
                new Person("Peter", 9),
                new Person("Jade", 7)
        );
        Collections.sort(personList, Comparator.comparing(Person::getAge));
        System.out.println(personList);
        //or
        List<Person> sortedlist = personList.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        System.out.println(sortedlist);

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

        //How many male and female employees are there in the organization?
        Map<String, Long> employees = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(employees);

        //Print the name of all departments in the organization?
        List<String> departments = employeeList.stream().map(Employee::getDepartment)
                .distinct().collect(Collectors.toList());
        System.out.println(departments);

        //What is the average age of male and female employees?
        Map<String, Double> avgAge = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgAge);

        //Get the details of highest paid employee in the organization?
        Employee maxsalaryemployee = employeeList.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(maxsalaryemployee);

        //Get the names of all employees who have joined after 2015?
        List<String> emp2015 = employeeList.stream()
                .filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName).collect(Collectors.toList());
        System.out.println(emp2015);

        //Count the number of employees in each department?
        Map<String, Long> empInEachDep = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        for (Map.Entry<String, Long> mp : empInEachDep.entrySet()) {
            System.out.println(mp.getKey() + ":" + mp.getValue());
        }

        //What is the average salary of each department?");
        Map<String, Double> avgSalaryOfDepartments =
                employeeList.stream().
                        collect(Collectors.groupingBy(Employee::getDepartment,
                                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryOfDepartments);

        //Get the details of youngest male employee in the product development department?
        Employee youngestMale = employeeList.stream()
                .filter(x -> x.getGender() == "Male" && x.getDepartment() == "Product Development")
                .sorted(Comparator.comparingInt(Employee::getAge)).findFirst().get();
        System.out.println(youngestMale);
        //or
        Employee youngestMale1 = employeeList.stream()
                .filter(x -> x.getGender() == "Male" && x.getDepartment() == "Product Development")
                .min(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(youngestMale1);

        //Who has the most working experience in the organization?
        Employee seniorMostEmployee = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst().get();
        System.out.println(seniorMostEmployee);

        //List down the names of all employees in each department?
        Map<String, List<String>> collect7 = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e.getName(), Collectors.toList())));
        System.out.println(collect7);
        // for (Map.Entry<String, List<Employee>> map : employeeListByDepartment.entrySet()) {
         //   System.out.println(map.getKey() + ":");
        //    map.getValue().stream().map(Employee::getName).forEach(System.out::println);
//            for (Employee e : map.getValue()) {
//                System.out.println(e.getName());
//            }

        //What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(employeeSalaryStatistics.getAverage());
        System.out.println(employeeSalaryStatistics.getSum());
        //or
        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).sum());
        System.out.println(employeeList.stream().mapToDouble(Employee::getSalary).average().getAsDouble());

        //Group employee into 2 groups, having salary more than 20k and less than 20k.
        Map<Boolean, List<Employee>> groupedBySalary = employeeList.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 20000));

        List<Employee> moreThan20k = groupedBySalary.get(true);
        List<Employee> lessThanOrEqual20k = groupedBySalary.get(false);

        System.out.println("Employees with salary more than 20k:");
        moreThan20k.forEach(System.out::println);

        System.out.println("\nEmployees with salary less than or equal to 20k:");
        lessThanOrEqual20k.forEach(System.out::println);

        System.out.println("------------------------------------------------------------------------");

        //print date and time
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        //change date format to dd-MM-yyyy
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println(dateTimeFormatter.format(localDateTime));

        //check if list is empty using optional and iterate if not null
        //List<Integer> li2=new ArrayList<>(Arrays.asList(7,11,10));
        List<Integer> li2 = null;
        Optional<List<Integer>> optionalList = Optional.ofNullable(li2);
        optionalList.ifPresentOrElse(x -> x.stream().forEach(System.out::println), () -> System.out.println("Empty list"));

        System.out.println("*************************************************************************");
        Square s = (side) -> (side * side);
        System.out.println(s.area(5));

//        new Thread(new Runnable() {
//
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
        //flatmap
        //generate list from list<list<Integer>>
        List<List<Integer>> number = new ArrayList<>();
        number.add(Arrays.asList(1, 2));
        number.add(Arrays.asList(3, 4, 5));
        number.add(Arrays.asList(6));
        number.add(Arrays.asList(7, 8));
        List<Integer> flatList = number.stream().flatMap(z -> z.stream()).collect(Collectors.toList());
        System.out.println(flatList);

        //Get the 1st Character of all the Strings present in a List<List<Strings>> and returning the
        //result in form of a stream.
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

        //immutable
        String strin = "hello";
        strin.toUpperCase();
        System.out.println(strin);

        System.out.println("___________________________________________________________________________________");

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

        //print count of each vowel
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

    }

}