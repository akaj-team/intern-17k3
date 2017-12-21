package vn.asiantech.internship.models;

/**
 * Created by vietphan on 13/12/2017.
 * Class Step
 */
public class Step {
    private String name;
    private int color;

    public Step(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
