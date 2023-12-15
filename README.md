# Advent Of Code solutions in Java

This repository contains my solutions for the [Advent of Code challenge](https://adventofcode.com/), implemented in Java.
I don't use any external libraries, only the standard Java libraries. The code is written in a way that it should be easy to understand and follow, even though it is not commented (that's a very bad practice, I know). I try to keep the execution times under 1 second for each puzzle.

[![Build and test](https://github.com/dapitch666/AdventOfCode/actions/workflows/maven.yml/badge.svg)](https://github.com/dapitch666/AdventOfCode/actions/workflows/maven.yml)

## Project Structure

The project is structured into different packages and classes. Each package represent a year and contains a class for each advent calendar day. The classes contain methods for solving the puzzles of each day.

The MainYYYY classes in this project are the entry points for running the Advent of Code solutions for a specific year. Each class corresponds to a specific year, as indicated by YYYY in the class name.

## Running the Code

To run the code for a specific day, navigate to the root directory of the project and execute the following command:

```bash
mvn exec:java -Dexec.mainClass="org.anne.aoc<YearNumber>.Day<DayNumber>"
```

Replace `<YearNumber>` and `<DayNumber>` with the year and the day you want to run. For example, to run the code for 2019, 15th, the command would be:

```bash
mvn exec:java -Dexec.mainClass="org.anne.aoc2019.Day15"
```

To execute the code for all days of a year, run the following command:

```bash
mvn exec:java -Dexec.mainClass="org.anne.aoc<YearNumber>.Main<YearNumber>"
```

## Testing the Code

Unit tests for each day's solutions are located in the `src/test/java/org/anne` directory.

To run the tests, navigate to the root directory of the project and execute the following command:

```bash
mvn test
```

## Use your own puzzle input

If you want to change the inputs with your puzzle input, change the contents of the files from `src/main/resources/aoc<YearNumber>/day<DayNumber>.txt`.

## Dependencies

This project is built with Maven and uses Java 17. Tests are written with JUnit 5.

## Contributing

This is a personal project for educational purposes. Contributions are not currently being accepted.

## License

This project is licensed under the MIT License.