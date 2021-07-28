import java.util.Scanner;

public class FractionalCalculator
{
    public static String convertToFraction(String fraction)
    {
        if (!fraction.contains("/"))
        { // if the number is just a whole number like 3 add /1
            return fraction + "/1";

        }
        else if (!fraction.contains("_"))
        { // if the number doesn't have a underscore return it as a fraction
            return fraction;
        }
        else
        {
            int underscore = fraction.indexOf('_'); // wherever the Underscore is
            int slash = fraction.indexOf('/'); // wherever the Slash is
            String Whole = fraction.substring(0, underscore); // whatever number between from the beginning to underscore
            // example 2_ it will take the 2
            int whole_Number = Integer.parseInt(Whole); // turn the number into an integer
            String numerator = fraction.substring(underscore + 1, slash); // whatever number between the _ and slash
            // example _5/ it will take out the 5 only
            int numerator_number = Integer.parseInt(numerator); // turn the number into an integer
            String denominator = fraction.substring(slash + 1); // whatever number after the slash example /9 takes the 9 only
            int denominator_number = Integer.parseInt(denominator); // turn the number into an integer
            int New_numerator_number = denominator_number * whole_Number + numerator_number; // mix number example
            // 2_3/4 so we do is 2 * 4 that gives us 8 then we plus the 3 to get 11 for the new Numerator
            // but the denominator stays the same
            if (whole_Number <= 0) // is whole number less than or equal to zero
                New_numerator_number = denominator_number * whole_Number - numerator_number; // negative mix number or
            // zero example -2_2/3 you do is -2 * 3 which gives us -6 then subtract -2 and that gives us -8
            // denominator stays the same
            return New_numerator_number + "/" + denominator_number; // returns the new numerator and denominator example
            // 2_3/4 = 11/4
        }
    }

    public static int Find_Numerator(String fraction)
    {
        int slash = fraction.indexOf("/");// wherever the / is
        String Numerator_Number = fraction.substring(0, slash); // any number before the /
        int Numerator = Integer.parseInt(Numerator_Number); // converts the string to an int
        return Numerator;
    }

    public static int Find_Denominator(String fraction)
    {
        int slash = fraction.indexOf("/");// wherever the / is
        String Denominator_Number = fraction.substring(slash + 1); // any number after the /
        int Denominator = Integer.parseInt(Denominator_Number); // converts the string to an int
        return Denominator;
    }

    public static String calculate(String left, String operator, String right)
    {
        int left_numerator = Find_Numerator(left);// finds the numerator of the string example 1/2 numerator is 1
        int left_denominator = Find_Denominator(left); // finds the denominator of the string example 1/2 denominator is 2
        int right_numerator = Find_Numerator(right);
        int right_denominator = Find_Denominator(right);
        if (operator.equals("+"))
        { // fraction addition
            int new_numerator = left_numerator * right_denominator + left_denominator * right_numerator;
            int new_denominator = left_denominator * right_denominator;
            return new_numerator + "/" + new_denominator;
        }
        else if (operator.equals("-"))
        { // fraction Subtraction
            int new_numerator = left_numerator * right_denominator - left_denominator * right_numerator;
            int new_denominator = left_denominator * right_denominator;
            return new_numerator + "/" + new_denominator;
        }
        else if (operator.equals("*"))
        { // fraction multiplication
            int new_numerator = left_numerator * right_numerator;
            int new_denominator = left_denominator * right_denominator;
            return new_numerator + "/" + new_denominator;
        }
        else
        { // fraction division Keep change flip
            int new_numerator = left_numerator * right_denominator;
            int new_denominator = left_denominator * right_numerator;
            return new_numerator + "/" + new_denominator;
        }
    }

    public static int gcf(int numerator, int denominator)
    {
        int lowestTerm = 1;
        for (int n = 1; n <= numerator; n++)
        {
            if (numerator % n == 0 && denominator % n == 0) // checks the first and second number to see if the same
                                                            // number can go in both
            {
                lowestTerm = n;
            }
        }
        if (numerator <= 0)
        {// numerator is negative or zero
            for (int number_of_times = -1; number_of_times >= numerator; number_of_times--)
            {// checks the first and second number to see if the same number can go in both
                if (numerator % number_of_times == 0 && denominator % number_of_times == 0)
                {
                    lowestTerm = number_of_times * -1; // example -9/3 would equal without this 3/-1 but this makes it
                                                       // equal the correct way -3/1
                }
            }
        }

        return lowestTerm;
    }

    public static String reduce(String fraction) // simplify fraction 1/2 * 2/8 = 1/8
    {
        int numerator = Find_Numerator(fraction);
        int denominator = Find_Denominator(fraction);
        int Reduce_Fraction = gcf(numerator, denominator);
        numerator = numerator / Reduce_Fraction;
        denominator = denominator / Reduce_Fraction;
        return numerator + "/" + denominator;
    }

    public static String convertToMixed(String fraction)
    {
        int numerator = Find_Numerator(fraction);
        int denominator = Find_Denominator(fraction);
        if (numerator > denominator)// is the numerator bigger than the denominator
        {
            int whole_number = numerator / denominator; // gets the whole number example 7/5 the whole number is 1
            int left_over_numerator = numerator % denominator; // 7/5 whole number is 1 and the left over is 2
            if (denominator == 1) // example 15/1 just have as 15 instead
            {
                return numerator + "";
            }
            return whole_number + "_" + left_over_numerator + "/" + denominator; // example 1_2/5 + 5/10 = 1_9/10

        }
        else if (numerator < 0) // Numerator has to be Negative?
        {
            int whole_number = numerator / denominator; // example 7/5 whole number is 1
            int left_over_numerator = numerator % denominator; // 7/5 the left over is 2
            int positive_numerator = left_over_numerator * -1; // without this and you tried to do negative it gives you
                                                               // something like 1_-1/1 or -1_-1/3 the right way -1_1/8
                                                               // only the whole number should be negative
            if (denominator == 1) // 15/1 = 15
            {
                return numerator + "";
            }
            return whole_number + "_" + positive_numerator + "/" + denominator; // 1_1/9 + 1/2 = 1_11/18

        }

        else if (denominator == 1)// 19/1 = 19
        {
            return numerator + "";
        }
        else
        {
            return fraction;// just return as a fraction 
        }
    }

    public static void main(String[] args)
    {
        Scanner fraction = new Scanner(System.in);
        String quit_or_continue = "";
        System.out.println("Welcome to the Fraction Calculator!");
        System.out.print("Enter an expression " + "(or \"quit\"): "); // type the fraction
        quit_or_continue = fraction.next();
        while (!quit_or_continue.equals("quit"))
        {
            String Left_operand = quit_or_continue; // type the first fraction or quit
            Left_operand = convertToFraction(Left_operand); // convert string to fraction
            String operator = fraction.next(); // type the operator of your choice + - / or *
            String Right_operand = fraction.next(); // type the next fraction after you type in the operator
            Right_operand = convertToFraction(Right_operand); // convert string to fraction
            String answer = calculate(Left_operand, operator, Right_operand);
            answer = reduce(answer);
            answer = convertToMixed(answer);
            System.out.println(answer);
            System.out.print("Enter an expression " + "(or \"quit\"): "); // type the fraction
            quit_or_continue = fraction.next(); // does the loop again and again until you enter quit

        }
        System.out.println("Goodbye!"); // when you type quit
        fraction.close();

    }

}
