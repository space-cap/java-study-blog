package mentor.homework0501;


// `Join`는 오직 `EquiJoin`, `NonEquiJoin`, `OuterJoin`만 상속할 수 있습니다.
sealed class Join permits EquiJoin, NonEquiJoin, OuterJoin { }

// `EquiJoin`은 더 이상 상속될 수 없다.
final class EquiJoin extends Join { }

// `NonEquiJoin`는 자유롭게 상속될 수 있다.
non-sealed class NonEquiJoin extends Join { }

// `OuterJoin`은 다시 한 번 상속 제한이 가능합니다.
sealed class OuterJoin extends Join permits LeftOuterJoin, RightOuterJoin { }

final class LeftOuterJoin extends OuterJoin { }
final class RightOuterJoin extends OuterJoin { }


public class SealedTest {
    public static void main(String[] args) {
        System.out.println("Hello, Sealed");
    }
}
