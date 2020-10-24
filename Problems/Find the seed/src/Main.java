import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        findSeed(a, b, n, k);
    }

    public static void findSeed(int a, int b, int n, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int seed = a;
        while (seed <= b) {
            List<Integer> list = new ArrayList<>();
            Random random = new Random(seed);
            for (int i = 0; i < n; i++) {
                int temp1 = random.nextInt(k);
                list.add(temp1);
                System.out.println(temp1);
            }
            map.put(seed, Collections.max(list));
            seed++;
        }

        Map.Entry<Integer, Integer> min = null;

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (min == null || min.getValue() > e.getValue()) {
                min = e;
            }
        }
        System.out.println("RESULTS");
        System.out.println(min.getKey());
        System.out.println(min.getValue());
    }
}