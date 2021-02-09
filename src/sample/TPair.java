package sample;

public class TPair<FIRST,SECOND,THIRD> {
    private FIRST first;
    private SECOND second;
    private THIRD third;
    public TPair(FIRST first, SECOND second, THIRD third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public THIRD getThird() {
        return third;
    }

    public void setThird(THIRD third) {
        this.third = third;
    }

    public FIRST getFirst() {
        return first;
    }
    public SECOND getSecond() {
        return second;
    }

    public void setFirst(FIRST e){ first = e;}
    public void setSecond(SECOND e){ second = e;}

    @Override
    public String toString() {
        return first + " " + second;
    }
}
