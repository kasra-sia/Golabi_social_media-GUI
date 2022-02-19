package golabi.model;

public class Report extends Model{
    private int reporterID;
    private Model reportedModel;
    public Report(int reporterID,Model model) {
        this.reporterID = reporterID;
        this.reportedModel = model;
    }

    public int getReporterID() {
        return reporterID;
    }

    public void setReporterID(int reporterID) {
        this.reporterID = reporterID;
    }

    public Model getReportedModel() {
        return reportedModel;
    }

    public void setReportedModel(Model reportedModel) {
        this.reportedModel = reportedModel;
    }
}
