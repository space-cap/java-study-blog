package beginner.phone;

public class PhoneDemo { // 객체 지향 세상
    public static void main(String[] args) {
       /* Person songa = new Person("손가");
        ApplePhone iPhone12mini = new ApplePhone("iPhone12mini");
        songa.buy(iPhone12mini);
        songa.sayJarang();*/

        //System.out.println(iPhone12mini);
        //System.out.println(iPhone12mini.myString());


        Person seonghoon = new Person("성훈");
        ApplePhone iPhone15ProMax = new ApplePhone("iPhone15ProMax");
        seonghoon.buy(iPhone15ProMax);
        seonghoon.turnOnPhone();
        seonghoon.sayJarang();
        seonghoon.turnOffPhone();

        Person youngmun = new Person("영문");
        SamsungPhone galaxyS24 = new SamsungPhone("갤럭시24");
        youngmun.buy(galaxyS24);
        youngmun.sayGalaxyJarang();

    }
}
