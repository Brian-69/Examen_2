import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


public class Main {
    private static final int ATTRIBUTE_COUNT = 10;
    private static final int MIN_ATTRIBUTE_DIFFERENCE = 1;
    private static final int MIN_ATTRIBUTE_OCCURRENCES = 2;
    private static final int MAX_ATTRIBUTES_PER_PERSON = 4;
    private static final int PERSON_COUNT = 10;

    private static int[] attributeOccurrences = new int[ATTRIBUTE_COUNT];

    private static final String[] NAMES = {"Jose", "Pedro", "Vicente", "Luis", "Juan", "Ana", "Dulce", "Natalia", "Julieta", "Paty"};
    private static final String[] ATTRIBUTES = {"Tall", "Strong", "Thin", "Blone", "Smart", "Funny", "Serious", "Kind", "Honest", "Creative"};

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < PERSON_COUNT; i++) {
            people.add(createPerson(NAMES[i], ATTRIBUTE_COUNT));
        }

        Collections.shuffle(people);

        System.out.println("Welcome to Guess Who! Here are the people and their attributes:");

        for (Person person : people) {
            System.out.print(person.getName() + ": ");
            int attributesPrinted = 0;
            for (int i = 0; i < ATTRIBUTE_COUNT; i++) {
                if (person.hasAttribute(i)) {
                    System.out.print(ATTRIBUTES[i] + ", ");
                    attributesPrinted++;
                    if (attributesPrinted == MAX_ATTRIBUTES_PER_PERSON) {
                        break;
                    }
                }
            }
            System.out.println();
        }

        System.out.println("Attribute Occurrences:");
        for (int i = 0; i < ATTRIBUTE_COUNT; i++) {
            System.out.println(ATTRIBUTES[i] + ": " + attributeOccurrences[i]);
        }

        Scanner scanner = new Scanner(System.in);

        // Choose a random person as the subject to be guessed
        int subjectIndex = new Random().nextInt(PERSON_COUNT);

        System.out.println("Guess who the mystery person is! You have 3 questions to narrow it down.");

        int questionNumber = 1;
        while (questionNumber <= 3) {
            System.out.println("Question " + questionNumber + ": What attribute do you want to ask for?");
            // Display the attribute options
            for (int i = 0; i < ATTRIBUTE_COUNT; i++) {
                System.out.println((i  + 1) + ". " + ATTRIBUTES[i]);
            }

            // Get the user's choice of attribute
            int attributeChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Check if the chosen attribute is valid
            if (attributeChoice < 1 || attributeChoice > ATTRIBUTE_COUNT) {
                System.out.println("Invalid attribute choice, please try again.");
                continue;
            }

            // Display the list of people who have the chosen attribute
            List<Person> peopleWithAttribute = new ArrayList<>();
            for (Person person : people) {
                if (person.hasAttribute(attributeChoice)) {
                    peopleWithAttribute.add(person);
                }
            }
            System.out.print("People that are " + ATTRIBUTES[attributeChoice - 1] + ": ");
            for (Person person : peopleWithAttribute) {
                System.out.print(person.getName() + " ");
            }
            System.out.println();

            // Remove people who do not have the chosen attribute
            people.retainAll(peopleWithAttribute);

            // If there is only one person left, they must be the mystery person
            if (people.size() == 1) {
                break;
            }

            // Increase question number for the next iteration
            questionNumber++;
        }

        // Guess the mystery person
        System.out.println("Who do you think the mystery person is? Please type the name of the person:");
        String guessName = scanner.nextLine();

        boolean correctGuess = false;
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(guessName)) {
                correctGuess = true;
                break;
            }
        }

        // Check if the guess is correct
        if (correctGuess) {
            System.out.println("Congratulations, you guessed correctly!");
        } else {
            System.out.println("Sorry, the mystery person was " + people.get(subjectIndex) + ".");
        }
    }

    private static Person createPerson(String name, int attributeCount) {
        Person person = new PersonA(name, attributeCount);
        List<Integer> availableAttributes = new ArrayList<>();
        for (int i = 0; i < attributeCount; i++) {
            availableAttributes.add(i);
        }
        Collections.shuffle(availableAttributes);
        for (int i = 0; i < MAX_ATTRIBUTES_PER_PERSON; i++) {
            int attribute = availableAttributes.get(i);
            attributeOccurrences[attribute]++;
            person.setAttribute(attribute, true);
        }
        return person;
    }
}
 