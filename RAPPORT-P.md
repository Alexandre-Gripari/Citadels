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

_Les fonctionnalités_ :

Toutes les règles et fonctionnalités ont été implémentées mis à part la possibilité pour le magicien d'échanger un nombre de carte voulu avec la pioche, il échange alors toutes ces cartes avec la pioche et il n'y pas les 30 pièces maximales par partie.

_Les logs_ :

Plutôt que de créer un attribut logger à chaque classe, nous avons préférer créer une classe logger et créer une variable d'instance à chaque fois que nous en avions le besoin.

_Le csv_:

Si le dossier stats n'existe pas notre programme le crée pour y mettre un fichier gamestats.csv contenant les statistiques de la partie. Si le dossier stats existe mais que le fichier qu'il devrait contenir n'existe pas, seul le fichier est créé. Si le chemin complet existe alors le fichier gamestats.csv est mis à jour.

_Le bot Richard_ :

En dehors des spécifités demandées pour ce bot, il agira comme le bot aggressif en héritant de lui, c'est-à-dire qu'il privilégiera poser des quartiers à bas coûts pour finit le plus vite possible.
Notre meilleur bot est le bot eco, il bat le bot Richard car celui-ci a un comportement proche du bot agressif, il pose des quartiers à bas coûts pour obtenir le bonus de fin de partie de 4 points, seulement le bot eco en privilégiant l'or pose des quartiers avec une valeurs et des effets plus importants, ce qui dépasse le bonus précedemment cité.

_L'architecture du programme_ :

Dix-neuf classes au total (sans les classes tests).

La classe __Card__ désigne un objet avec un nom (__CardsName__) et une __Couleur__.

L'Enum __CardsName__ contient les noms des cartes (quartiers, merveilles).

L'Enum __Couleur__ représente les couleurs de cartes disponibles dans Citadelles (Commercial, Noble, Religieux, Soldatesque et Merveilleux)

La Classe __Construction__ hérite de __Card__ et représente les quartiers, ils possèdent une valeur.

La classe __Wonder__ hérite alors elle-même de __Construction__ avec comme couleur initiale _Merveilleux_ et possède un pouvoir.

C'est l'Enum __WondersPower__ qui implémente ces pouvoirs.

La classe __Player__ possède une cité (__City__) et une main (__Hand__) ainsi qu'un personnage (__Character__).

__City__ et __Hand__ sont des listes de constructions (__Construction__).

__Character__ est un Enum contenant les personnages et leurs abilités.

Les sous-classes de __Strategy__ sont un attribut des joueurs (__Player__), les joueurs appellent les stratégies pour le choix des actions qu'ils effectuent à l'aide de leurs méthodes.

Les stratégies redéfinissent les méthodes de la classe parente __Strategy__ pour redéfinir les choix des personnages, ce qu'ils font avec ces personnages, leur choix en début de tour et l'utilisation de leurs merveilles en fonction de la stratégie. Ce qui rend la modification/suppression/ajout de nouvelles stratégies simple. En effet il suffit d'implémenter les différentes méthodes de choix dans une nouvelle classe héritant de __Strategy__ et de mettre en attribut à __Player__ une instance de cette nouvelle stratégie.

La __StrategyAggro__ a pour objectif de terminer au plus vite la partie afin d'obtenir le bonus de fin de partie en prenant des quartiers lorsque sa main est vide et de l'or sinon. Il pose alors les quartiers les moins chers de sa main. Il essayera de dépenser le moins d'or possible, par exemple, lorsqu'il joue avec le condottiere, il détruit le quartier avec le moins de valeur.

La __StrategyEco__ a pour objectif d'accumuler de l'or pour poser des quartiers avec une valeur importante, notamment les merveilles. Pour atteindre cet objectif, il choisira le personnage pouvant lui rapporter le plus d'or selon les quartiers présents dans sa cité. Par exemple : le condotierre pour les quartiers soldatesque, le roi pour les quartiers noble etc... 

La classe __Draw__ permet de gérer la pioche.

La classe __Game__ tient le rôle de chef d'orchestre, il gère le jeu dans son ensemble, initialise (les joueurs, la pioche, les personnages), fait se dérouler et termine la partie (classement).

La classe __MyLogger__ gère le stockage et l'affichage des actions effectuées au cours d'une partie par les joueurs ainsi que l'affichage des informations de cette partie.

La classe __Csv__ permet de gérer la lecture/écriture dans un fichier csv de données, ici les statistiques des joueurs dans les différentes parties jouées.

Enfin la classe __Main__ permet en fonction d'un paramètre donné d'effectuer diverse actions :
- twoThousands qui lance 2 x 1000 parties => ce qui affichera des statistiques de victoire, défaites, égalité et le score moyen sur le total de ces 2 x 1000 parties
- demo qui lance une partie => ce qui affichera toutes les actions menées par les joueurs durant cette unique partie
- csv qui lance 2000 parties => ce qui stockera les statistiques des joueurs sur ces 2000 parties dans un fichier csv

Nos choix ont eu pour but de séparer au maximum les responsabilités et de pouvoir facilement apporter des modifications et/ou des ajouts aux différentes parties du programme.
Comme expliqué pour les stratégies précedemment ou encore pour la cité et la main d'un joueur qui sont des classes.

_Les infos de la java doc, des points et classes importants_ ; elles se trouvent dans le Readme.

_Etat de la base du code_ :

Responsabilités biens attribuées dans l’ensemble

Bonne séparation des classes et des méthodes

Ajout/Suppression de stratégies simples

/Cependant

Capacités des merveilles et des personnages utilisées directement par les stratégies et non par les joueurs

Méthodes des capacités des merveilles et des personnages possèdent des signatures différentes

Classe Main conséquente

_Les rôles dans l'équipe_ :

Alexandre : choix des personnages stratégie agressive/Richard, logger

Yanis : stratégie Eco et fichier CSV

Yohan : squelette des stratégies et utilisation des personnages dans stratégie agressive, bonus de fin de partie

Ilias : stratégie agressive, stratégie Richard

En commun : Character, WondersPower

_Process de l'équipe_ :

Une milestone pour chaque fonctionnalité majeure/distincte avec un nombre important d'issues :
pour amener de la valeur
pour amener des correctifs

Notre utilisation des branches git :
Pour chaque milestone, on utilise des branches pour les fonctionnalités, ce qui permet de développer les fonctionnalités en simultanées. 
On utilise ensuite une branche debug qui correspond au git flow dans laquelle on merge les différentes branches de fonctionnalités (après les avoir testés), on résout les conflits et on debug.
Enfin on envoie tout dans la branche main qui est donc la branche principale toujours stable.












