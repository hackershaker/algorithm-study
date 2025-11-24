package problems.p7662_이중_우선순위_큐;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, K;
    static Queue<Integer> minHeap = new PriorityQueue<>();
    static Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                if (cmd.equals("I")) {
                    insert(value);
                } else if (cmd.equals("D") && value == 1) {
                    delete(maxHeap);
                } else {
                    delete(minHeap);
                }
            }

            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                while (!map.containsKey(maxHeap.peek())) {
                    maxHeap.poll();
                }
                while (!map.containsKey(minHeap.peek())) {
                    minHeap.poll();
                }
                System.out.println(maxHeap.peek() + " " + minHeap.peek());
            }
        }
    }

    public static void insert(int x) {
        minHeap.add(x);
        maxHeap.add(x);
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    public static void delete(Queue<Integer> que) {
        if (map.isEmpty()) return;
        while (true) {
            int key = que.peek();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) - 1);
                if (map.get(key) == 0) {
                    map.remove(key);
                }
                que.poll();
                return;
            }
            que.poll();
        }
    }
}
