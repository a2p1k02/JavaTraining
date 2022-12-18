package nodomain.a2p1k02.java.algs;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(100));
    }

//    private static long fib(int n) {
//        if (n <= 1) return n;
//        return fib(n - 1) + fib(n - 2);
//    }

    private static long fib(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++)
            arr[i] = arr[i - 1] + arr[i - 2];
        return arr[n];
    }
}
