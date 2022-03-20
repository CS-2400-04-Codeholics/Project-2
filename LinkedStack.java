import java.util.EmptyStackException;
/**
 * Linked Stack
 * A class of stacks whose entries are stored in a chain of nodes.
 * @author Ian Atkinson
 */
public class LinkedStack<T> implements StackInterface<T>
{

    private Node topNode; // references the first node in the chain

    /**
     * Adds a new node index to the top of the stack.
     */
    @Override
    public void push(T newEntry)
    {
        topNode = new Node(newEntry, topNode);
    } // end push

    /**
     * Removes top node from the stack.
     */
    @Override
    public T pop()
    {
        T top = peek();
        topNode = topNode.getNextNode();
        return  top;
    } // end pop

    /**
     * Retrieves top node of the stack.
     */
    @Override
    public T peek()
    {
        if (isEmpty())
        {
            throw new EmptyStackException();
        }
        else
        {
            return topNode.getData();
        }
    } // end peek

    /**
     * Empties the top nodes.
     */
    @Override
    public boolean isEmpty()
    {
        return topNode == null;
    } // end isEmpty

    /**
     * Clears entry top nodes.
     */
    @Override
    public void clear()
    {
        topNode = null;
    } // end clear

    /**
     * Converts an infix expression to an equivalent postfix expression.
     * @param infix The expression that is being converted.
     * @return The equivalent postfix expression.
     */
    @Override
    public String convertToPostFix(String infix)
    {
        LinkedStack<Character> operatorStack = new LinkedStack<>();
        StringBuilder postfixBuilder = new StringBuilder();
        String postfix;

        int counter = 0;
        char nextChar = infix.charAt(counter);

        while (counter != infix.length())
        {
            switch (nextChar)
            {
                case 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z':
                    postfixBuilder.append(nextChar);
                    if (counter + 1 != infix.length())
                    {
                        counter++;
                        nextChar = infix.charAt(counter);
                    }
                    else
                    {
                        counter++;
                        nextChar = infix.charAt(counter - 1);
                    }
                    break;

                case '^','(':
                    operatorStack.push(nextChar);
                    counter++;
                    nextChar = infix.charAt(counter);
                    break;

                case '+','-','*','/':
                    while (!operatorStack.isEmpty() && (precedence(nextChar) <= precedence(operatorStack.peek())))
                    {
                        postfixBuilder.append(operatorStack.peek());
                        operatorStack.pop();
                    }
                    operatorStack.push(nextChar);
                    counter++;
                    nextChar = infix.charAt(counter);
                    break;

                case ')':
                    char topOperator = operatorStack.pop();
                    while (topOperator != '(')
                    {
                        postfixBuilder.append(topOperator);
                        topOperator = operatorStack.pop();
                    }
                    if (counter + 1 != infix.length())
                    {
                        counter++;
                        nextChar = infix.charAt(counter);
                    }
                    else
                    {
                        counter++;
                        nextChar = infix.charAt(counter - 1);
                    }
                    break;

                default:
                    counter++;
                    nextChar = infix.charAt(counter);
                    break;
            }
        }

        while (!operatorStack.isEmpty())
        {
            char topOperator = operatorStack.pop();
            postfixBuilder.append(topOperator);
        }

        postfix = postfixBuilder.toString();

        return postfix;
    }

    private int precedence(char operator)
    {

        int precedenceValue;

        switch (operator)
        {
            case '+','-':
            {
                precedenceValue = 1;
                break;
            }

            case '*','/':
            {
                precedenceValue = 2;
                break;
            }

            case '^':
            {
                precedenceValue = 3;
                break;
            }

            default:
            {
                precedenceValue = 0;
                break;
            }
        }
        return precedenceValue;
    }

    @Override
    public Integer evaluatePostFix(String postfix)
    {
        return null;
    }

    private class Node
    {
        private T data; // entry in stack
        private Node next; // link to next node

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        private T getData()
        {
            return data;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }

    }
}
