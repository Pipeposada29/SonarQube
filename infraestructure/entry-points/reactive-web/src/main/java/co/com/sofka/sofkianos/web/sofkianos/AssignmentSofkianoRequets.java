package co.com.sofka.sofkianos.web.sofkianos;


import lombok.Data;

import java.util.Date;

@Data
public class AssignmentSofkianoRequets {
     private  String projectName;
     private  String idCompany;
     private  String service;
     private long startDate;
     private  long releaseDate;
     private  Double assigmentPercent;
     private  Double rate;
     private  String rateType;
     private  Boolean isBillable;
}

