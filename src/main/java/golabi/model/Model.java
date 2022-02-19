package golabi.model;

import java.time.LocalDateTime;
import java.util.LinkedList;

public abstract class Model {
    protected int ID;
    protected LinkedList<Integer> reportCounter = new LinkedList<>();

    protected LocalDateTime creatAt;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LinkedList<Integer> getReportCounter() {
        return reportCounter;
    }

    public void setReportCounter(LinkedList<Integer> reportCounter) {
        this.reportCounter = reportCounter;
    }
}
