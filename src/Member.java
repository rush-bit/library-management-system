public class Member {
    private String memberId;
    private String name;
    private String contact;

    public Member(String Id,String name,String contact){
        this.memberId=Id;
        this.name=name;
        this.contact=contact;
    }

    public String getMemberId(){
        return memberId;
    }
    public String getName(){
        return name;
    }
    public String getContact(){
        return contact;
    }

    @Override
    public String toString(){
        return "Member{"+
                "ID='"+memberId+'\''+
                ", Name='"+name+'\''+
                ", Contact='"+contact+'\''+
                '}';
    }

    public String toDisplayString(){
        return String.format("%-15s %-25s %-20s",memberId,name,contact);
    }
}
