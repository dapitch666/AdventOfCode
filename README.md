# Advent Of Code solutions in Java

This repository contains my solutions for the [Advent of Code challenge](https://adventofcode.com/), implemented in Java.
No external libraries are used, only the standard Java libraries. The code is written in a way that it should be easy to understand and follow, even though it is not commented (that's a very bad practice, I know). I try to keep the execution times under 1 second for each puzzle.

[![Build and test](https://github.com/dapitch666/AdventOfCode/actions/workflows/maven.yml/badge.svg)](https://github.com/dapitch666/AdventOfCode/actions/workflows/maven.yml)

## Advent of Code stars
The goal of the challenge is to collect stars by solving puzzles. There are two puzzles per day, each with two parts. For each part, a star is awarded. The puzzles are released daily at midnight EST (UTC-5) from December 1st to December 25th.
The following badges show the number of stars obtained in the Advent of Code challenge for each year. The number of stars is updated daily during the challenge.

### Current year
![](https://img.shields.io/badge/2025-0%20⭐-teal)

### Previous years
![](https://img.shields.io/badge/2015-34%20⭐-teal)
![](https://img.shields.io/badge/2016-50%20⭐-teal)
![](https://img.shields.io/badge/2017-50%20⭐-teal)
![](https://img.shields.io/badge/2018-50%20⭐-teal)
![](https://img.shields.io/badge/2019-50%20⭐-teal)
![](https://img.shields.io/badge/2020-50%20⭐-teal)
![](https://img.shields.io/badge/2021-50%20⭐-teal)
![](https://img.shields.io/badge/2022-50%20⭐-teal)
![](https://img.shields.io/badge/2023-50%20⭐-teal)
![](https://img.shields.io/badge/2024-50%20⭐-teal)

## Project Structure

The project is structured into different packages and classes. Each package represent a year and contains a class for each advent calendar day. Each class contains methods for solving the puzzles of its respective day.

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

## Use your own puzzle input

Input files are stored in a separate private repository, in compliance with AOC [rules](https://www.reddit.com/r/adventofcode/wiki/faqs/copyright/inputs/). If you want to use your own input, put them in the resources folder: `src/main/resources/aoc<YearNumber>/day<DayNumber>.txt`.

## Tests
### Unit tests

Unit tests for each day's solutions are located in the `src/test/java/org/anne` directory. They are mostly based on the examples provided in the puzzle descriptions.

To run the tests, navigate to the root directory of the project and execute the following command:

```bash
mvn test
```

### Large tests

Large tests ensure the correctness of each puzzle solution using the unique user input files. They are located in the `src/test/java/org/anne` directory. The tests are disabled by default, as they are not meant to be run on every build. To run the large tests, navigate to the root directory of the project and execute the following command:

```bash
mvn test -P large-tests
```

Large tests are automatically triggered in GitHub Actions when code is pushed to the main branch or when a pull request is created targeting the main branch. The GitHub Actions workflow is defined in the `.github/workflows/maven.yml` file.

To run the large tests for your own input, add a csv file named `answers.csv` at the root of the `resources` directory (either in the `main` or `test` package) with the following format:

```csv
year,day,part1,part2
2015,1,answer1,answer2
2015,2,answer1,answer2
...
``` 

This file can also be generated by running the `generate_answers.py` script located in the `scripts` directory.

During development, you can run the large tests for a specific set of days by adding the solutions in the `LargeTests` class in the `src/test/java/org/anne` directory.


## Adding a new puzzle

Run the python script `create_day.py` to create all the files needed for a day. The script takes two arguments: the year and the day. For example, to create a new solution for day 15 of 2019, run the following command:

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