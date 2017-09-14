import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private int size; // number of elements in array
    private Node head;
    private Node tail;

    private class Node
    {
        final Item item;
        private Node next;
        private Node prev;

        private Node(Item item)
        {
            this.item = item;
        }
    }

    /**
     * Initializes an empty deque
     */
    public Deque()
    {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * @return true if deque is empty
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * @return number of items in the deque
     */
    public int size()
    {
        return size;
    }

    /**
     * Adds an item to the front of the deque
     */
    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }

        Node temp = new Node(item);

        if (isEmpty())
        {
            head = temp;
            tail = temp;
        } else {
            temp.next = head;
            head.prev = temp;
            head = temp;
            
        }
        size++;
    }

    /**
     * Adds an item to the end of the deque
     */
    public void addLast(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }

        Node temp = new Node(item);

        if (isEmpty())
        {
            tail = temp;
            head = temp;
        } else {
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }

        size++;
    }

    /**
     * Remove and return the item from the front
     * 
     * @return first item of deque
     */
    public Item removeFirst()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        Item temp = head.item;

        if (size == 1)
        {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        
        size--;

        return temp;
    }

    /**
     * Remove and return the item from the end
     * 
     * @return last item of deque
     */
    public Item removeLast()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        Item temp = tail.item;

        if (size == 1)
        {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        
        return temp;
    }

    /**
     * Return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node curr = head;

        public boolean hasNext()
        {
            return curr != null;
        }

        public Item next()
        {
            if (curr == null)
            {
                throw new NoSuchElementException();
            }

            Item item = curr.item;
            curr = curr.next;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args)
    {
        Deque<Integer> arr = new Deque<Integer>();
        arr.addLast(1);
        // arr.addFirst(2);
        // arr.addLast(3);
        // arr.addLast(5);
        // arr.removeLast();
        arr.removeFirst();
        Iterator<Integer> arriter = arr.iterator();

        while (arriter.hasNext())
        {
            System.out.print(arriter.next() + " ");
        }
    }
}
