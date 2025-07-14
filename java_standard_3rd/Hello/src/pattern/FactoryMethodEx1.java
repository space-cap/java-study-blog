package pattern;

abstract class Product0606 {
    public abstract void use();
}

abstract class Factory0606 {
    public final Product0606 create(String owner) {
        Product0606 product = createProduct(owner);
        registerProduct(product);
        return product;
    }

    protected abstract Product0606 createProduct(String owner);
    protected abstract void registerProduct(Product0606 product);
}

class IdCard extends Product0606 {
    private String owner;

    IdCard(String owner) {
        System.out.println("IdCard created: " + owner);
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("Use IdCard!");
    }

    @Override
    public String toString() {
        return "IdCard: " + owner;
    }

    public String getOwner() {
        return owner;
    }
}

class IdCardFactory extends Factory0606 {
    @Override
    protected Product0606 createProduct(String owner) {
        return new IdCard(owner);
    }

    @Override
    protected void registerProduct(Product0606 product) {
        System.out.println("Register IdCard: " + product);
    }
}


public class FactoryMethodEx1 {
    public static void main(String[] args) {
        System.out.println("Hello, Factory Method!");

        Factory0606 factory = new IdCardFactory();
        Product0606 card1 = factory.create("kim");
        Product0606 card2 = factory.create("lee");
        Product0606 card3 = factory.create("park");
        card1.use();
        card2.use();
        card3.use();
    }
}
