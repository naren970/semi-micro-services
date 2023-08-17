pipeline{

    agent any

    stages{

       stage("Info"){
        steps{
            "This is general information"
        }
       }

       stage("SCM config"){
        steps{
            git branch: 'develop', changelog: false, credentialsId: 'n_github_secret', poll: false, url: 'https://github.com/naren970/semi-micro-services.git'
        }
       } 
    }

}