pipeline{

    agent any

    stages{

       stage("Info"){
        steps{
            sh "echo 'This is general information'"
        }
       }

       stage("SCM config"){
        steps{
            git branch: 'develop', changelog: false, credentialsId: 'a4d9a3ae-a84d-48ee-b989-ad637328423f', poll: false, url: 'https://github.com/naren970/semi-micro-services.git'
        }
       } 
    }

}