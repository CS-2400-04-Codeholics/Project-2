/**
 * Calculator
 * Testing both methods from ResizeableArrayStack and LinkStack.
 */
public class Calculator
{
    public static void main(String[] args)
    {
        calculatorCompute();
    }

    public static int calculatorCompute()
    {
        LinkedStack<String> LinkedStackTest = new LinkedStack<>();
        ResizeableArrayStack<Integer> ResizeableArrayStackTest = new ResizeableArrayStack<>();

        String infix = "a*b/(c-a)+d*e";
        String postfix = LinkedStackTest.convertToPostFix(infix);
        String postfixSub = "23*42-/56*+";
        int postfixEval = ResizeableArrayStackTest.evaluatePostFix(postfixSub);

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Postfix Expression with Substituted Variables: " + postfixSub);
        System.out.println("Evaluated Postfix Expression: " + postfixEval);

        return postfixEval;
    }
}