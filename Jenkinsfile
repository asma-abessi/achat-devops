pipeline {
    agent any

    stages {
        stage('checkout GIT') {
            steps{
                echo 'Pulling ... ';
            git branch:'asma_abessi' ,
            url : 'https://github.com/asma-abessi/achat-devops.git';
            }
        }
           stage("Mvn clean") {
      
      steps {
        echo 'cleaning the application ...'
        sh "mvn clean"
      }
    }
  
    stage("Mvn compile") {
      
      steps {
        echo 'compiling the application ...'
        sh "mvn compiler:compile"
      }
    }
      stage("MVN SonarQube") {
      
        steps {
        sh "mvn sonar:sonar \
  -Dsonar.projectKey=sonarqube \
  -Dsonar.host.url=http://192.168.1.182:9000 \
  -Dsonar.login=5ad19481d4916f75d2076ccc181afd3adf1b5aa9"
      }
    }
    
    stage('Nexus') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
    stage("Building Docker Image") {
                steps{
                    sh 'docker build -t asmaabbessi1/achat .'
                }
        }
    
   
        stage("Login to DockerHub") {
                steps{
                
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u asmaabbessi1 -p asma07487210'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push asmaabbessi1/achat'
                }
        }
    }
}