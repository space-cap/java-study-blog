package ch02;

public class TypeCastingEx1 {
    public static void main(String[] args) {
        // int i = 0x123456789; // error. integer number too large.
        // float f = 3.14; // error. incompatible types: possible lossy conversion from double to float.


        // double → float 변환은 자동 변환이 불가능함.
        // 형변환을 명시적으로 해야 함 → (float) 값
        // f 또는 F를 붙이면 float 리터럴로 인식됨 → 3.14f

        float f = (float)3.14;
        f = 3.14f;

        System.out.println("f:" + f);

        //System.out.println(f.getClass().getSimpleName());
        System.out.println(((Object) f).getClass().getSimpleName());
        System.out.println(((Object) f).getClass().getName());

        // char c = ''; // empty character literal
    }
}
