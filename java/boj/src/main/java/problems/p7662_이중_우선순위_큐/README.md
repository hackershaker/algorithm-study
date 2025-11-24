## 문제 요약
- 이중 우선순위 큐를 구현한다.
- 숫자 삽입, 최댓값 삭제, 최솟값 삭제 연산 구현

## 입출력 예시
- 입력
```angular2html
2
7
I 16
I -5643
D -1
D 1
D 1
I 123
D -1
9
I -45
I 653
D 1
I -642
I 45
I 97
D 1
D -1
I 333
```
- 출력
```angular2html
EMPTY
333 -45
```

## 문제 유형
- 자료 구조
- 집합과 맵
- 트리를 사용한 집합과 맵
- 우선순위 큐

## 핵심 아이디어
처음 아이디어는 LinkedList를 정렬된 상태로 유지하고, 양 끝에서 최댓값, 최솟값을 삭제하는 연산을
하면 어떨까 라고 생각했다. 하지만 정렬 삽입 시 시간 복잡도가 O(n)이므로 너무 오래 걸리겠다는 생각을 했다.
따라서 min heap, max heap 2개를 동시에 사용해서 최댓값, 최솟값 연산을 해결하려고 했다.

## 복잡도
O(M*log(N)) (N:우선순위 큐에 들어가 있는 수의 갯수, M: 연산 횟수)

## 배운 점

우선순위 큐를 구현할 때 다음과 같은 코드로 구현했다

```java
static Queue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b); 
static Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```

그런데 이렇게 하면 overflow error 가 발생할 수 있다(예 a: -2147483648, b: 2147483647).
따라서 Java의 Collections.Comparator를 활용하여 구현하는 것이 안전하다.

```java
static Queue<Integer> minHeap = new PriorityQueue<>();
static Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
```
