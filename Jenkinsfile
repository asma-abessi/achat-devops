pipeline {
    agent any
	environment {
		DOCKERHUB_CREDENTIALS=credentials('ae30e884-2de1-4829-80a6-099e6db256a2')
	}

 

    
    stages{
        stage("git pull"){
            steps{
              
                git branch: 'oussemasb', 
                credentialsId: '9acb558c-4d6c-41ed-8b05-0da5b90ab4cc', 
                url: 'https://github.com/asma-abessi/achat-devops.git'
                    
                }
                
            }
            
        stage('MVN COMPILE') {
                steps {
                sh 'mvn clean compile'
                    
                }
                
            }
        stage('clean'){
                steps{
                sh 'mvn clean package'
                    
                }
                
            }  
        stage('MVN TEST') {
                steps {
                sh 'mvn clean test'
                    
                }
                
            }  
        stage('build'){
            steps{
                sh 'mvn install package'
            }
         }
	    stage ('SONAR'){
            steps {
        
                  sh "mvn sonar:sonar \
  -Dsonar.projectKey=devops \
  -Dsonar.host.url=http://192.168.1.16:9000 \
  -Dsonar.login=9856c56b854bf955d05481aa01ef833bdad1906e"

                 }
    
                     }
	       stage('NEXUS') {
     
             steps {
             sh 'mvn deploy -DskipTests'
                    }
                       }
	    stage('Docker Build') {

			steps {
				sh 'docker build -t oussamasb/achat .'
			}
		}
         
   stage('Docker Login') {

			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'
			}
		}
	    stage('Push') {

			steps {
				sh 'docker push oussamasb/achat'
			}
		}
    
	    

	}

}
