package Beginner;

public class PersonDemo {
    public static void main(String[] args) {
        System.out.println("PersonDemo");

        // new Person(); // Person 클래스로 실체화 = 객체 생성
        // new의 역할 : 자바가 알아볼 수 있도록, 일을 할 수 있도록
        //            객체를 생성해서 메모리에 올려놓는 기능

        Person kim씨 = new Person();
        kim씨.walk();
        kim씨.eat("돈까스");

    }
}
