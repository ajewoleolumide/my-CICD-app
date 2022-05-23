pipeline{
    agent any
 parameters {
  string defaultValue: 'https://github.com/ajewoleolumide/my-CICD-app.git/', description: 'https URL', name: 'git_repo_url'
  string defaultValue: 'any', description: 'Environment', name: 'Target_Env'
}

    stages {
        stage('job preparation'){
            steps{
                script{
                    cleanWs()
                    println('Preparing job with variables')
                    currentBuild.displayName = "${BUILD_NUMBER} started by ${currentBuild.getBuildcauses()[0].userid}"
                    currentBuild.description = """Environment selected ${TARGET_ENV}, git repo url: ${git_repo_url} """
                }
            }
        }
        stage('git clone'){
            steps{
                git branch: 'main',changelog; 'False', credentialsId: 'my-cba-aws-credentials-id', poll: false, url: git_repo_url, 
            }
        }
        stage('Readfile'){
                steps{
                    script{
                        fileexist = fileExist 'README.md'
                        if fileexist{
                            def file = ReadFile 'README.md'
                            println(file)
                        } else {
                            println('No README.md file')
                        }
                        cleanWs()
                    }
                }
        }
    }
}