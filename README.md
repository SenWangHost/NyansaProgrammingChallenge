# NyansaProgrammingChallenge
## Instructions to build and run
There are simply two steps to follow to run the java solution.

1. javac Solution.java
2. java Solution [filename]

## Brief overview:
My entire solution is consist of three Java class.

The **Solution** java class is the main function where we read the file line by line using BufferReader and process each line.

The **DataCollector** java class will receive one line read from file, parse out the date and url and put the url into DailyDataCollector for further processing. Here, I use hashmap for mapping one date to its corresponding DailyDataCollector and priorityqueue for maintaining the order of date.

The **DailyDataCollector** java class uses the double linked list and hashmap to achieve O(1) time complexity for maintaining the sorted-by-frequency order. each double linked list's node keeps track of the frequency and a set of urls that matched with that frequency. Since every time when add url to DailyDataCollector, new node will be created and url will be 'bubble up' to the next node. And by using double linked list, all those operations will be O(1) time complexity, which achieve total of O(1) time complexity for maintaining order by frequency.

Because of the usage of priority queue for maintaining the order of date and DailyDataCollector is O(1) for adding url. Thus, the total time complexity for adding one line to DataCollector would be O(log(n)) where n is the size of priority queue.

More details are covered in the comments of source code.