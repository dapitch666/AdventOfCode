import re
import requests
import browser_cookie3
import csv
import os

years = range(2016, 2025)
results = []

for year in years:
    for i in range(1, 26):
        url = f"https://adventofcode.com/{year}/day/{i}"
        cj = browser_cookie3.chrome()
        response = requests.get(url, cookies=cj)
        if response.status_code == 200:
            content = response.text
            matches = re.findall(r'Your puzzle answer was <code>(.*?)</code>', content)
            results.append([year, f"Day{i}", "part1", matches[0]])
            if len(matches) >= 2:
                results.append([year, f"Day{i}", "part2", matches[1]])
            else:
                results.append([year, f"Day{i}", "part2", "Merry Christmas!"])
        else:
            print(f"Error while retrieving {url}")

# Define the path to the resources directory
resources_dir = os.path.join(os.path.dirname(__file__), '../src/main/resources')
os.makedirs(resources_dir, exist_ok=True)

# Save the results to a CSV file in the resources directory
with open(os.path.join(resources_dir, "answers.csv"), "w", newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["year", "day", "part", "answer"])
    writer.writerows(results)
