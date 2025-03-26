package site.easy.to.build.crm.enums;

public enum TypeExpense {
    LEAD("lead") ,
    TICKET("ticket");

    private final String value;
    public String getValue() {
        return this.value ;
    }
    TypeExpense(String value) {
        this.value = value;
    }
}
