pipeline {
    agent any

    options {
        timestamps()
        skipDefaultCheckout()
    }

    parameters {
        choice(name: 'ENVNAME', choices: ['bld', 'int', 'pre', 'prod'], description: 'Select the test environment')
        string(name: 'TAG', defaultValue: '@Bookingroom', description: 'Select the BDD test scenarios tag name')
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                echo "*********************** Workspace clean and Declarative checkout ***********************************"
                // Cleanup before starting the stage
                cleanWs()

                // Checkout the repository
                checkout scm

                echo "*********************** Checkout source code from version control ***********************************"
                // git https://github.com/traviprasad/api-automation-framework.git'
                }
            }
        }

        stage('BDD Test Scenarios') {
            steps {
            sh '''
            echo "*********************** Running BDD  Cucumber Test Scenarios ***********************************"
                mvn -o clean verify -Dcucumber.options="--tags '${TAG} and not @ignore and not @bug and not @wip and not @manual and not @local'" -Denv=${ENVNAME} -B
            '''
            }
            post {
                    always {
                     echo "*********************** Publish Cucumber Report ***********************************"
                     cucumber buildStatus: 'UNSTABLE', fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target', reportTitle: 'Integration Test'
                     archive includes: 'target/cucumber.json'
                     archive includes: 'logs/*'
                     archive includes: 'logs/**/*'
                    }
                }
        }
    }

post{
    always{
      archiveArtifacts allowEmptyArchive: true, artifacts: 'logs/*'
      }
    }
}