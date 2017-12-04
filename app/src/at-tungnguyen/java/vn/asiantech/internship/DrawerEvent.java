package vn.asiantech.internship;

/**
 * Created by tungnguyen on 04/12/2017.
 */

public class DrawerEvent {
    public static final int EVENT_HEADER = 0;
    public static final int EVENT_CONTENT = 1;
    private String mHeader;
    private String mContent;
    private int mType;

    public DrawerEvent(String mHeader, String mContent, int mType) {
        this.mHeader = mHeader;
        this.mContent = mContent;
        this.mType = mType;
    }

    public static int getEventHeader() {
        return EVENT_HEADER;
    }

    public static int getEventContent() {
        return EVENT_CONTENT;
    }

    public String getmHeader() {
        return mHeader;
    }

    public void setmHeader(String mHeader) {
        this.mHeader = mHeader;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}