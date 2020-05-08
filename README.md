# CMU-95_771-Data-Structures-and-Algorithms-for-Information-Processing
This repository is for my 95_771 in CMU.
------------------------------------------------------------------------------------
Project1 LinkedList & Merkle-Hellman Knapsack Crytosystem & Merkle Tree
First, I built linked list data structure using Michael Main’s ObjectNode.java. Second, I used the linked list class created in Part 1 to implement Merkle-Hellman Knapsack cryptography. Third, I used the same linked list to build a Merkle Tree.
------------------------------------------------------------------------------------
Project2 Queues, Red Black Trees and Dynamic Programming
First, I wrote a spell checker that is based on a red black tree. The execution of the program, when run with shortWords.txt as a command line argument, will appear as follows:
	Legal commands are: 
	d   display the entire word tree with a level order traversal.
	s    print the words of the tree in sorted order (using an inorder traversal).
	r    print the words of the tree in reverse sorted order (reverse inorder traversal). 
	c   <word> to spell check this word.
	a   <word> add this word to tree.
	f   <fileName> to check this text file for spelling errors.
	i   display the diameter of the tree.
	m  view this menu.
	!   to quit.
Second, I played with some fundamental ideas behind dynamic programming.
------------------------------------------------------------------------------------
Project3 Stacks, Red Black Trees and Reverse Polish Notation (RPN)
First, I built stack data structure with multible functions.
Second, I linked red black tree and files that needs to be stored.
Third, I wrote a class called ReversePolishNotation.java that reads and then evaluates postfix expressions involving BigIntegers and variables using red black tree as storage structure.
------------------------------------------------------------------------------------
Project4 The traveling Salesperson Problem (TSP), Minimum Spanning Trees (MST), Heaps and Graphs
First, I used an approximation algorithm to solve TSP.
	Approx-TSP-Tour(G,c)
	1. Select a vertex r to be a root vertex
	2. Compute a minimum spanning tree T for G from root r using MST-Prim(G,c,r)
	3. Let L be the list of vertices visited in a preorder tree walk of T
    4. Return the Hamiltonian cycle H that visits the vertices in the order L
Second, finding an optimal solution to TSP.
Third, displaying the output to Google Earth.
------------------------------------------------------------------------------------
Project5 Lempel-Ziv Welch Compression
I wrote a Java implementation of the Lempel-Ziv Wech compression algorithm. My program is able to compress and decompress ASCII and binary files.
	Efficiency: Compression Ratio = After zipped size / Before zipped size ( the less the better)
	shortwords.txt
		Before: 50 bytes; After: 57 bytes;
		Compression Ratio: 114.0 %
		Reason: The file is too small
	CrimeLatLonXY.csv
		Before: 2608940, bytes; After: 1284804 bytes;
		Compression Ratio: 49.2 %
	words.html
		Before: 1070450 bytes; After: 2493531 bytes;
		Compression Ratio: 42.9 %
	01_Overview.mp4
		Before: 25008838 bytes; After: 33771266 bytes;
		Compression Ratio: 135.0%
------------------------------------------------------------------------------------
Project6 Turing Machine
First, I wrote a Java program that simulates a Turing Machine. Second, I designed transcations to achieve proper subtraction m – n. Third, I designed transcations to read a series of zeroes and ones and decides the language L = {0^n*1^n, n >= 1}.
------------------------------------------------------------------------------------