import os
import requests
import browser_cookie3

def get_input(year, day):
    print(f"Retrieving input for {year} Day {day}")
    cj = browser_cookie3.chrome()
    url = f"https://adventofcode.com/{year}/day/{day}/input"
    response = requests.get(url, cookies=cj)
    if response.status_code == 200:
        return response.text
    else:
        print(f"Error while retrieving {url}")
        return ""

def save_input(year, day, input_data):
    resources_dir = os.path.join(os.path.dirname(__file__), f'../src/main/resources/aoc{year}')
    os.makedirs(resources_dir, exist_ok=True)
    input_file = os.path.join(resources_dir, f'day{day}.txt')
    with open(input_file, "w") as file:
        file.write(input_data)

def main():
    years = [2019, 2020]
    for year in years:
        for day in range(1, 26):
            input_data = get_input(year, day)
            if input_data:
                save_input(year, day, input_data)

if __name__ == "__main__":
    main()