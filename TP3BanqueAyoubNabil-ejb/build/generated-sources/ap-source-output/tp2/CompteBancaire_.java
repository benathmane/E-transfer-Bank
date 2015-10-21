package tp2;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tp2.OperationBancaire;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-21T09:23:36")
@StaticMetamodel(CompteBancaire.class)
public abstract class CompteBancaire_ { 

    public static volatile ListAttribute<CompteBancaire, OperationBancaire> listeOprerations;
    public static volatile SingularAttribute<CompteBancaire, String> description;
    public static volatile SingularAttribute<CompteBancaire, Integer> solde;
    public static volatile SingularAttribute<CompteBancaire, Integer> id;
    public static volatile SingularAttribute<CompteBancaire, String> nom;

}