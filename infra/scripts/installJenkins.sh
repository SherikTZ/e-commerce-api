#!/bin/bash

# Create log directory and file
mkdir -p logs
LOG_FILE="logs/installJenkins.log"
touch $LOG_FILE

echo "Starting Jenkins installation at $(date)" | tee -a $LOG_FILE

# Update the system
echo "Updating system packages..." | tee -a $LOG_FILE
sudo yum update -y >> $LOG_FILE 2>&1

# Install Java (required for Jenkins)
echo "Installing Java..." | tee -a $LOG_FILE
sudo yum install java-21-amazon-corretto-devel.x86_64 -y >> $LOG_FILE 2>&1

# Verify Java installation
echo "Verifying Java installation..." | tee -a $LOG_FILE
java -version >> $LOG_FILE 2>&1

# Add Jenkins repository
echo "Adding Jenkins repository..." | tee -a $LOG_FILE
sudo wget -O /etc/yum.repos.d/jenkins.repo \
    https://pkg.jenkins.io/redhat-stable/jenkins.repo >> $LOG_FILE 2>&1

# Install GPG key for Jenkins
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key >> $LOG_FILE 2>&1
# Install Jenkins
echo "Installing Jenkins..." | tee -a $LOG_FILE
sudo yum install jenkins -y >> $LOG_FILE 2>&1

#Reload systemd to recognize the new Jenkins service
echo "Reloading systemd..." | tee -a $LOG_FILE
sudo systemctl daemon-reload >> $LOG_FILE 2>&1

# Start Jenkins service
echo "Starting Jenkins service..." | tee -a $LOG_FILE
sudo systemctl enable jenkins >> $LOG_FILE 2>&1
sudo systemctl start jenkins >> $LOG_FILE 2>&1

# Check if Jenkins is running
echo "Checking Jenkins status..." | tee -a $LOG_FILE
sudo systemctl status jenkins | grep "active (running)" >> $LOG_FILE 2>&1
if [ $? -eq 0 ]; then
    echo "Jenkins installed and running successfully!" | tee -a $LOG_FILE
else
    echo "Jenkins installation completed but service might not be running. Check logs for details." | tee -a $LOG_FILE
fi

# Get the initial admin password
echo "Jenkins initial admin password:" | tee -a $LOG_FILE
sudo cat /var/lib/jenkins/secrets/initialAdminPassword | tee -a $LOG_FILE

# Get public IP and display Jenkins URL
PUBLIC_IP=$(curl -s http://169.254.169.254/latest/meta-data/public-ipv4)
echo "Jenkins is accessible at: http://$PUBLIC_IP:8080" | tee -a $LOG_FILE
echo "Use the above admin password for initial setup" | tee -a $LOG_FILE