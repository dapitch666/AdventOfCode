import re
import requests
import browser_cookie3
import csv
import os


def fetch_puzzle_answers(year, day, cookies):
    url = f"https://adventofcode.com/{year}/day/{day}"
    response = requests.get(url, cookies=cookies)
    if response.status_code == 200:
        matches = re.findall(r'Your puzzle answer was <code>(.*?)</code>', response.text)
        return matches if len(matches) >= 2 else matches + ["Merry Christmas!"]
    else:
        print(f"Error while retrieving {url}")
        return []


def main():
    years = range(2015, 2025)
    results = []
    cookies = browser_cookie3.chrome()

    for year in years:
        for day in range(1, 26):
            answers = fetch_puzzle_answers(year, day, cookies)
            if answers:
                results.append([year, f"Day{day}", *answers])

    resources_dir = os.path.join(os.path.dirname(__file__), '../src/main/resources')
    os.makedirs(resources_dir, exist_ok=True)

    with open(os.path.join(resources_dir, "answers.csv"), "w", newline='') as file:
        writer = csv.writer(file)
        writer.writerow(["year", "day", "part1", "part2"])
        writer.writerows(results)


if __name__ == "__main__":
    main()
