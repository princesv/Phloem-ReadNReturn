package com.prince.phloem;



public class Order{
    private Long id;
    private String name;
    private Long contact;
    private String physics;
    private String chemistry;
    private  String mathematics;
    private String economics;
    private String accounts;
    private String bst;
    private String fnf;
    private String competitiveBooks;
    private String others;

    public Order(){}

    public Order(Long id,String name, Long contact, String physics, String chemistry, String mathematics, String economics, String accounts, String bst, String fnf,String competitiveBooks,String others) {
        this.id=id;
        this.name =""+ name;
        this.contact = contact;
        this.physics =""+ physics;
        this.chemistry = ""+chemistry;
        this.mathematics =""+ mathematics;
        this.economics =""+ economics;
        this.accounts = ""+accounts;
        this.bst =""+ bst;
        this.fnf = ""+fnf;
        this.competitiveBooks=""+competitiveBooks;
        this.others = ""+others;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getContact() {
        return contact;
    }

    public String getPhysics() {
        return physics;
    }

    public String getChemistry() {
        return chemistry;
    }

    public String getMathematics() {
        return mathematics;
    }

    public String getEconomics() {
        return economics;
    }

    public String getAccounts() {
        return accounts;
    }

    public String getBst() {
        return bst;
    }

    public String getFnf() {
        return fnf;
    }

    public String getOthers() {
        return others;
    }

    public String getCompetitiveBooks() {
        return competitiveBooks;
    }
}
