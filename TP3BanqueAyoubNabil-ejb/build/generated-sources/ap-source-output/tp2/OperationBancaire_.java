package tp2;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-21T09:23:36")
@StaticMetamodel(OperationBancaire.class)
public class OperationBancaire_ { 

    public static volatile SingularAttribute<OperationBancaire, String> description;
    public static volatile SingularAttribute<OperationBancaire, Integer> montant;
    public static volatile SingularAttribute<OperationBancaire, Integer> id;
    public static volatile SingularAttribute<OperationBancaire, Date> dateOperation;

}