__Description du projet__

SGA (Système de Gestion des Absences) est une application développée au sein de l'Agence Nationale de la
Couverture Maladie Univerelle et qui permet de faire la gestion des demandes d'absence et de congés.

__Pré-requis avant execution__	



*Installer un jdk ou au moins un jre sur son ordinateur.* 


Pour l'installation merci de suivre les étapes suivantes:
 
* Rendez-vous sur la partie Java du site d’Oracle : http://www.oracle.com/technetwork/java
 
* Dans la zone de droite ’software downloads’, cliquez sur le lien ’Java SE’ (pour Java Standard Edition)
  
* Cliquez sur l’icône ’Java’ pour télécharger le JDK
  
* Accepter la licence
  
* De préférence choisir la version la plus récente (dernière sur la liste)
	
  * Si votre machine est une 64 bit, choisir le jdk dont le nom a 'windows-X64' avant le '.exe'
	
  * Si votre machine est une 32 bit, choisir le jdk dont le nom a 'windows-i586' avant le '.exe' la version 32 bit fonctionne sur un Système Windows 32 bit ou 64 bit.
  
* Cliquez sur le bouton ’Enregistrer le fichier’. Le téléchargement commence	 
  
* Double cliquez sur le fichier téléchargé. L’assistant d’installation du JDK se lance
  
* Cliquez sur le bouton ’Next’ des fenêtre qui vont s'afficher
  
* Au moment d’installer un environnement d’exécution indépendant du JDK, l’assistant vous consulte. Acceptez les options par défaut et cliquez sur Next
  
* Ainsi même si vous supprimez par la suite le répertoire d’installation du JDK (qui contient aussi une JRE), vous pourrez tout de même exécuter des programmes Java sur votre ordinateur
  
* Vous arrivez à l’écran de fin d’installation, qui vous précise que l’installation s’est bien déroulée 
  
* Cliquez sur Close: L’installation du JDK est terminée. 

*Installer nodejs*
Visiter le site nodejs.org 




__Installer un IDE sur son ordinateur__ 


* Dans notre cas nous allons installer Eclipse.
*

 Pour l'installation merci de suivre les étapes suivantes:
  
* Rendez-vous à l'adresse https://www.eclipse.org/downloads/
  
* Cliquez sur le bouton Télécharger 64 bits
  
* Cliquez ensuite sur Télécharger
  
* Démarrez l'exécutable du programme d'installation
  
* Sélectionnez le package à installer
  
* Sélectionnez votre dossier d'installation
  
* Lancez Eclipse 



__Installer le plugin spring boot et angular sur eclipse__
* Cliquez sur Help
* Cliquez sur EclipseMarketplace...
* Dans la barre de recherche tapez sts, suivez l'assistant pour terminer l'installation.

Ouvrir votre invite de commande (cmd.exe) et tapez les commandes suivantes:
* npm install -g angular -ide
* ngide install ~/Development/angular-ide
* ngide open Foo.ts

__Version du jdk__


java version "1.8.0_73"  

Java(TM) SE Runtime Environment (build 1.8.0_73-b02)  

Java HotSpot(TM) 64-Bit Server VM (build 25.73-b02, mixed mode)



__Execution de l'application__ 


*Veuillez suivre les indications suivantes:*

1. Téléchargez le fichier compressé SGA.zip

2. Décompressez le dans votre workspace C:\Users\nom_utilisateur\eclipse-workspace	

3. Dans Eclipse cliquez sur file > Open Projects from File System...	

4. Sélectionnez le projet SGA	

5. Cliquez sur finish
. 
6. Déplasser le dossier cloud-conf dans home://utilisateurs/nom_utilisateur
7. Clic droit sur le dossier MicroServiceRegister > Run As > Spring Boot Application 
8. Clic droit sur le dossier MicroServiceProxy > Run As > Spring Boot Application 
9. Clic droit sur le dossier MicroServiceConfiguration > Run As > Spring Boot Application 
10. Clic droit sur le dossier MicroServiceParametrage > Run As > Spring Boot Application 
11. Clic droit sur le dossier MicroServiceDemandeConge > Run As > Spring Boot Application
12. Clic droit sur le dossier MicroServiceAdministration > Run As > Spring Boot Application
13. Avec votre invite de commande accéder au dossier SGA puis tapez la commande npm install et ensuite
la commande ng serve 