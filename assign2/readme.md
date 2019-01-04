
# Readme.md
  
# Justifications

## Trie
I used a trie so that we could search for words that made up other words. A dictionary (HashMap for instance)
doesn't support finding words that are prefixes of other words because the keys are unique and exact. You'd need to try all combinations
of letters to find possible keys to search the dictionary (HashMap) for the list of occurrences. That's silly if we can use a trie which already gives us the children of the prefixes. 

## Linked List
I used a linked list to store occurrences of words inside the trie nodes because it's smaller on average than the ArrayList because you just build one at a time instead of allocating extra space. Mind you it's all null initially but saves processing of copying elements between arrays if that's a concern, it all adds up.

I used a linked list and a simple while loop to test words on each line because it's easy, and works. Using the trie for that would take too much cross-referencing and more operations. Just test each line, since that's really what we're after.

A linked list would also be useful for the phrase occurrence search so you can add each word to the linked list and find contiguous words that make up a phrase. Then you "just" need to check words in order of the phrase. 

# References

LinkedList and ListNode

Thomas, R. (2018). Algorithms and Data Structures.

Trie Implementation

V. Rao Sanaka, "Trie | (Insert and Search)", Geeks For Geeks. [Online]. Available: https://www.geeksforgeeks.org/trie-insert-and-search/. [Accessed: 15- Sep- 2018].