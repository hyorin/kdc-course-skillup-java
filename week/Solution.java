class Solution {
    public static int solution(int[][] cost, int[][] order) {
        int answer = 0;

        int lastMonth = 0;
        for (int[] o : order) {
            lastMonth = Math.max(o[0], lastMonth);
        }

        int orderAmount = 0;
        int[] montlyOrder = new int[lastMonth];
        for (int[] o : order) {
            int monthIndex = o[0] - 1;
            montlyOrder[monthIndex] += o[1];
            orderAmount += o[1];
        }

        int made = 0;
        for (int i = 0; i < cost.length - 1; i++) {
            if (orderAmount == 0) {
                break;
            }

            int limit = cost[i + 1][0] - cost[i][0];
            int price = cost[i][1];
            int remains = 0;
            for (int j = 0; j < lastMonth; j++) {
                int make = Math.min(limit, orderAmount - made);
                answer += price * make;
                made += make;
                orderAmount -= montlyOrder[j];

                if (montlyOrder[j] == 0) {
                    continue;
                }

                int sentAmonut = Math.min(made, montlyOrder[j]);
                made -= sentAmonut;
                montlyOrder[j] -= sentAmonut;
                remains += montlyOrder[j];
            }
            orderAmount = remains;
            made = 0;
        }

        answer += orderAmount * cost[cost.length - 1][1];
        return answer;
    }

    public static void main(String[] args) {
        int[][] cost = {{0, 10}, {50, 20}, {100, 30}, {200, 40}};
        int[][] order = {{3, 50}, {7, 200}, {8, 200}};
        System.out.println(Solution.solution(cost, order));
    }
}
