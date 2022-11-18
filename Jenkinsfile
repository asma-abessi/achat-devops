pipeline {
    agent any

    stages {
        stage('import backend from GIT') {
            steps{
                echo 'Pulling ... ';
            git branch:'aicha_nouisser' ,
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
    stage('JUnit Test') {
            steps{
                sh 'mvn test'
            }
    }
    
      stage("MVN SonarQube") {
      
        steps {
        sh "mvn sonar:sonar \
  -Dsonar.projectKey=sonarqube \
  -Dsonar.host.url=http://192.168.1.19:9000 \
  -Dsonar.login=f15b2c3b18c15ad584f4a5b51518cd9d3ba50566"
      }
    }
    
    stage('Nexus') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
    
    
     stage("docker_composer"){
            steps{
                sh'docker-compose up -d'
            }
        }
        
        
    stage("Building Docker Image") {
                steps{
                    sh 'docker build -t nouisseraicha/achat .'
                }
        }
    
   
        stage("Login to DockerHub") {
                steps{
                
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u nouisseraicha -p "aicha123456789"'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push nouisseraicha/achat'
                }
        }
        
    }
}