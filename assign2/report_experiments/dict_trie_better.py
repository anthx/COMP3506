import re
import json
import time


class Trie:

    def __init__(self):
        self.dict_trie = {'children':{}}

    def add(self, word: str, occurrences: tuple):
        head = self.dict_trie
        curr = head
        for c in word:
            if c not in curr['children']:
                curr[c] = {'children':{}, 'character':c}
            curr = curr[c]
        curr['occurrences'] = []

def search(trie, s):
    if not trie:
        return True
    for c in s:
        try:
            trie = trie[c]
        except KeyError:
            return False
        if not trie:
            return True
    return False

start = time.time()
word_list = []
f = open('shakespeare.txt', 'r', errors='ignore')
for line in f.readlines():
    word_list.extend(re.split(" ", line.strip()))
f.close()
end = time.time()
print((end - start)*1000)

start = time.time()
built_trie = build_trie(word_list)
end = time.time()
with open("trie_file.json", "w") as write_file:
    json.dump(built_trie, write_file)

print((end - start)*1000)

start = time.time()
with open("trie_file.json", "r") as read_file:
    trie = json.load(read_file)
end = time.time()
print((end - start)*1000)
print(search(trie, "the"))
