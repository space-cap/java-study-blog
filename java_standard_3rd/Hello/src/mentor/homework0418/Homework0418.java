package mentor.homework0418;

import java.util.*;

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

        int count = price / 1000;

        if(count == 0) {
            System.out.println("돈 부족. 어디에서 돈을 구해라.");
            return;
        }

        if(count > 100) {
            System.out.println("로또는 100장 이상 구매할 수 없습니다.");
            return;
        }

        System.out.println(count + "개를 구매했습니다.");

        List<Integer> lottoNumbers = new ArrayList<>();
        for(int c = 0; c < count; c++) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 45; i++) {
                numbers.add(i);
            }

            Collections.shuffle(numbers);
            lottoNumbers = numbers.subList(0, 6);
            Collections.sort(lottoNumbers);

            System.out.println(lottoNumbers);
        }

        //지난 주 당첨 번호를 입력해 주세요.
        System.out.println("지난 주 당첨 번호를 입력해 주세요.(1 ~ 45)");
        String winNum = scanner.nextLine();
        String[] winNums = winNum.split(",");
        List<Integer> winNumList = new ArrayList<>();
        for (String number : winNums) {
            int num = Integer.parseInt(number.trim());
            winNumList.add(num);
        }

        System.out.println("당첨 통계");
        System.out.println("---------");

        Map<Integer, Integer> winNumMap = new TreeMap<>();

        int matchCount = 0;
        for(var num : lottoNumbers) {
            if (winNumList.contains(num)) {
                matchCount++;
            }
        }
        System.out.println("일치하는 번호는 " + matchCount + "개 입니다.");

        int value = winNumMap.getOrDefault(matchCount, 0) + 1;
        winNumMap.put(matchCount, value);

        for (var entry : winNumMap.entrySet()) {
            System.out.println(entry.getKey() + "개 일치 - " + entry.getValue() + "개");
        }


        double profit = 0.0;
        System.out.println("총 수익률은 " + profit + "입니다.");
        System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");

    }
}
