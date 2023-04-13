package gitlet;

//import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
        } else {
            GITLET_DIR.mkdir();
            File commitDirectory = Utils.join(GITLET_DIR, "commits");
            commitDirectory.mkdir();
        }

        //initialize
        Commit initialCommit = new Commit(null, "initial initialCommit", new Date(0));

        //create initialCommit file
        File initialCommitFile = Utils.join(GITLET_DIR, "commits", initialCommit.getSha());

        //write the object to file
        Utils.writeObject(initialCommitFile, initialCommit);
    }

    //adds the file to the staging area
    public static void add(String fileToAdd){

    }

    //displays a log of metadata
    public static void log() {
        String fullPath = GITLET_DIR + "/commits";
        List<String> list = Utils.plainFilenamesIn(fullPath);
        assert list != null;
        for (String file : list) {
            System.out.println();
            File commitFile = Utils.join(fullPath, file);
//            Commit commit = Utils.readObject(commitFile, Commit.class);
            System.out.println(Utils.readContentsAsString(commitFile));
        }
    }
}
