import sys
import datetime
import os
import re
import requests
from bs4 import BeautifulSoup
import browser_cookie3


def get_title(year, day):
    print("Retrieving title from adventofcode.com")
    this_month = datetime.datetime.now().month
    this_year = datetime.datetime.now().year
    if int(year) < 2015 or (this_month == 12 and int(year) > this_year) or (this_month < 12 and int(year) > this_year - 1):
        print("No title available for year", year)
        return ""
    url = "https://adventofcode.com/" + year + "/day/" + str(day)
    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, "html.parser")
        node_text = soup.find('h2').get_text()
        title = re.search(r'--- Day [0-9]+: (.*) ---', node_text)
        if title:
            return title.group(1)
        else:
            return title
    else:
        print("Error while retrieving", url)


def get_input(year, day):
    print("Retrieving input from adventofcode.com")
    cj = browser_cookie3.chrome()
    url = "https://adventofcode.com/" + year + "/day/" + str(day) + "/input"
    response = requests.get(url, cookies=cj)
    if response.status_code == 200:
        return response.text
    else:
        print("Error while retrieving", url)
        return ""


def create_day_class(year, day, src_dir, day_class, name):
    print("Creating Day class inside", src_dir)
    with open(day_class, "w") as file:
        file.write("package org.anne.aoc" + year + ";\n\n")
        file.write("import org.anne.common.Day;\n\n")
        file.write("import java.util.List;\n\n")
        file.write("public class Day" + str(day) + " extends Day {\n")
        file.write("    public static void main(String[] args) {\n")
        file.write("        new Day" + str(day) + "().run();\n")
        file.write("    }\n\n")
        file.write("    @Override\n")
        file.write("    public void execute() {\n")
        file.write("        setName(\"" + name + "\");\n")
        file.write("        List<String> input = readFile();\n")
        file.write("        setPart1(part1(input));\n")
        file.write("        setPart2(part2(input));\n")
        file.write("    }\n\n")
        file.write("    public static int part1(List<String> input) {\n")
        file.write("        return 0;\n")
        file.write("    }\n\n")
        file.write("    public static int part2(List<String> input) {\n")
        file.write("        return 0;\n")
        file.write("    }\n")
        file.write("}\n")


def create_test_class(year, day, test_dir, test_class):
    print("Creating test class inside", test_dir)
    with open(test_class, "w") as file:
        file.write("package org.anne.aoc" + year + ";\n\n")
        file.write("import org.junit.jupiter.api.Test;\n\n")
        file.write("import java.util.Arrays;\nimport java.util.List;\n\n")
        file.write("import static org.junit.jupiter.api.Assertions.assertEquals;\n\n")
        file.write("class Day" + str(day) + "Test {\n\n")
        file.write("    List<String> input = Arrays.asList();\n\n")
        file.write("    @Test\n")
        file.write("    void testPart1() {\n")
        file.write("        assertEquals(0, Day" + str(day) + ".part1(input));\n")
        file.write("    }\n\n")
        file.write("    @Test\n")
        file.write("    void testPart2() {\n")
        file.write("        assertEquals(0, Day" + str(day) + ".part2(input));\n")
        file.write("    }\n")
        file.write("}\n")


def create_main_class(year, main_class):
    with open(main_class, "w") as file:
        file.write("package org.anne.aoc" + year + ";\n\n")
        file.write("import org.anne.common.Year;\n\n")
        file.write("public class Main" + year + " {\n")
        file.write("    public static void main(String[] args) {\n")
        file.write("        new Year(" + year + ").run();\n")
        file.write("    }\n")
        file.write("}\n")


def main():
    if len(sys.argv) == 2:
        date = sys.argv[1]
    else:
        if datetime.datetime.now().month == 12:
            date = datetime.datetime.now().strftime('%Y%d')
        else:
            print("Outside of December, please provide year and day (YYYYDD), exiting")
            exit(1)
    day = int(date[-2:])
    year = date[:4]

    root = os.path.dirname(os.getcwd())
    src_dir = os.path.join(root, "src", "main", "java", "org", "anne", "aoc" + year)
    test_dir = os.path.join(root, "src", "test", "java", "org", "anne", "aoc" + year)
    resources_dir = os.path.join(root, "src", "main", "resources", "aoc" + year)
    main_class = os.path.join(src_dir, "Main" + year + ".java")
    day_class = os.path.join(src_dir, "Day" + str(day) + ".java")
    input_file = os.path.join(resources_dir, "day" + str(day) + ".txt")
    test_class = os.path.join(test_dir, "Day" + str(day) + "Test.java")

    if os.path.exists(day_class):
        print("Day class already exists, exiting")
        exit(1)

    print("Creating day", day, "of year", year)
    name = get_title(year, day)
    if not os.path.exists(src_dir):
        print("Creating src folder", src_dir)
        os.mkdir(src_dir)
    if not os.path.exists(main_class):
        print("Creating Main class", main_class, "inside", src_dir)
        create_main_class(year, main_class)
    if not os.path.exists(test_dir):
        print("Creating test folder", test_dir)
        os.mkdir(test_dir)
    if not os.path.exists(resources_dir):
        print("Creating resources folder", resources_dir)
        os.mkdir(resources_dir)

    if not os.path.exists(day_class):
        print("Creating Day class", day_class, "inside", src_dir)
        create_day_class(year, day, src_dir, day_class, name)
    if not os.path.exists(test_class):
        print("Creating test class", test_class, "inside", test_dir)
        create_test_class(year, day, test_dir, test_class)
    if not os.path.exists(input_file):
        print("Creating input file", input_file, "inside", resources_dir)
        with open(input_file, "w") as file:
            file.write(get_input(year, day))


if __name__ == "__main__":
    main()
