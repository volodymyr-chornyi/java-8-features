package co.inventorsoft;

import co.inventorsoft.model.Person;
import co.inventorsoft.model.User;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Contains simple cases for trying Stream API in action.
 */
public class StreamHomework {
    /**
     * Used to filter children and adults.
     * Returns collection of teenagers (13-19 years old).
     *
     * @param people collection of people to extract teenagers
     * @return collection of teenagers
     */
    public List<Person> extractTeenagers(final List<Person> people) {
        List<Person> teenagers = people.stream()
                .filter(person -> person.getAge() >= 13)
                .filter(person -> person.getAge() <= 19)
                .collect(toList());
        return teenagers;
    }

    /**
     * Creates users, based on given collection of emails.
     * Handles email duplicates and null-values.
     *
     * @param emails collection of emails, duplicates or null-values are possible
     * @return collection of user, without duplicates
     */
    public List<User> createUsers(final List<String> emails) {
        List<User> users = emails.stream()
                .distinct()
                .filter(Objects::nonNull)
                .map(User::new)
                .collect(toList());
        return users;
    }

    /**
     * Builds map with user email, as a key and user as a value.
     *
     * @param users collection of users
     * @return map {user email : user}
     */
    public Map<String, User> groupByEmail(final List<User> users) {
        Map<String, User> byEmail = users.stream()
                .collect(toMap(User::getEmail, Function.identity()));
        return byEmail;
    }

    /**
     * Builds map with person age, as a key and collection of people with this age as a value.
     *
     * @param people collection of people
     * @return map {age : people with this age}
     */
    public Map<Integer, List<Person>> groupByAge(final List<Person> people) {
        Map<Integer, List<Person>> byAge = people.stream()
                .collect(groupingBy(Person::getAge));
        return byAge;
    }

    /**
     * Creates single string, representing all people names, emphasizing uniqueness!
     * Example:
     *    input:  [{13, "Harry"}, {13, "Ron"}, {14, "Hermione"}, {13, "Harry"}]
     *    output: "Distinct names: Harry, Ron, Hermione!"
     *
     * @param people collection of people
     * @return string with unique names, like "Distinct names: a, b, c!"
     */
    public String collectDistinctNames(final List<Person> people) {
        String name = people.stream()
                .map(Person::getName)
                .distinct()
                .collect(joining(", ", "Distinct names: ", "!"));
        return name;
    }
}
