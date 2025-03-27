package ch02;
// 변수(variable) 하나의 값을 저장하기 위한 공간
// 상수(constant) 값을 한번만 저장할 수 있는 공간
// 리터럴(literal) 그 자체로 값을 의히하는 것

// 대소문자 단축키 Command + shift + U

public class ConstantEx1 {
    public static void main(String[] args) {
        System.out.println("constant & literal");

        final int MAX_VALUE; // error, 상수는 선언과 동시에 초기화해야 한다.
        // 1.6 이후부터 선언과 동시에 초기화 하지 않아도 된다.
        // 사용하기 전에만 초기화 하면 된다.


        final int MAX_SPEED = 10; // 상수를 설정을 하기 위해서는 final 키워드를 해 준다.

        MAX_VALUE = 100;
        System.out.println(MAX_VALUE);

        // variable MAX_VALUE might already have been assigned
        // 상수인데 이미 초기화를 해서 에러 발생.
        // MAX_VALUE = 101; // error.

    }
}
