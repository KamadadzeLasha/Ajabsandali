package pgdp.arrays;

import Minijava.MiniJava;

public class ProbabilisticSearch extends MiniJava {
    public static void main(String[] args) {
        int[] example1 = new int[]{6, 20, 22, 35, 51, 54, 59, 74, 77, 80, 87, 94, 97};
        int[] example2 = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 100};
        int[] x = probalisticSearch(example2, 59);

        System.out.println(drawCard());

    }

    public static int[] searchUp(int[] arr, int position, int old_position, int value, int power, int steps) {
        if (arr[position] < value) {
            int jump = (int) Math.pow(2, power);
            if (position + jump > arr.length) {
                return searchUp(arr, arr.length - 1, position, value, power + 1, steps + 1);
            }
            else {
                return searchUp(arr, position + jump, position, value, power + 1, steps + 1);
            }
        }
        else {
            if (arr[position] == value) {
                return new int[]{position, steps};
            }
            else return find0(arr, value, old_position + 1, position - 1, steps);

        }
    }

    public static int[] searchDown(int[] arr, int position, int old_position, int value, int power, int steps) {
        if (arr[position] > value) {
            int jump = (int) Math.pow(2, power);
            if (position - jump < 0) {
                return searchDown(arr, 0, position, value, power + 1, steps + 1);
            }
            else {
                return searchDown(arr, position - jump, position, value, power + 1, steps + 1);
            }
        }
        else {
            if (arr[position] == value) {
                return new int[]{position, steps};
            }
            else return find0(arr, value, position + 1, old_position - 1, steps);
        }
    }

    public static int[] probalisticSearch(int[] arr, int value) {
        int position = Math.round(((float) arr.length - 1) * (float) ((value - arr[0])) / (float) (arr[arr.length - 1] - arr[0]));
        if (position > arr.length - 1 || position < 0) {
            return new int[]{-1, 1};
        }
        else if (arr[position] == value)
            return new int[]{position, 1};
        else if (arr[position] < value) {
            return searchUp(arr, position, 0, value, 0, 1);
        }
        else {
            return searchDown(arr, position, 0, value, 0, 1);
        }
    }

    public static void compareApproaches(int[] arr, int min, int max) {
        long hardNum1, hardNum2, totalCalls1, totalCalls2, maxCalls1, maxCalls2;
        hardNum1 = hardNum2 = maxCalls1 = maxCalls2 = totalCalls1 = totalCalls2 = 0;
        for (int i = min; i <= max; i++) {
            int[] first = probalisticSearch(arr, i);
            totalCalls1 += first[1];
            if (first[1] > maxCalls1) {
                maxCalls1 = first[1];
                hardNum1 = i;
            }
            int[] second = find(arr, i);
            totalCalls2 += second[1];
            if (second[1] > maxCalls2) {
                maxCalls2 = second[1];
                hardNum2 = i;
            }
        }
        write("Binary Search:\nMaximum number of calls:\n" + maxCalls2 + "\nValue at which the maximum number of calls occurs:\n" + hardNum2 + "\nNumber of total calls:\n" + totalCalls2 + "\nProbabilistic Search: \nMaximum number of calls\n" + maxCalls1 + "\nValue at which the maximum number of calls occurs:\n" + hardNum1 + "\nNumber of total calls:\n" + totalCalls1);
    }
    public static int[] find(int[] a, int x) {
        return find0(a, x, 0, a.length - 1, 1);
    }

    public static int[] find0(int[] a, int x, int n1, int n2, int numberOfSteps) {
        int t = (n1 + n2) / 2;
        if (a[t] == x)
            return new int[]{t, numberOfSteps};
        else if (n1 >= n2)
            return new int[]{-1, numberOfSteps};
        else if (x > a[t])
            return find0(a, x, t + 1, n2, numberOfSteps + 1);
        else if (n1 < t)
            return find0(a, x, n1, t - 1, numberOfSteps + 1);
        else return new int[]{-1, numberOfSteps};
    }
}