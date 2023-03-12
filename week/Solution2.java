import java.util.Arrays;
import java.util.stream.IntStream;

class Solution2 {
    // 가장 큰 수 문제
    // 문자 -> 정렬 -> 리턴 순서로
    public String solution(int[] numbers) {
        String answer = "";

        Object[] arr = IntStream.of(numbers).mapToObj(n -> String.valueOf(n)).toArray();
        Arrays.sort(arr, (str1, str2) -> {
            return ((String) str2 + str1).compareTo((String) str1 + str2);
        });

        for (Object obj : arr) {
            answer += obj;
        }

        if (answer.startsWith("0"))
            return "0";
        return answer;
    }

    // 마법의 엘리베이터
    // 재귀호출하는데, 값을 비교할 때 Math.min 하는 게 좋다. 남은 값이 이상일 때 올림하는게 아니고, 둘 중 하나를 선택해야함. elevate +1 + (10 -
    // rest) 보다 elevate + rest 가 더 적을 수 있음
    public int solution(int storey) {
        return elevate(storey);
    }

    public int elevate(int storey) {
        if (storey <= 1) {
            return storey;
        }

        int rest = storey % 10;
        int root = storey / 10;

        return Math.min(elevate(root) + rest, elevate(root + 1) + (10 - rest));
    }
}
