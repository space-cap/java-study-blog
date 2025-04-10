package quiz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @org.junit.jupiter.api.Test
    void main() {
        System.out.println("CardTest");
        // Add test cases here
        assertTrue(true); // Example assertion, replace with actual test logic
    }

    @Test
    void initCards() {
        CardDemo cardDemo = new CardDemo();
        cardDemo.InitCards();
        // Add assertions to verify the cards are initialized correctly
        assertNotNull(cardDemo.cardArr);
        assertEquals(5, cardDemo.cardArr.length);
    }

    public String rankCheck(Card[] cardArr) {
        CardDemo cardDemo = new CardDemo();
        cardDemo.cardArr = cardArr;
        return cardDemo.rankCheck();
    }

    @Test
    public void FourCardTest() {
        // 조건
        Card[] cardArr = {
                new Card(1,"H"),new Card(1,"H"),new Card(1,"H"),
                new Card(1,"H"),new Card(3,"D")};

        // 테스트
        String result = rankCheck(cardArr);

        // System.out.println(result);

        // 확인
        assertTrue(result.equals("FOUR CARD"));
    }

    @Test
    public void fullHouseTest() {
        // 조건
        Card[] cardArr = {
                new Card(1,"H"),new Card(1,"H"),new Card(1,"H"),
                new Card(3,"H"),new Card(3,"D")};

        // 테스트
        String result = rankCheck(cardArr);

        // 확인
        assertTrue(result.equals("FULL HOUSE"));
    }


    @Test
    public void flushTest() {
        // 조건
        Card[] cardArr = {
                new Card(1,"H"),new Card(8,"H"),new Card(3,"H"),
                new Card(4,"H"),new Card(5,"H")};

        // 테스트
        String result = rankCheck(cardArr);

        // 확인
        assertTrue(result.equals("FLUSH"));
    }


    @Test
    public void straightTest() {
        // 조건
        Card[] cardArr = {
                new Card(1,"H"),new Card(2,"D"),new Card(3,"H"),
                new Card(4,"H"),new Card(5,"H")};

        // 테스트
        String result = rankCheck(cardArr);

        // 확인
        assertTrue(result.equals("STRAIGHT"));
    }

}