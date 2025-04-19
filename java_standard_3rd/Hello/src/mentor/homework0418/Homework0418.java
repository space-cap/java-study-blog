package mentor.homework0418;

import java.util.*;

class Lotto {
    private List<Integer> numbers;

    void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    List<Integer> getNumbers() {
        return numbers;
    }
}

class LottoCompany {

    static List<Integer> createNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, 6);
    }

    static List<Integer> drawLottoNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.(1 ~ 45)");
        String winNum = scanner.nextLine();
        String[] winNums = winNum.split(",");
        List<Integer> winNumList = new ArrayList<>();
        for (String number : winNums) {
            int num = Integer.parseInt(number.trim());
            winNumList.add(num);
        }
        return winNumList;
    }

    static int calculatePrizeAmount(int matchCount, int count) {
        switch (matchCount) {
            case 6:
                return 2000000000*count;
            case 5:
                return 1500000*count;
            case 4:
                return 50000*count;
            case 3:
                return 5000*count;
            default:
                return 0;
        }
    }
}

class LottoStore {
    int getLottoCountByAmount(int price) {
        int count = price / 1000;
        if (count == 0) {
            System.out.println("돈 부족. 어디에서 돈을 구해라.");
            return 0;
        }
        if (count > 100) {
            System.out.println("로또는 100장 이상 구매할 수 없습니다.");
            return 0;
        }
        return count;
    }

    private Lotto buyLotto() {
        Lotto lotto = new Lotto();
        List<Integer> numbers = LottoCompany.createNumbers();
        lotto.setNumbers(numbers);
        return lotto;
    }

    List<Lotto> buyLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lotto lotto = buyLotto();
            lottos.add(lotto);
        }
        return lottos;
    }
}

class LottoAssistant {
    private List<Lotto> lottos;
    private int price;
    private int count;

    boolean buyLottos(int price) {
        LottoStore store = new LottoStore();
        int count = store.getLottoCountByAmount(price);
        if (count > 0) {
            lottos = store.buyLottos(count);
            this.price = price;
            this.count = count;
            return true;
        }
        return false;
    }

    void printLottoNumbers() {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    private int countMatchingNumbers(Lotto lotto, List<Integer> winNums) {
        int matchCount = 0;
        for (var num : lotto.getNumbers()) {
            if (winNums.contains(num)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    void checkLottoResult(List<Integer> winNums) {
        Map<Integer, Integer> resultMap = new TreeMap<>();

        for (Lotto lotto : lottos) {
            int matchCount = countMatchingNumbers(lotto, winNums);
            int value = resultMap.getOrDefault(matchCount, 0) + 1;
            resultMap.put(matchCount, value);
        }

        for (var entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + "개 일치 - " + entry.getValue() + "개");
        }

        double profit = calculateReturnRate(resultMap);
        profit = Math.round(profit * 10) / 10.0;
        System.out.println("총 수익률은 " + profit + "입니다.");
        System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }

    double calculateReturnRate(Map<Integer, Integer> resultMap) {
        int totalSpent = count * 1000;
        int totalWinnings = 0;
        for(var entry : resultMap.entrySet()) {
            totalWinnings +=  LottoCompany.calculatePrizeAmount(entry.getKey(), entry.getValue());
        }
        return (double) totalWinnings / totalSpent;
    }
}

public class Homework0418 {
    public static void main(String[] args) {
        System.out.println("homework0418");

        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.(1000원 ~ 십만원)");
        String scanValue = scanner.nextLine();

        int price = 0;
        try {
            price = Integer.parseInt(scanValue);
        } catch (NumberFormatException e) {
            System.out.println("숫자 입력혀. --;");
            return;
        }

        LottoAssistant luckyAssistant = new LottoAssistant();
        if (luckyAssistant.buyLottos(price) == false) {
            System.out.println("구매 실패");
            return;
        }
        luckyAssistant.printLottoNumbers();

        List<Integer> winNum = LottoCompany.drawLottoNumbers();
        System.out.println("지난 주 당첨 번호는 " + winNum + "입니다.");

        luckyAssistant.checkLottoResult(winNum);

    }
}
