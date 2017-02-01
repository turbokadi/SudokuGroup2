# Projet Sudoku 3IL Ingenieur

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9815384c2be34895b5556012c3bcce66)](https://www.codacy.com/app/J4BB3R/SudokuGroup2?utm_source=github.com&utm_medium=referral&utm_content=J4BB3R/SudokuGroup2&utm_campaign=badger)

Groupe2 promo 2016

# Régles relative à Git
  - On bosse sur des branches, pas de glands sur le master
  - On commite pas dessus sauf si vous savais se que vous faite...
  - Sous Linux si possible, Git sous windows ça marche pas top (Et sa viens pas de Git ... MicroPenis sa pue)

# Cycle de production typique

  - Clonage du repo => git clone https://github.com/J4BB3R/SudokuGroup2.git // On le fais qu'une fois biensur ;)
  - Création de vôtre branche (une taches une branche) => git checkout -b nomDeVotreBranche
  - Vous taffer + commiter vos modifs => git add vosFichier && git commit -m "votreCom" <- On met toujours un Com
  - Vous revenez sur le master et vous puller les modif remote => git checkout origin master && git pull
  - Vous revenez sur votre branche => git checkout votreBranche
    /!\ si ya conflis il faut merger a la mano, je vous conseille meld => git mergetool -t meld
  - On pousse son travail en Merge Request => git push origin votreBranche
  - On attend que la valid sois faite par quelqu'un pour que ça parte sur le master...
  - On pull son master pour récup sa modif => git pull origin master

# Coding Rules

  - On met des variables explicites...!

  - On essaye de repérer les groupes d'attributs et on nomme leurs utilisations dans le code Ex :

      - // Component Configuration Attribute
      - private final static int gridPanelOffsetWidth = 63, gridPanelOffsetHeight = 63;
      - private final static int gridPanelWidth = 274, gridPanelHeight = 274;

  - On fait pareil pour des méthodes ambigues ou groupes de méthodes indigestes Ex :

      - // Grib Panel Instanciation;
      - gridPanel = new JPanel();
      - gridPanel.setName("gridPanel");
      - gridPanel.setLocation(new Point(gridPanelOffsetWidth,gridPanelOffsetHeight));
      - gridPanel.setSize(gridPanelWidth, gridPanelHeight);

  - On ne créer pas de variables quand c'est pas utile, sa surcharge pour rien

  - On fait un header (en Anglais) avec au minimum l'auteur et la date initiale, Ex a peut prés complet :


      - //-- Create by J4BB3R<<johanmaurel@gmail.com>>; --//
      - [Contributors] none;
      - [Initial Date] : 08/12/2016
      - [Last Date] : 09/12/2016
      - [Description] :
           - This class is the game view;
      - [Increments] :
           - 09/12/2016 [v0.1] : Creation of the views;
