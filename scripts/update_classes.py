import os
import re

# Define the directories and files to be updated
years = ['aoc2019', 'aoc2020', 'aoc2021', 'aoc2022', 'aoc2023', 'aoc2024']
day_classes = [f'Day{i}.java' for i in range(4, 21)]
prefix = '../src/main/java/org/anne/'

# Define the new pattern to replace lines 10 to 16
new_pattern = '''        day.run();
    }

    @Override
    public void execute() {
'''

# Function to update the file content
def update_file_content(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()

    # Replace "day." with "this."
    updated_lines = [line.replace('day.', 'this.') for line in lines]

    # Find the line with the pattern "Day day = new DayXX();"
    for i, line in enumerate(updated_lines):
        if re.search(r'Day day = new Day\d+\(\);', line):
            # Insert the new pattern after this line
            updated_lines.insert(i + 1, new_pattern)
            break

    # Write the updated content back to the file
    with open(file_path, 'w') as file:
        file.writelines(updated_lines)

# Iterate over the directories and update the files
for year in years:
    directory = os.path.join(prefix, year)
    for root, _, files in os.walk(directory):
        for file in files:
            if file in day_classes:
                file_path = os.path.join(root, file)
                update_file_content(file_path)