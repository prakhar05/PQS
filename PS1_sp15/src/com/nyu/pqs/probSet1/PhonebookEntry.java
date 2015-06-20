package com.nyu.pqs.probSet1;

public class PhonebookEntry implements java.io.Serializable{
  //serialVersioinUID for the serialization of objects (writing to file and reading form file)
  private static final long serialVersionUID = 1L;
  //No limit imposed on length of fields
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private String note;
  
  
  PhonebookEntry() {
    name = "";
    address = "";
    phoneNumber = "";
    email = "";
    note = "";
  }

  public void setEntry(String name, String address, String phoneNumber,
      String email, String note) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.note = note;
  }
  
  public PhonebookEntry getEntry(){
    return this;
  }

  //provide the value to be searched and the field to be looked up, returns boolean value
  public boolean isEqualField(String field, String value) {
    if (field.equalsIgnoreCase("name")) {
      return (this.name.equalsIgnoreCase(value));
    } else if (field.equalsIgnoreCase("address")) {
      return (this.address.equalsIgnoreCase(value));
    } else if (field.equalsIgnoreCase("phoneNumber")) {
      return (this.phoneNumber.equalsIgnoreCase(value));
    } else if (field.equalsIgnoreCase("email")) {
      return (this.email.equalsIgnoreCase(value));
    } else if (field.equalsIgnoreCase("note")) {
      return (this.note.equalsIgnoreCase(value));
    } else {
      return false;
    }
  }

}
