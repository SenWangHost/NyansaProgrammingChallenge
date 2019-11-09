import java.util.*;
/**
 * This is the class for collecting data for each day, in order to preserve the sorted-by-frequency
 * data structure in O(1), we use double linked list and hashmap to achieve it.
 */
public class DailyDataCollector {
    /**
     * the double linked list for keeping tracking of frequency and the urls that has the
     * same frequency with each other
     */
    private DoubleLinkedList list;
    /**
     * the hashmap for mapping url to its corresponding node
     */
    private Map<String, Node> map;
    /**
     * constructor for daily data collector, for initializing the data structure
     */
    public DailyDataCollector() {
        list = new DoubleLinkedList();
        map = new HashMap<String, Node>();
    }
    /**
     * add the url to the collector, since we use double linked list and hashmap, finding the node and
     * populating node up to next frequency is O(1) operation
     * @param inUrl
     */
    public void addRecord(String inUrl) {
        if (!map.containsKey(inUrl)) {
            if (list.isEmpty() || list.tail.prev.freq != 1) {
                // create a new node with frequency 1
                Node newNode = new Node();
                newNode.freq = 1;
                newNode.urls.add(inUrl);
                list.insertBefore(list.tail, newNode);
                map.put(inUrl, newNode);
            } else {
                // add the url to the last node's url collection
                Node lastNode = list.tail.prev;
                lastNode.urls.add(inUrl);
                map.put(inUrl, lastNode);
            }
        } else {
            Node curr = map.get(inUrl);
            if (curr.next == list.head || curr.next.freq != curr.freq + 1) {
                // insert a new node with freq plus one to the dll
                Node newNode = new Node();
                newNode.freq = curr.freq + 1;
                newNode.urls.add(inUrl);
                list.insertBefore(curr, newNode);
                map.put(inUrl, newNode);
            } else {
                // inser the urls to the next node of current node
                curr.next.urls.add(inUrl);
                map.put(inUrl, curr.next);
            }
            // remove url from current node and add it to the next node
            curr.urls.remove(inUrl);
            // remove the current node if there is no urls inside it
            if (curr.urls.size() == 0) {
                list.removeNode(curr);
            }
        }
    }
    /**
     * print the urls starting from the highest frequency to the lowest frequency, the urls
     * with the same frequency will be printed out as the inserted order
     */
    public void printDailyRecords() {
        Node curr = list.head.next;
        while (curr != list.tail) {
            for (String url : curr.urls) {
                System.out.println(url + " " + curr.freq);
            }
            curr = curr.next;
        }
    }
    /**
     * The double linked list data structure
     */
    private class DoubleLinkedList {
        /**
         * the head of the double linked list
         */
        private Node head;
        /**
         * the tail of the double linked list
         */
        private Node tail;
        /**
         * constructor for the double linked list
         */
        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }
        /**
         * insert a new node before a node in the list
         * @param inNode
         */
        public void insertBefore(Node inNode, Node inToBeInserted) {
            Node prevNode = inNode.prev;
            inNode.prev = inToBeInserted;
            inToBeInserted.next = inNode;
            prevNode.next = inToBeInserted;
            inToBeInserted.prev = prevNode;
        }
        /**
         * Remove a node from double linked list
         * @param inNode
         */
        public void removeNode(Node inNode) {
            Node nextNode = inNode.next;
            Node prevNode = inNode.prev;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            inNode.next = null;
            inNode.prev = null;
        }
        /**
         * check whether the double linked list is empty or not
         */
        public boolean isEmpty() {
            return head.next == tail;
        }
    }
    /**
     * This is the class for Node in DoubleLinkedList
     */
    private class Node {
        /**
         * The frequency for the urls
         */
        public int freq;
        /**
         * a hash set for containing all urls with the same frequency, the reason
         * to use linked hash set here is that we can remove one url in O(1) and when we 
         * add url, we preserve the order of addition
         */
        public LinkedHashSet<String> urls;
        /**
         * The reference to the next node
         */
        public Node next;
        /**
         * The reference to the previous node
         */
        public Node prev;
        /**
         * constructor for the node
         */
        public Node() {
            freq = 0;
            urls = new LinkedHashSet<String>();
            next = null;
            prev = null;
        }
    }
}