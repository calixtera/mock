package sistema;

//classe que define rotina de update do banco de dados

public class PersonService {
	private final PersonDAO personDAO;
    public PersonService( PersonDAO personDAO ) //construtor
    {
        this.personDAO = personDAO;
    }
    
    public boolean update( Integer personId, String name ){ //função que efetua a rotina de update
        Person person = personDAO.fetchPerson( personId ); //recebe o 'ID' do um objeto pessoa a ser atualizado no banco
        if( person != null ) //verifica a existência desse objeto
        {
            Person updatedPerson = new Person( person.getPersonID(), name ); //cria um novo objeto do tipo pessoa 
            personDAO.update( updatedPerson ); //realiza o update no objeto
            return true;
        }
        else
        {
            return false;
        }
    }
}
