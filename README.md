Auteurs du projet : Gripari Alexandre, Lamiri Yannis, Mazzi Yohan, Moutaoukil Ilias.

Etat actuel du projet :
- les 54 cartes de quartiers
- les 11 cartes de merveilles
- les 8 personnages
- règles du jeu respectées (ordre du choix des personnages, un seul quartier de même nom dans chaque cité, ...)
- bonus de fin de partie intégrés
- le magicien ne peut pas prendre ne nombre de cartes voulues dans la pioche et il n'y a pas 30 pièces maximum

Classes implémentées :
>cards
- Card => carte basique avec un nom et une couleur 
- Character (Enum) => les personnages avec leur abilité
- Color (Enum) => les couleurs possibles pour les cartes, commercial, merveille ...
- CardsName (Enum) => les noms des quartiers 
- Construction => les quartiers, cartes auxquelles on donne une valeur
- Wonder => les merveilles, quartier possédant (initialement) la couleur merveille et un pouvoir
- WondersPower (Enum) => les pouvoirs des merveilles
>players
- City => la cité d'un joueur
- Hand => la main d'un joueur
>strategies
- Strategy (abstract) => la classe globale des stratégies qui dictent les actions que les joueurs doivent réaliser
- StrategyAggro => la stratégie agressif
- StrategyEco => la stratégie d'économie d'or
- StrategyRichard => aggressif + spécificités demandées pour ce bot
>others
- Csv => gère l'écriture/lecture dans un fichier de données récupérées dans le programme
- Draw => la pioche
- Game => gère le jeu dans son ensemble, initialise (les joueurs, la pioche, les personnages), fait se dérouler et termine la partie (classement)
- Main => permet le lancement de parties avec le paramètre souhaité (voir dernier point) 
- MyLogger => gère les affichages des actions des joueurs au cours d'une partie et les informations de cette partie
- Player => les joueurs qui possèdent un numéro, leur or, une main, une cité et une stratégie (ainsi que leurs différents score, au sein d'une partie et globalement)

Comment avoir un aperçu de ce que fait notre projet lorque l'on lance une partie ?
On lance la classe Main depuis un terminal avec 3 paramètres possibles :
- twoThousands qui lance 2000 parties => ce qui affichera des statistiques de victoire, défaites, égalité et autres sur le total de ces 2000 parties
- demo qui lance une partie => ce qui affichera toutes les actions menées par les joueurs durant cette unique partie
- csv qui lance 2000 parties => ce qui stockera les statistiques des joueurs sur ces 2000 parties dans un fichier csv

Commande à utiliser : exec:java -Dexec.args="_paramètre_".


