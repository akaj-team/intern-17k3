package vn.asiantech.internship.models;

/**
 * Created by hoangnhat on 14/12/2017.
 * This class is display screen
 */
public class Screen {
    private String content;
    private int color;

    public Screen(String content, int color) {
        this.content = content;
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
