package sistema;

//classe pessoa
public class Person {
	  private final Integer personID;
	    private final String personName;
	    public Person( Integer personID, String personName ) //construtor
	    {
	        this.personID = personID;
	        this.personName = personName;
	    }
	    public Integer getPersonID()
	    {
	        return personID;
	    }
	    public String getPersonName()
	    {
	        return personName;
	    }
}
