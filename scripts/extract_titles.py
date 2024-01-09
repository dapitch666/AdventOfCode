import re
import requests
import sys
from bs4 import BeautifulSoup

year = sys.argv[1]
for i in range(1, 26):
    url = "https://adventofcode.com/" + year + "/day/" + str(i)
    response = requests.get(url)
    if response.status_code == 200:
        soup = BeautifulSoup(response.content, "html.parser")
        node_text = soup.find('h2').get_text()
        result = re.search(r'--- Day [0-9]+: (.*) ---', node_text)
        if result:
            res = "day.setName(\"{}\");".format(result.group(1))
            print(res)
        else:
            print(node_text)
    else:
        print("Error while retrieving", url)
