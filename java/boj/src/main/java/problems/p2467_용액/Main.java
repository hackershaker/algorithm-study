package problems.p2467_용액;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 용액값 x를 가지는 용액으로 최대한 0에 가깝게 만들려면 -x에 최대한 가까운 용액을 선택해야 한다.
// 그럼 이분탐색으로 -x와 가장 가까운 용액을 찾으면 된다. 입력이 정렬되어 들어오기 때문이다.
// 그런데 이분탐색을 진행하면 내가 처음에 선택한 용액을 다시 선택할 위험이 있다.
// 용액은 서로 다른 두 용액을 섞어야 하므로 내가 선택한 용액 왼쪽과 오른쪽 베열로 나눠서
// 거기서 -x에 가까운 값을 2개 뽑는다. 이후 x와 섞었을 때 0에 가장 가까운 용액들을 기록하여 출력한다.

public class Main {
    static int N, ans = Integer.MAX_VALUE, solution1, solution2;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < N; i++) {
            int leftClosestIdx = findClosestValue(0, i - 1, arr[i]);
            int rightClosestIdx = findClosestValue(i + 1, N - 1, arr[i]);
//            System.out.println(i + " -> 인덱스 후보들 : " + leftClosestIdx + " " + rightClosestIdx);

            int leftMixValue = (leftClosestIdx == -1) ? Integer.MAX_VALUE : Math.abs(arr[leftClosestIdx] + arr[i]);
            int rightMixValue = (rightClosestIdx == -1) ? Integer.MAX_VALUE : Math.abs(arr[rightClosestIdx] + arr[i]);
//            System.out.println("특성값 후보들 : " + leftMixValue + " " + rightMixValue);

            int mixedValue = Math.min(leftMixValue, rightMixValue);
            int index = (leftMixValue < rightMixValue) ? leftClosestIdx : rightClosestIdx;

            if (ans > mixedValue) {
                ans = mixedValue;
                solution1 = arr[i];
                solution2 = arr[index];
            }
        }

        if (solution1 > solution2) {
            int temp = solution1;
            solution1 = solution2;
            solution2 = solution1;
        }
        System.out.println(solution1 + " " + solution2);
    }

    public static int findClosestValue(int lidx, int ridx, int value) {
        if (lidx > ridx) {
            return -1;
        }
        if (lidx == ridx) {
            return lidx;
        }
        int left = lidx, right = ridx, middle, targetValue = value * -1;
        if (targetValue <= arr[lidx]) {
            return lidx;
        }
        if (arr[ridx] <= targetValue) {
            return ridx;
        }

        while (right - left > 1) { // 후보 2개 남을 때까지 이진탐색
            middle = (left + right) / 2;
//            System.out.println("이진탐색 : " + left + " " + middle + " " + right);

            if (arr[middle] == targetValue) {
                return middle;
            } else if (arr[middle] < targetValue) {
                left = middle;
            } else {
                right = middle;
            }
        }
//        System.out.println("이진탐색 결과 : " + left + " " + right);

        return (Math.abs(arr[left] - targetValue) < Math.abs(arr[right] - targetValue)) ? left : right;
    }
}
