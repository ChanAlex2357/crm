package site.easy.to.build.crm.enums;

public enum LeadStatus {
    MEETING_TO_SCHEDULE("meeting-to-schedule"),
    ASSIGN_TO_SALES("assign-to-sales"),
    ARCHIVED("archived"),
    SUCCESS("success");

    private String status;

    LeadStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

}
