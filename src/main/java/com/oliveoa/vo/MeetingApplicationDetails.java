package com.oliveoa.vo;

import com.oliveoa.pojo.MeetingApplication;
import com.oliveoa.pojo.MeetingMember;

import java.util.List;

/**
 * Created by Lee on 2018/9/4.
 */
public class MeetingApplicationDetails {
    private MeetingApplication meetingApplication;
    private List<MeetingMember> meetingMembers;

    public MeetingApplicationDetails() {
    }

    public MeetingApplicationDetails(MeetingApplication meetingApplication, List<MeetingMember> meetingMembers) {
        this.meetingApplication = meetingApplication;
        this.meetingMembers = meetingMembers;
    }

    public List<MeetingMember> getMeetingMembers() {
        return meetingMembers;
    }

    public void setMeetingMembers(List<MeetingMember> meetingMembers) {
        this.meetingMembers = meetingMembers;
    }

    public MeetingApplication getMeetingApplication() {
        return meetingApplication;
    }

    public void setMeetingApplication(MeetingApplication meetingApplication) {
        this.meetingApplication = meetingApplication;
    }

    @Override
    public String toString() {
        return "MeetingApplicationDetails{" +
                "meetingApplication=" + meetingApplication +
                ", meetingMembers=" + meetingMembers +
                '}';
    }
}
