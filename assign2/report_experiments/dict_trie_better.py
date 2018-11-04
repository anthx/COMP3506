import re
import json
import time


class Occurrence(object):
    def __init__(self, word, line_number, column_number):
        self.word = word
        self.line_number = line_number
        self.column_number = column_number

    def getWord(self):
        return self.word

    def getStartingColumn(self):
        return


class Trie:

    def __init__(self, existing=None):
        self.dict_trie = {'children':{}}
        if existing is not None:
            self.dict_trie = existing

    def add(self, word: str, occurrences: tuple):
        head = self.dict_trie
        curr = head
        for c in word:
            if c not in curr['children']:
                curr['children'][c] = {'children':{}, 'character':c}
            curr = curr['children'][c]
        if 'occurrences' not in curr.keys():
            curr['occurrences'] = []
        curr['occurrences'].append(occurrences)

    def get_dict(self) -> dict:
        return self.dict_trie


    def search(self, string):
        head = self.dict_trie
        curr = head
        if not self.dict_trie:
            return True
        for c in string:
            try:
                curr = self.dict_trie['children']
            except KeyError:
                return False
            if not self.dict_trie:
                return True
        return False


def processLine(line: str, lineNumber: int):
    words:list = line.lower().split("[ -]")
    result:list = []
    startingColumn = 0
    startingSearchColumn = 0
    for word in words:
        TrimmedWword = re.sub("[^a-zA-Z]+", "", word)
        startingColumn = line.lower().find(TrimmedWword, startingSearchColumn)
        occurence = Occurrence(TrimmedWword, lineNumber, startingColumn + 1)
        startingSearchColumn =+ word.__len__() + startingColumn
        result.append(occurence)

    return result


start = time.time()
built_trie = Trie()
f = open('testThe.txt', 'r', errors='ignore')
lineNumber = 0
for line in f.readlines():
    wordsOnLine: list = processLine(line, lineNumber)
    for word in wordsOnLine:
        if wordsOnLine.__len__() > 0:
            for wol in wordsOnLine:
                built_trie.add(wol.getWord(), (lineNumber, wol.getStartingColumn()) )
        lineNumber += 1
        # built_trie.add(word.getWord, (1, 2) )

f.close()
end = time.time()
print("load and build trie",(end - start)*1000, "ms")

start = time.time()
with open("trie_file.json", "w") as write_file:
    json.dump(built_trie.get_dict(), write_file)
end = time.time()
print("write out", (end - start)*1000, "ms")

start = time.time()
with open("trie_file.json", "r") as read_file:
    built_trie = Trie(json.load(read_file))
end = time.time()
print((end - start)*1000, "ms")
# print(built_trie.search(trie, "the"))
