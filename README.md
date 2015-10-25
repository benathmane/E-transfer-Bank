<B>TP4 à rendre Le 2/11 à 8h pour le MBDS 2015-2016</B>

Vous devez rendre le TP4 complet avec les ajouts suivants :

- Template avec joli Layout, logo, etc...
 
- Beaucoup de comptes et beaucoup d'opérations (table des opérations aussi en Lazy Layout)
 
- On ne peut accèder à rien tant qu'on est pas loggué. Gérer la connexion comme vu en cours (cf le projet JSFLoginPassword) avec un JSF bean en SessionScoped (penser à le mettre Serializable et avoir la librairie CDI dans le projet)
 
- Lazy loading des comptes, essayer de faire marcher le tri et les filtres (par colonne, global)
 
- Afficher les soldes négatifs en rouge (cf projet Goualmi)
 
- Avoir des comptes Epargne et des comptes courants

- Pour les dégourdis: trouver un système pour que les comptes épargne s'incrémentent toutes les XXX secondes en appliquant le taux d'épargne (EJB timers tester avec NetBeans..., PrimeFaces AjaxCore/Poll, etc.) 
 
- Avoir un système ergonomique pour transférer de l'argent (par exemple: champ avec auto completion de PrimeFaces)
 
- Utiliser les ViewParams quand on navigue d'une page à l'autre, utiliser SessionScoped uniquement pour l'authentification (login/password)
 
- Système ergonomique pour supprimer des comptes (selection de lignes et menu contextuel click droit cf PrimeFaces datatable/selection ou joli bouton avec icone de poubelle). Utiliser un widget de dialogue PrimeFaces pur confirmation Oui/Non.
 
- Joli menu sur la gauche, par exemple avec des panels Primefaces en accordéon
 
- Obligation d'utiliser au moins 5 composants PrimeFaces non vus en cours.

Réalisé par : 
- BENATHMANE Ayoub<br/>
- BOUGHZAF Nabil<br/>