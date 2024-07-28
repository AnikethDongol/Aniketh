public class Visitor extends Person {
    private String visitorId;
    private boolean hasTicket;

    public Visitor(String name, int age, String gender, String visitorId, boolean hasTicket) {
        super(name, age, gender);
        this.visitorId = visitorId;
        this.hasTicket = hasTicket;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public boolean isHasTicket() {
        return hasTicket;
    }

    public void setHasTicket(boolean hasTicket) {
        this.hasTicket = hasTicket;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorId='" + visitorId + '\'' +
                ", hasTicket=" + hasTicket +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                '}';
    }

    public String toCsv() {
        return getName() + "," + getAge() + "," + getGender() + "," + visitorId + "," + hasTicket;
    }

    public static Visitor fromCsv(String csv) {
        String[] parts = csv.split(",");
        return new Visitor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3], Boolean.parseBoolean(parts[4]));
    }
}
