
public abstract class Person {
    private String name;
    private boolean[] attributes;

    public Person(String name, int attributeCount) {
        this.name = name;
        this.attributes = new boolean[attributeCount];
    }

    public String getName() {
        return this.name;
    }

    public boolean hasAttribute(int attributeNumber) {
        if (attributeNumber <= 0 || attributeNumber > this.attributes.length) {
            return false;
        }
        return this.attributes[attributeNumber - 1];
    }

    public void setAttribute(int attributeNumber, boolean value) {
        if (attributeNumber > 0 && attributeNumber <= this.attributes.length) {
            this.attributes[attributeNumber - 1] = value;
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

class PersonA extends Person {
    public PersonA(String name, int attributeCount) {
        super(name, attributeCount);
    }
}

