import java.util.Arrays;

class Solution3 {
    // 지방 예산 배정
    public int solution(int[] budgets, int M) {
        int answer = 0;

        Arrays.sort(budgets);
        int max = budgets[budgets.length - 1];
        int min = 0;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (answer == mid) {
                break;
            }

            int summary = sumBudgetsUnderLimit(budgets, mid);
            if (summary < M) {
                min = mid;
                answer = mid;
            } else if (summary == M) {
                answer = mid;
                break;
            } else if (summary > M) {
                max = mid;
            }

        }

        return answer;
    }

    public int sumBudgetsUnderLimit(int[] budgets, int limit) {
        int returnVal = 0;

        int i = 0;
        for (; i < budgets.length; i++) {
            if (budgets[i] >= limit) {
                break;
            }
            returnVal += budgets[i];
        }
        returnVal += limit * (budgets.length - i);
        return returnVal;
    }

    public static void main(String[] args) {
        Solution3 d = new Solution3();
        // System.out.println(d.solution(new int[] {120, 110, 140, 150}, 485));
        System.out.println(d.solution(43, new int[] {5, 3, 7, 6, 4}));
    }

    // 인형 공장 인센티브
    public long solution(int goal, int[] durations) {
        long answer = 0;

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int duration : durations) {
            max = Math.max(duration, max);
            min = Math.min(duration, min);
        }

        int minTime = goal / durations.length * min;
        int maxTime = (goal / durations.length * max) + 1;
        while (minTime < maxTime) {
            int midTime = (minTime + maxTime) / 2;

            int summary = 0;
            for (int duration : durations) {
                summary += midTime / duration;
            }

            if (summary < goal) {
                minTime = midTime + 1;
            } else {
                maxTime = midTime;
            }
        }

        int remains = goal;
        int lowest = maxTime / max;
        for (int duration : durations) {
            int value = maxTime / duration;
            remains -= value;
            answer += (value - lowest) * 10000;
        }

        // System.out.println(remains);
        if (remains > 0) {
            answer += (remains * 10000);
        }
        return answer;
    }
}
