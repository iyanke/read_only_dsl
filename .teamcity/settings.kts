import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.2"

project {

    vcsRoot(ToReproduce_ReadonlyProject_2_HttpsGithubComIyankeBigdataRefsHeadsMaster)

    buildType(ToReproduce_ReadonlyProject_2_Build)
    buildType(ToReproduce12_ReadonlyProject1_Build22)

    template(ToReproduce_ReadonlyProject_2_Temp)
}

object ToReproduce12_ReadonlyProject1_Build22 : BuildType({
    id("Build22")
    name = "Build22"
    paused = true

    params {
        param("build22_param", "2")
    }

    vcs {
        root(ToReproduce_ReadonlyProject_2_HttpsGithubComIyankeBigdataRefsHeadsMaster)
    }

    steps {
        script {
            scriptContent = "echo a"
        }
    }

    triggers {
        vcs {
        }
    }
})

object ToReproduce_ReadonlyProject_2_Build : BuildType({
    id = AbsoluteId("ToReproduce_ReadonlyProject_2_Build")
    name = "Build"
    paused = true

    params {
        param("build", "${ToReproduce12_ReadonlyProject1_Build22.depParamRefs["build22_param"]}")
        param("Build_param", "1")
    }

    vcs {
        root(ToReproduce_ReadonlyProject_2_HttpsGithubComIyankeBigdataRefsHeadsMaster)
    }

    steps {
        script {
            scriptContent = "echo a"
        }
    }

    triggers {
        vcs {
        }
        schedule {
            triggerBuild = onWatchedBuildChange {
                buildType = "${ToReproduce12_ReadonlyProject1_Build22.id}"
            }
        }
    }

    dependencies {
        dependency(ToReproduce12_ReadonlyProject1_Build22) {
            snapshot {
            }

            artifacts {
                artifactRules = "**/*.*"
            }
        }
    }
})

object ToReproduce_ReadonlyProject_2_Temp : Template({
    id = AbsoluteId("ToReproduce_ReadonlyProject_2_Temp")
    name = "temp"

    vcs {
        root(ToReproduce_ReadonlyProject_2_HttpsGithubComIyankeBigdataRefsHeadsMaster)
    }

    steps {
        script {
            id = "RUNNER_110"
            scriptContent = "echo a"
        }
    }

    triggers {
        vcs {
            id = "vcsTrigger"
        }
    }
})

object ToReproduce_ReadonlyProject_2_HttpsGithubComIyankeBigdataRefsHeadsMaster : GitVcsRoot({
    id = AbsoluteId("ToReproduce_ReadonlyProject_2_HttpsGithubComIyankeBigdataRefsHeadsMaster")
    name = "https://github.com/iyanke/bigdata#refs/heads/master"
    url = "https://github.com/iyanke/bigdata"
    branch = "refs/heads/master"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "iyanke"
        password = "credentialsJSON:7c44fbf5-6ed1-4ecb-ac02-060f37b97bf7"
    }
})
