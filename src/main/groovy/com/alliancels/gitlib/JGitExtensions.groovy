package com.alliancels.gitlib

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Constants
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.RevWalk

/**
 * Convenience methods for interacting with a Git repo.
 */
class JGitExtensions {

    /**
     * Get the currently checked out version.
     *
     * If the repo is dirty, the version returned will be null.
     * If the current commit has a tag associated with it, the version will be that tag.  Ex: "1.0.0".
     * If the current commit does not have a tag associated with it, the most recently tagged parent commit
     * tagged will be appended with the current commit's ID.  Ex: "1.0.0-3a6d0fb".
     *
     * @param repo The repository.
     * @return A description of the current version.
     */
    static String getVersionTag(Repository repo) {
        Git git = new Git(repo)

        if (git.status().call().isClean()) {
            return git.describe().call()
        } else {
            return null
        }
    }

    /**
     * Get the number of seconds since the Unix epoch that the commit was created.
     *
     * @param repo The repository.
     * @return String with the timestamp of the currently checked out commit, or current timestamp if
     * the repo is in a dirty state.
     */
    static int getHeadCommitTime(Repository repo) {
        def head = repo.resolve(Constants.HEAD)
        def walk = new RevWalk(repo)
        RevCommit commit = walk.parseCommit(head)
        def time = commit.getCommitTime()
        return time
    }

    /**
     * Get date and time.
     *
     * @param repo The repository.
     * @return String with the timestamp of the currently checked out commit, or current timestamp if
     * the repo is in a dirty state.  Format is "YYYY-MM-dd HH:mm:ss".
     */
    static String getDateTime(Repository repo) {
        def dateTimeFormat = "YYYY-MM-dd HH:mm:ss"
        Git git = new Git(repo)
        if (git.status().call().isClean()) {
            def time = getHeadCommitTime(repo)
            return new Date((time as long)*1000).format(dateTimeFormat)
        } else {
            return new Date().format(dateTimeFormat)
        }
    }
}
