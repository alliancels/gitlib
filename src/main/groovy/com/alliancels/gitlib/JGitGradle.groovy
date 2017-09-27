package com.alliancels.gitlib

import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.lib.RepositoryBuilder
import org.gradle.api.Project

/**
 * Convenience methods for interacting with a Gradle project's Git repo.
 */
class JGitGradle {

    /**
     * Get the currently checked out version.
     *
     * If the repo is dirty, the version returned will be null.
     * If the current commit has a tag associated with it, the version will be that tag.  Ex: "1.0.0".
     * If the current commit does not have a tag associated with it, the most recently tagged parent commit
     * tagged will be appended with the current commit's ID.  Ex: "1.0.0-3a6d0fb".
     *
     * @param project The project.
     * @return A description of the current version.
     */
    static String getVersionTag(Project project) {
        def repo = getProjectRepo(project)
        return JGitExtensions.getVersionTag(repo)
    }

    /**
     * Get the number of seconds since the Unix epoch that the commit was created.
     *
     * @param project The Gradle project.
     * @return String with the timestamp of the currently checked out commit, or current timestamp if
     * the repo is in a dirty state.
     */
    static int getHeadCommitTime(Project project) {
        def repo = getProjectRepo(project)
        return JGitExtensions.getHeadCommitTime(repo)
    }

    /**
     * Get date and time.
     *
     * @param project The Gradle project.
     * @return String with the timestamp of the currently checked out commit, or current timestamp if
     * the repo is in a dirty state.  Format is "YYYY-MM-dd HH:mm:ss".
     */
    static String getDateTime(Project project) {
        def repo = getProjectRepo(project)
        return JGitExtensions.getDateTime(repo)
    }

    /**
     * Get the repository that the given project belongs to.
     *
     * @param project The project.
     * @return The repository.
     */
    static Repository getProjectRepo(Project project) {
        def repo = new RepositoryBuilder()
                .setGitDir(new File(project.rootProject.rootDir, '/.git'))
                .readEnvironment()
                .build()

        return repo
    }
}
