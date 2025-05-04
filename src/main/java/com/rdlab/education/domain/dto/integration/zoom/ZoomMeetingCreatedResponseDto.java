package com.rdlab.education.domain.dto.integration.zoom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class ZoomMeetingCreatedResponseDto {

    private String uuid;
    private long id;

    @JsonProperty("host_id")
    private String hostId;

    @JsonProperty("host_email")
    private String hostEmail;

    private String topic;
    private int type;
    private String status;

    @JsonProperty("start_time")
    private ZonedDateTime startTime;

    private int duration;
    private String timezone;

    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonProperty("start_url")
    private String startUrl;

    @JsonProperty("join_url")
    private String joinUrl;

    private String password;

    @JsonProperty("h323_password")
    private String h323Password;

    @JsonProperty("pstn_password")
    private String pstnPassword;

    @JsonProperty("encrypted_password")
    private String encryptedPassword;

    private Settings settings;

    @JsonProperty("creation_source")
    private String creationSource;

    @JsonProperty("pre_schedule")
    private boolean preSchedule;

    @Data
    public static class Settings {

        @JsonProperty("host_video")
        private boolean hostVideo;

        @JsonProperty("participant_video")
        private boolean participantVideo;

        @JsonProperty("cn_meeting")
        private boolean cnMeeting;

        @JsonProperty("in_meeting")
        private boolean inMeeting;

        @JsonProperty("join_before_host")
        private boolean joinBeforeHost;

        @JsonProperty("jbh_time")
        private int jbhTime;

        @JsonProperty("mute_upon_entry")
        private boolean muteUponEntry;

        private boolean watermark;

        @JsonProperty("use_pmi")
        private boolean usePmi;

        @JsonProperty("approval_type")
        private int approvalType;

        private String audio;

        @JsonProperty("auto_recording")
        private String autoRecording;

        @JsonProperty("enforce_login")
        private boolean enforceLogin;

        @JsonProperty("enforce_login_domains")
        private String enforceLoginDomains;

        @JsonProperty("alternative_hosts")
        private String alternativeHosts;

        @JsonProperty("alternative_host_update_polls")
        private boolean alternativeHostUpdatePolls;

        @JsonProperty("close_registration")
        private boolean closeRegistration;

        @JsonProperty("show_share_button")
        private boolean showShareButton;

        @JsonProperty("allow_multiple_devices")
        private boolean allowMultipleDevices;

        @JsonProperty("registrants_confirmation_email")
        private boolean registrantsConfirmationEmail;

        @JsonProperty("waiting_room")
        private boolean waitingRoom;

        @JsonProperty("request_permission_to_unmute_participants")
        private boolean requestPermissionToUnmuteParticipants;

        @JsonProperty("registrants_email_notification")
        private boolean registrantsEmailNotification;

        @JsonProperty("meeting_authentication")
        private boolean meetingAuthentication;

        @JsonProperty("encryption_type")
        private String encryptionType;

        @JsonProperty("approved_or_denied_countries_or_regions")
        private ApprovedOrDeniedCountriesOrRegions approvedOrDeniedCountriesOrRegions;

        @JsonProperty("breakout_room")
        private BreakoutRoom breakoutRoom;

        @JsonProperty("internal_meeting")
        private boolean internalMeeting;

        @JsonProperty("continuous_meeting_chat")
        private ContinuousMeetingChat continuousMeetingChat;

        @JsonProperty("participant_focused_meeting")
        private boolean participantFocusedMeeting;

        @JsonProperty("push_change_to_calendar")
        private boolean pushChangeToCalendar;

        private List<Object> resources;

        @JsonProperty("allow_host_control_participant_mute_state")
        private boolean allowHostControlParticipantMuteState;

        @JsonProperty("alternative_hosts_email_notification")
        private boolean alternativeHostsEmailNotification;

        @JsonProperty("show_join_info")
        private boolean showJoinInfo;

        @JsonProperty("device_testing")
        private boolean deviceTesting;

        @JsonProperty("focus_mode")
        private boolean focusMode;

        @JsonProperty("meeting_invitees")
        private List<Object> meetingInvitees;

        @JsonProperty("private_meeting")
        private boolean privateMeeting;

        @JsonProperty("email_notification")
        private boolean emailNotification;

        @JsonProperty("host_save_video_order")
        private boolean hostSaveVideoOrder;

        @JsonProperty("sign_language_interpretation")
        private SignLanguageInterpretation signLanguageInterpretation;

        @JsonProperty("email_in_attendee_report")
        private boolean emailInAttendeeReport;


        @Data
        public static class ApprovedOrDeniedCountriesOrRegions {
            private boolean enable;

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }
        }

        @Data
        public static class BreakoutRoom {
            private boolean enable;

            public boolean isEnable() {
                return enable;
            }

            public void setEnable(boolean enable) {
                this.enable = enable;
            }
        }

        @Data
        public static class ContinuousMeetingChat {
            private boolean enable;

            @JsonProperty("auto_add_invited_external_users")
            private boolean autoAddInvitedExternalUsers;

            @JsonProperty("auto_add_meeting_participants")
            private boolean autoAddMeetingParticipants;

            @JsonProperty("channel_id")
            private String channelId;

        }

        @Data
        public static class SignLanguageInterpretation {
            private boolean enable;
        }


    }
}
