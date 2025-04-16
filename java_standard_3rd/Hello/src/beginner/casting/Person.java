package beginner.casting;

public class Person {
    Casper casper;
    Ferrari ferrari;

    public void buyCar(Casper casper) {
        this.casper = casper;
    }

    public void buyCar(Ferrari ferrari) {
        this.ferrari = ferrari;
    }

    public void pressBbang() {
        casper.bbangbbang();
    }
}
