_Objectif_ : recréer le jeu citadelle (dans sa première version) sur lequel s'affronte des bots.

_Le déroulement du projet_ :

Nous avons développé intégralement en java. 

I) Le premier objectif a été de faire s'affronter deux bots avec une même logique simple ;
- les deux bots peuvent prendre des pièces, des quartiers et les jouer.
- si leur main est vide, ils piochent 2 quartiers et choisissent le moins cher
- sinon ils récupèrent 2 pièces d'or
- dans tous les cas, ils construisent le premier quartier pour lequel ils ont assez d'or.

La partie se termine lorsqu'un joueur possède 3 quartiers dans sa cité.
On affiche les actions des actions joueurs, ainsi que leur or et leur cité à la fin de leur tour.

II) Sur cette base ont été ajoutés les personnages, leur pouvoir, ainsi que leurs effets sur la partie :
- le joueur ayant le roi tire la carte personnage en premier au tour d'après
- l'ordre de jeu selon le numéro du personnage
- les joueurs utilisent les capacité du personnage qu'ils possèdent dès que possible

Désormais la partie se termine à partir de huit quartiers posés.

III) De la même manière, ajout des cartes merveilles et de leur pouvoir, les bots utilisent le pouvoir de leur carte à chaque fois que cela est possible.
Deux bots supplémentaires jouent désormais.

IV) Une fois le jeu stable et les règles du jeu implémentées, les stratégies ont pu être intégrées.
- création des stratégies qui décident des actions que les joueurs doivent effectuer.
- elles prennent la responsabilité des choix en fonction des paramètres de la partie

V) Enfin, en simultané :
- pour chaque partie, les résultats des différents joueurs sont enregistrés dans un fichier csv
- implémente le bonus de fin de partie qui permet de gagner 3 points si l'on possède toutes les couleurs dans la main ainsi que celui de 4 points lorsque l'on finit en premier et 2 points pour ceux finissant le même tour.
- Créer un logger pour pouvoir gérer les sorties (1000 parties avec le meilleur bot contre les autres et 1000 parties contre lui-même, les deux n'ont pas de log de partie, juste des statistiques nb parties gagnées, nb parties perdues, nb parties nulles en % et
  score moyen ou Une partie détaillée pour la démonstration avec les logs d'une seul partie)

_L'architecture du programme_ :

Dix-huit classes au total (sans les classes tests).

La classe __Card__ désigne un objet avec un nom est une __Couleur__.

L'Enum __Couleur__ représente les couleurs de cartes disponibles dans citadelle (Commercial, Noble, Religieux, Soldatesque et Merveilleux)

La Classe __Construction__ hérite de __Card__ et représente les quartiers, ils possèdent une valeur.

La classe __Wonder__ hérite alors elle-même de __Construction__ avec comme couleur initiale _Merveilleux_ et possède un pouvoir.

C'est l'Enum __WondersPower__ qui implémente ces pouvoirs.

La classe __Player__ possède une cité (__City__) et une main (__Hand__) ainsi qu'un personnage (__Character__).

__City__ et __Hand__ sont des listes de constructions (__Construction__).

__Character__ est un Enum contenant les personnages et leurs abilités.

Les sous-classes de __Strategy__ sont un attribut des joueurs (__Player__), les joueurs appellent les stratégies pour le choix des actions qu'ils effectuent à l'aide de leurs méthodes.

Les stratégies redéfinissent les méthodes de leur classe parente __Strategy__ pour redéfinir les choix des personnages, ce qu'ils font avec ces personnages, leur choix en début de tour et l'utilisation de leurs merveilles en fonction de la stratégie.

La classe __Draw__ permet de gérer la pioche.

La classe __Game__ tient le rôle de chef d'orchestre, il gère le jeu dans son ensemble, initialise (les joueurs, la pioche, les personnages), fait se dérouler et termine la partie (classement).

La classe __MyLogger__ gère le stockage et l'affichage des actions effectuées au cours d'une partie par les joueurs.

La classe __Csv__ permet de gérer la lecture/écriture dans un fichier csv de données, ici les statistiques des joueurs dans les différentes parties jouées.

Enfin la classe __Main__ permet en fonction d'un paramètre donné d'effectuer diverse actions :
- twoThousands qui lance 2000 parties => ce qui affichera des statistiques de victoire, défaites, égalité et autres sur le total de ces 2000 parties
- demo qui lance une partie => ce qui affichera toutes les actions menées par les joueurs durant cette unique partie
- csv qui lance 2000 parties => ce qui stockera les statistiques des joueurs sur ces 2000 parties dans un fichier csv
