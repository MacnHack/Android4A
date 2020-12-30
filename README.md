# Android4A

## Présentation

Ce projet de programmation mobile de 4A est une application Android codé en Kotlin.
Elle permet, grâce à une API, d'afficher différents Pokemon.
On peut aussi sélectionner un Pokemon afin de faire apparaître à l'écran des informations le concernant

## Consigne minimale :

  - Language Kotlin / Swift si iOS
  - Architecture MVVM
  - Clean Architecture
  - Utilisation d’une réelle BDD
  - Utilisation Api REST
  - Affichage d’une liste
  - Design

## Ajout :

  - Affichage du détail des éléments de la liste
  - Mode Sombre (sauf affichage des informations du Pokemon)
  - Gitflow rudimentaire

## L'application :

### Premier écran

  - Ecran de connexion : il est possible de créer un compte ou de se connecter si on en possède déjà un.
    - Si, lors de la connexion, la combinaison Login/Password n'existe pas, on obtient une erreur.
    - Si, lors de la création de compte, le login est déjà utilisé, on obtient une erreur.

<img src="Images/LoginPage.jpg" alt="loginpage" width="30%" height="30%">



### Second écran

  - Une fois connecté, on obtient une liste de Pokemon :

<img src="Images/ListePokemon.jpg" alt="listepokemon" width="30%" height="30%">

  - Si l'on se risque (soyons fou) à appuyer sur un Pokémon on obtient des informations complémentaires sur ce dernier :
  
<img src="Images/ivysaur.jpg" alt="ivysaur" width="30%" height="30%">
  
  - Sur cette page, on peut :
    - Retourner en arrière en appuyant sur la flèche en haut à droite.
    - Allez voir une évolution précédente (si il en existe) en appuyant sur le nom de l'évolution.
    - Ou encore, allez voir une évolution suivante (toujours si il en existe) en appuyant sur le nom de l'évolution.

<img src="Images/bulbasaur.jpg" alt="bulbasaur" width="30%" height="30%"> <img src="Images/venusaur.jpg" alt="venusaur" width="30%" height="30%">
