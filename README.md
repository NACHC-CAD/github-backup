# github-backup
The github-backup project is a tool that can be used to create a local backup of all repositories identified by a github token.  
# Introduction
The github-backup project is a tool that can be used to create a local backup of all repositories identified by a github token.  
To use the tool, download the latest zip file from the releases page of this project, extract the contents, update the configuration file, and add a chron job to call the github-backup.jar file using java.  Detailed instructions are given below.  
The current version of this tool uses a naming convention to create a folder for daily backups using a yyyy-MM-dd format.  
On the 22nd day of each month, a monthly backup will be created in the MONTHLY folder and all backups from previous months will be deleted (leaving 22 daily backups).  
Details on istalation and use are given below.  
# Instalation and Use
## Get a github token
Instructions on getting a github token are at https://nachc-cad.github.io/fhir-to-omop/pages/navbar/how-tos/developer-how-tos/git-token/GitToken.html
## Download and Install


