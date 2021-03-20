package eu.victor.ro.ManageITTeam.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String message){
        super(message);
    }
}
