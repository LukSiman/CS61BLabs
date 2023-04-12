package gitlet;

// TODO: any imports you need here

import java.util.Date; // TODO: You'll likely use this in this class

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit {
    /**
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /**
     * The message of this Commit.
     */
    private String message;

    //Timestamp of the commit
    private String timestamp;

    //Parent of the commit
    private Commit parent;

    //UID of the commit
    private String UID;

    //Branch name
    private String branchName;

    public Commit(Commit parent, String message) {
        this.parent = parent;
        if(this.parent == null){
            this.timestamp = "00:00:00 UTC, Thursday, 1 January 1970";
//            this.UID = Utils.sha1(this);
        }
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Commit getParent() {
        return parent;
    }

    public String getUID() {
        return UID;
    }

    public String getBranchName() {
        return branchName;
    }
}
