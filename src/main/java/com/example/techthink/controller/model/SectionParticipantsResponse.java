package com.example.techthink.controller.model;

import java.util.List;

public class SectionParticipantsResponse {
    private CourseSectionResponse sectionResponse;
    private List<UserResponse> participants;

    public SectionParticipantsResponse() {
    }

    public SectionParticipantsResponse(CourseSectionResponse sectionResponse, List<UserResponse> participants) {
        this.sectionResponse = sectionResponse;
        this.participants = participants;
    }

    public CourseSectionResponse getSectionResponse() {
        return sectionResponse;
    }

    public void setSectionResponse(CourseSectionResponse sectionResponse) {
        this.sectionResponse = sectionResponse;
    }

    public List<UserResponse> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserResponse> participants) {
        this.participants = participants;
    }
}
