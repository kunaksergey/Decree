package ua.shield.entity;

/**
 * Created by sa on 14.07.17.
 */
public class Order {
    /**
     * Статусы документа:
     * 0-подготовленный
     * 1-в работе
     * 2-завершенный
     */
    public static final int STATUS_PREPARED=0;
    public static final int STATUS_IN_WORK=1;
    public static final int STATUS_COMPLETED=2;

    private Integer id;
    private int status;
    private String caption;
    private String description;
    private byte[] file;

    public Order() {
    }

    public Order(Integer id, int status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public Order(int status, String describe) {
        this.status = status;
        this.description = describe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescribe(String describe) {
        this.description = description;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
