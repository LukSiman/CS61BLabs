package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class

import static gitlet.Utils.serialize;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Commit implements Serializable {
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
    private Date timestamp;

    //Parent of the commit
    private Commit parent;

    public Commit(Commit parent, String message, Date date) {
        this.parent = parent;
        this.timestamp = date;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Commit getParent() {
        return parent;
    }

    public String getSha(){
        String serialized = serializeCommit();
        return String UID = Utils.sha1(serialized);
    }

    private String serializeCommit(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.message);
        stringBuilder.append(this.timestamp);
        if (this.parent == null){
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.parent.getSha());
        }

        return stringBuilder.toString();
    }
}
