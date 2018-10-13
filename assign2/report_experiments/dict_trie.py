import re
import json
import time

def build_trie(words):
    head = {}
    for word in words:
        curr = head
        for c in word:
            if c not in curr:
                curr[c] = {}
            curr = curr[c]
    return head


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
f = open('shakespeare.txt', 'r')
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
