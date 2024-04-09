# Clavier-Logiciel-BE

Ce projet est un bureau d'études réalisé avec l'aide de M. Raynal, chercheur à l'IRIT sur le domaine de l'amélioration des systèmes de saisie de texte. Le but est de réaliser un clavier logiciel à destination de personnes en situation de handicap moteur, utilisant un pointeur à la place d'un clavier physique. L'utilisation d'un système de prédiction linguistique sera utilisée pour simplifier la saisie. De plus, il est important de réduire au maximum la distance parcourue par le pointeur, pour éviter à l'utilisateur de s'épuiser.

Le projet a un but expérimental, me permettant de développer et d'imaginer un nouveau clavier qui répondra au mieux aux critères cités plus haut. J'ai réalisé un état de l'art des claviers logiciels existants, pour mieux appréhender l'élaboration de ma solution. Le clavier que je propose sera structuré selon 3 cercles concentriques. Rangeant les lettres les plus probables dans le cercle le plus au centre et les moins probables sur le cercle extérieur. À chaque touche pressée, on calculera la lettre la plus probable d’être sélectionnée à la suite. Si celle-ci ne se trouve pas dans le cercle le plus au centre alors on va l’introduire à l’intérieur, en échangeant sa place avec l'ancienne lettre. Pour indiquer ce changement, la lettre ajoutée pourra émettre un léger flash lumineux, cela attirera le regard de l’utilisateur, l’aidant à sélectionner la lettre la plus probable. L’objectif est que la prochaine lettre choisie soit toujours le plus au centre pour que l’utilisateur bouge le moins possible son pointeur.

premier prototype :
![Screenshot 2024-03-25 231542](https://github.com/NicolasGiry/BE-ClavierLogiciel-Prediction/assets/114723956/dfeabd96-a5b5-49fd-92f7-42cf5771a63c)

demonstration statique : 

https://github.com/NicolasGiry/BE-ClavierLogiciel-Prediction/assets/114723956/9c7974c2-b38a-4705-9ceb-90aa7f4844aa

demonstration de prediction dynamique du premier caractère le plus probable :

https://github.com/NicolasGiry/BE-ClavierLogiciel-Prediction/assets/114723956/cb9a352a-111a-4299-81f6-9d84a05eb116

demonstration de prediction dynamique des trois premiers caractères les plus probables :

https://github.com/NicolasGiry/BE-ClavierLogiciel-Prediction/assets/114723956/752615ff-5bdb-4b37-9d12-33ed5232ac14

demonstration de prediction dynamique de toutes les touches :

https://github.com/NicolasGiry/BE-ClavierLogiciel-Prediction/assets/114723956/8eec064b-18c0-4c42-ac04-1c40a89e2795
