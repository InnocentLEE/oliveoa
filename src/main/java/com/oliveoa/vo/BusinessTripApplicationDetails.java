package com.oliveoa.vo;

import com.oliveoa.pojo.BusinessTripApplication;
import com.oliveoa.pojo.BusinessTripApplicationApprovedOpinion;

import java.util.List;

/**
 * Created by Lee on 2018/6/10.
 */
public class BusinessTripApplicationDetails {
    private BusinessTripApplication businessTripApplication;
    private List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList;

    public BusinessTripApplicationDetails() {
    }

    public BusinessTripApplicationDetails(BusinessTripApplication businessTripApplication, List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList) {
        this.businessTripApplication = businessTripApplication;
        this.businessTripApplicationApprovedOpinionList = businessTripApplicationApprovedOpinionList;
    }

    public BusinessTripApplication getBusinessTripApplication() {
        return businessTripApplication;
    }

    public void setBusinessTripApplication(BusinessTripApplication businessTripApplication) {
        this.businessTripApplication = businessTripApplication;
    }

    public List<BusinessTripApplicationApprovedOpinion> getBusinessTripApplicationApprovedOpinionList() {
        return businessTripApplicationApprovedOpinionList;
    }

    public void setBusinessTripApplicationApprovedOpinionList(List<BusinessTripApplicationApprovedOpinion> businessTripApplicationApprovedOpinionList) {
        this.businessTripApplicationApprovedOpinionList = businessTripApplicationApprovedOpinionList;
    }

    @Override
    public String toString() {
        return "BusinessTripApplicationDetails{" +
                "businessTripApplication=" + businessTripApplication +
                ", businessTripApplicationApprovedOpinionList=" + businessTripApplicationApprovedOpinionList +
                '}';
    }
}
