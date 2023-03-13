import java.util.Arrays;

public class Solution4 {

    public int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        int index = B.length - 1;
        for (int i = A.length - 1; 0 <= i; i--) {
            if (A[i] < B[index]) {
                answer++;
                index--;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution4 s = new Solution4();
        System.out.println(s.solution(new int[] {0, 0, 1, 1, 0, 0}, 2));
    }

    public enum Side {
        FRONT(1), BACK(0);

        private int value;

        Side(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public int solution(int[] coin, int k) {
        int[] copied = new int[coin.length];
        System.arraycopy(coin, 0, copied, 0, coin.length);

        int back = checkSide(coin, k, Side.BACK);
        int front = checkSide(copied, k, Side.FRONT);
        // 앞면 0
        if (back == -1) {
            return front;
        }
        if (front == -1) {
            return back;
        }
        return Math.min(back, front);
    }

    public void plipCoins(int[] coin, int k, int index) {
        for (int i = index; i < index + k; i++) {
            coin[i] = Math.abs(1 - coin[i]);
        }
    }

    public int checkSide(int[] coin, int k, Side side) {
        int returnVal = 0;
        // 뒷면 0
        for (int i = 0; i <= coin.length - k; i++) {
            if (coin[i] == side.getValue()) {
                continue;
            }
            plipCoins(coin, k, i);
            returnVal++;
        }

        for (int i = coin.length - k + 1; i < coin.length; i++) {
            if (coin[i] == side.getValue()) {
                continue;
            }
            return -1;
        }
        return returnVal;
    }
}
