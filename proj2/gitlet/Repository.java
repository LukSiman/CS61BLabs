package gitlet;

//import org.checkerframework.checker.units.qual.C;

import jdk.jshell.execution.Util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import static gitlet.Utils.*;

/**
 * Represents a gitlet repository.
 * does at a high level.
 */
public class Repository {
    /**
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */


    //Treemap for branches
    private static TreeMap<String, String> branches = new TreeMap<>();

    //name of the current branch
    private static String currentBranch = "";

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
        Commit initialCommit = new Commit(null, "initial commit", new Date(0));

        //create initialCommit file
        File initialCommitFile = Utils.join(GITLET_DIR, "commits", initialCommit.getSha());

        //write the object to file
        Utils.writeObject(initialCommitFile, initialCommit);

        //create and set initial branch
        String defaultBranchName = "master";
        createBranch(defaultBranchName, initialCommit.getSha());
        setBranch(defaultBranchName);
    }

    private static void createBranch(String name, String sha) {
        branches.put(name, sha);
        saveBranches();
    }

    private static void saveBranches() {
        // Serialize the branches object
        byte[] branchesBytes = Utils.serialize(branches);

        // Save the serialized branches object to a file
        File branchesFile = Utils.join(GITLET_DIR, "branches");
        Utils.writeObject(branchesFile, branchesBytes);
    }

    private static void setBranch(String name) {
        currentBranch = name;
        saveCurrentBranch();
    }

    private static void saveCurrentBranch() {
        // Serialize the current branch object
        byte[] currentBranchBytes = Utils.serialize(currentBranch);

        // Save the serialized branches object to a file
        File currentBranchFile = Utils.join(GITLET_DIR, "currentBranch");
        Utils.writeObject(currentBranchFile, currentBranchBytes);
    }

    //adds the file to the staging area
    public static void add(String fileToAdd) {
        //check if gitlet is initialized
        checkIfInitialized();

        File file = new File(fileToAdd);

        //check if file exists
        if (!file.exists()) {
            Utils.message("File does not exist.");
            System.exit(0);
        }

        //check if staging directory has been created
        File stagingDirectory = Utils.join(GITLET_DIR, "staging");
        if (!stagingDirectory.exists()) {
            stagingDirectory.mkdir();
        }

        //calculate the hash of the file
        String fileHash = calculateHash(file);

        //check if file is different
        String lastCommitFileHash = getLastCommitFileHash(fileHash);
    }

    //gets the last commit hash of the file
    private static String getLastCommitFileHash(String fileHash) {
        //TODO: FINISH
        return "";
    }


    //calculates the hash of the file
    private static String calculateHash(File file) {
        byte[] fileBytes = Utils.serialize(file);
        return Utils.sha1((Object) fileBytes);
    }

    //displays a log of metadata
    public static void log() {
        //check if gitlet is initialized
        checkIfInitialized();

        String fullPath = GITLET_DIR + "/commits";
        List<String> list = Utils.plainFilenamesIn(fullPath);
        assert list != null;
        for (String file : list) {
            File commitFile = Utils.join(fullPath, file);
            Commit commit = Utils.readObject(commitFile, Commit.class);

            System.out.println("===");
            System.out.println("commit " + commit.getSha());
            System.out.println("Date: " + commit.getTimestamp());
            System.out.println(commit.getMessage());
        }
    }

    private static void checkIfInitialized() {
        if (!GITLET_DIR.exists()) {
            Utils.message("Not in an initialized Gitlet directory.");
            System.exit(0);
        }
    }
}
