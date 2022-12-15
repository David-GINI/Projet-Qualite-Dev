# Projet-Qualite-Dev

Voici notre projet pour la ressource "qualité de développement"


Le .jar se trouve sur le chemin : out\artifacts\Projet_Qualite_Dev_jar\Projet-Qualite-Dev.jar

Nous avons réalisé ce projet sur IntelliJ IDEA






# Etude de conception

Nous avons dédié la première séance à faire un brainstorming : nous avons rassemblé toutes nos idées dans un schéma de conceptualisation et les avons mis en parallèle avec les TD de la matière pour vérifier si elles étaient en accord avec le sujet.  
Au fur et à mesure de notre phase de brainstorming, nous avons rempli notre schéma et avons pensé notre jeu comme ceci :
Le jeu consiste à simuler un restaurant. 
Dans ce restaurant, il y aura des employés, des clients, de l’argent à gagner, qui pourra être dépensé pour payer les employés et acheter des équipements.
Il y aura plusieurs types de clients :
                                - avec un statut (Lambda ou critique culinaire)
                                - avec une préférence alimentaire (casher, halal, poisson)
Les clients pourront passer commande et seront pris en charge par un cuisinier.
Il y aura une file d’attente dans laquelle seront placés les clients, ces derniers ont un temps d’attente, passé celui-ci, ils partent de la file.  
Il y aura un système d’écoulement d’heures de l’ordre de quelques secondes par heure. Après une certaine heure, le restaurant sera fermé et on pourra passer au jour suivant. 
Avant de passer à la programmation de notre application, nous nous sommes réparti le travail en s’attribuant chacun une partie du jeu à réaliser entre la gestion des commandes, des clients, des employés, des journées, du restaurant, et des threads avec les classes associés. 

![jeud](https://user-images.githubusercontent.com/92530859/207983204-f7db10d7-d47f-4941-b99d-f8cd2aae57e1.PNG)

![sc](https://user-images.githubusercontent.com/92530859/207983283-15d9d814-05a0-488e-a351-e95860445529.PNG)

![jour](https://user-images.githubusercontent.com/92530859/207983313-598e0877-28fe-43f2-9009-d7e6e0eec7a5.PNG)

# Simulation d’un restaurant : Manuel d’utilisation

-	Après avoir lancé l’application, elle demande d’entrer deux noms(vos 2 premiers employés), celui du cuisinier et du nettoyeur après les avoir entrés, le joueur doit choisir un nom pour le restaurant. 

-	L’application présente 4 employés avec un niveau d’efficacité allant de 0 à 20, plus un employé est efficace, plus il va réaliser une tâche rapidement. L’utilisateur a le choix entre :   - choisir un employé en indiquant le numéro associé à celui voulu puis                 indiquer quelle poste il/elle occupera
                                   - « R », reroll pour regénérer 4 nouveaux employés
                                   -« T », terminer pour sortir du panel de sélection 

-	Un récapitulatif du Staff s’affiche avec le nom des employés cuisiniers et des employés nettoyeurs ainsi que leur efficacité respective et leur salaire.

-	L’utilisateur appuie sur n’importe quelle touche de son clavier suivi de la touche entrée et le jeu commence.

-	A chaque heure, on affiche le nombre de clients dans la file d’attente et le nombre de clients pris attendant leur commande (sur place ou non)

-	Quand un cuisinier est dispo il prend en charge le premier dans la file d’attente et s’occupe de sa commande
-	Après quelques secondes le cuisinier est de nouveau libre et peut prendre la commande d’un autre client.

-	Lorsqu’un client a été servi, il mange pendant un certain temps puis, lorsqu’il a fini,  le montant de ce qu’il doit, est affiché à l’écran et s’ajoute au total de la caisse. 

-	Lorsqu’un client veut manger sur place, il occupe une table, lorsqu’il part, cette table à 1 chance sur 2 de devenir sale, dans ce cas-là, si un nettoyeur est dispo, il la nettoie

-	Si la moitié des tables du restaurant sont sales, le restaurant devient sale. Comme pour les tables un nettoyeur peut intervenir si il est disponible 

-	Le jeu continue, les heures défilent jusqu’à l’heure de fermeture où le restaurant affiche être fermé, l’argent gagné durant la journée s’affiche ainsi que l’argent qu’a rapporté chaque cuisinier et le nombre de tables nettoyés par chaque nettoyeur ( sous forme de classement)

-	L’utilisateur peut choisir quel employé il souhaite promouvoir en indiquant le numéro associé à celui-ci ou entrer « T » pour sortir du panel.

-	L’argent total de la caisse est affiché ainsi que le nombre de tables que dispose le restaurant.

-	L’application propose d’acheter une table, si l’utilisateur choisit cette option, un montant de 600€ est déduit de l’argent de la caisse et une table est ajouté au restaurant, sinon il peut sortir du panel en entrant « T » au clavier.

-	On propose de passer au jour suivant, si on entre « oui » le jeu recommence, on doit indiquer un montant qui sera l’objectif à atteindre durant la journée et  il peut de nouveau embaucher des employés générés aléatoirement

-	Si on entre « non », le jeu est terminé. 
