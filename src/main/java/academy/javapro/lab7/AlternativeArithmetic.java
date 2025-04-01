package academy.javapro.lab7;

public class AlternativeArithmetic {
    public static int addWithoutPlus(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }

    public static int divideWithoutDivideOperator(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }

        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        int result = 0;
        while (absDividend >= absDivisor) {
            long temp = absDivisor, multiple = 1;
            while (absDividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            absDividend -= temp;
            result += multiple;
        }

        return isNegative ? -result : result;
    }

    public static int subtractWithoutMinusOperator(int a, int b) {
        return addWithoutPlus(a, ~b + 1);
    }

    /**
     * Test all implementations
     */
    public static void main(String[] args) {
        // Test cases for addition
        int[][] additionTests = {
                {5, 3},     // 8
                {-2, 7},    // 5
                {0, 0},     // 0
                {-5, -3},   // -8
                {100, 200}, // 300
                {Integer.MAX_VALUE, 1}, // Edge case: handling overflow
                {-100, 100} // Additional: adding to zero
        };

        System.out.println("Testing addition without '+' operator:");
        for (int[] test : additionTests) {
            int result = addWithoutPlus(test[0], test[1]);
            int expected = test[0] + test[1];
            System.out.println(test[0] + " + " + test[1] + " = " + result +
                    (result == expected ? " (Correct)" : " (Incorrect, expected " + expected + ")"));
        }

        // Test cases for division
        int[][] divisionTests = {
                {10, 2},    // 5
                {15, 3},    // 5
                {8, 4},     // 2
                {7, 2},     // 3 (integer division)
                {100, 10},  // 10
                {-15, 3},   // -5 (negative dividend)
                {15, -3},   // -5 (negative divisor)
                {0, 5},     // 0 (dividend is 0)
                {1024, 2}   // 512 (power of 2 divisor)
        };

        System.out.println("\nTesting division without '/' operator:");
        for (int[] test : divisionTests) {
            try {
                int result = divideWithoutDivideOperator(test[0], test[1]);
                int expected = test[0] / test[1];
                System.out.println(test[0] + " / " + test[1] + " = " + result +
                        (result == expected ? " (Correct)" : " (Incorrect, expected " + expected + ")"));
            } catch (ArithmeticException e) {
                System.out.println(test[0] + " / " + test[1] + " = " + e.getMessage());
            }
        }

        // Test cases for bonus subtraction
        int[][] subtractionTests = {
                {10, 3},    // 7
                {5, 8},     // -3
                {0, 0},     // 0
                {-5, -3},   // -2
                {100, 50}   // 50
        };

        System.out.println("\nTesting subtraction without '-' operator:");
        for (int[] test : subtractionTests) {
            int result = subtractWithoutMinusOperator(test[0], test[1]);
            int expected = test[0] - test[1];
            System.out.println(test[0] + " - " + test[1] + " = " + result +
                    (result == expected ? " (Correct)" : " (Incorrect, expected " + expected + ")"));
        }
    }
}