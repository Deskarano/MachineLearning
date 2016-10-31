package ml.function;


public abstract class Function
{
    private static final String[] OPS = {"+", "-", "*", "/", "sin", "cos", "tan", };
    private String name;
    private String signature;
    private String[] args;

    Function(String init)
    {
        boolean hasName = false;
        boolean hasArgs = false;
        boolean hasEquals = false;
        boolean containsCorrectEquation = false;


    }

    public String getSignature()
    {
        return signature;
    }

    public String[] getArgs()
    {
        return args;
    }
}
