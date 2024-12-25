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

Input files are stored in a separate private repository, in compliance with AOC [rules](https://www.reddit.com/r/adventofcode/wiki/faqs/copyright/inputs/). If you want to use your own input, put them in the resources folder: `src/main/resources/aoc<YearNumber>/day<DayNumber>.txt`

## Adding new solutions

Run the python script `create_day.py` to create a new solution for a day. The script takes two arguments: the year and the day. For example, to create a new solution for day 15 of 2019, run the following command:

```bash
python create_day.py 201915
```

Don't provide any argument to the script to create a new solution for the current day.

The script also takes care of creating the year package if it doesn't exist yet.

## Dependencies

This project is built with Maven and uses Java 23. Tests are written with JUnit 5.

## Contributing

This is a personal project for educational purposes. Contributions are not currently being accepted.

## License

This project is licensed under the MIT License.