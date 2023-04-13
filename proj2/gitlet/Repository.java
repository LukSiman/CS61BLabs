package gitlet;

//import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.IOException;

import static gitlet.Utils.*;

// TODO: any imports you need here

/**
 * Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 * @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /**
     * The current working directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /**
     * The .gitlet directory.
     */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    //initialize repository
    public static void init() {

        //check if already initialized
        if (GITLET_DIR.exists()) {
            Utils.message("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        //initialize
        Commit commit = new Commit(null, "initial commit");
        GITLET_DIR.mkdir();

        //create commit file
        File initialCommitFile = Utils.join(GITLET_DIR, "initialCommit");

        //write the object to file
        Utils.writeObject(initialCommitFile, commit);

        //get UID by reading the content
        String uid = Utils.sha1((Object) Utils.readContents(initialCommitFile));
        commit.setUID(uid);

        //write the object again to the file
        Utils.writeObject(initialCommitFile, commit);
    }
}
