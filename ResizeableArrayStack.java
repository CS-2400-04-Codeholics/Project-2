import java.util.EmptyStackException;
import java.util.Arrays;
/**
 * Resizeable Array Stack
 * A class of stacks whose entries are stored in an array.
 * @author Miguel Sierra
 */
public final class ResizeableArrayStack<T> implements StackInterface<T>
{
    private T[] stack;  //Array of stack entries
    private int topIndex; //Index of top entry
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizeableArrayStack()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public ResizeableArrayStack(int initialCapacity)
    {
        integrityOK = false;
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } // end constructor

    /**
     * Adds a new entry on the very top of the stack.
     */
    public void push(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } // end push

    private void ensureCapacity()
    {
        if (topIndex >= stack.length -1) // if array is full, double its size
        {
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        } // end if
    } // end ensure Capacity

    /**
     * Removes the top most entry of the stack
     */
    public T pop()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        } // end if
    } // end pop

    /**
     * Retrieving the top entry of the stack.
     */
    public T peek()
    {
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } // end peek

    /**
     * Checks if top entry is empty.
     */
    public boolean isEmpty()
    {
        return topIndex < 0;
    } // end isEmpty

    /**
     * Clears the top entry if empty.
     */
    public void clear()
    {
        checkIntegrity();

        // Remove references to the objects in the stack,
        // but do not deallocate the array
        while(topIndex > -1)
        {
            stack[topIndex] = null;
            topIndex--;
        } // end while
        // Assertion: topIndex is -1
    } // end clear

    /**
     * This checks if the input is invalid or not.
     */
    private void checkIntegrity()
    {
        if(!integrityOK)
        {
            throw new SecurityException("INVALID INPUT!!!");
        }
    }

    /**
     * This checks if the given capacity is under the max limit
     * and throws an invalid message.
     * @param capacity The capacity of the stack.
     */
    private void checkCapacity(int capacity)
    {
        if(capacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("YOUR STACK CAPACITY IS TOO BIG!!!");
        }
    }

    public String convertToPostFix(String infix)
    {
        return null;
    }

    /**
     * Evaluates a postfix expression.
     * @param postfix The expression that is being evaluated.
     * @return The final value of the stack.
     */
    public Integer evaluatePostFix(String postfix)
    {
        ResizeableArrayStack<Integer> valueStack = new ResizeableArrayStack<>();
        int i = 0;
        while(i < postfix.length())
        {
            char character = postfix.charAt(i);

            if(Character.isDigit(character))
            {
                valueStack.push(character - '0');
            }
            else
            {
                int operandOne = valueStack.pop();
                int operandTwo = valueStack.pop();

                switch(character)
                {
                    case '+':
                        valueStack.push(operandTwo + operandOne);
                        break;
                    case '-':
                        valueStack.push(operandTwo - operandOne);
                        break;
                    case '*':
                        valueStack.push(operandTwo * operandOne);
                        break;
                    case '/':
                        valueStack.push(operandTwo / operandOne);
                        break;
                    case '^':
                        valueStack.push(operandTwo ^ operandOne);
                        break;
                    default :
                        break;
                }
            }
            i++;
        }
        return valueStack.peek();
    }
}