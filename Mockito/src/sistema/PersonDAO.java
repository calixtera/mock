package sistema;


//interface de comunicação com o banco de dados
public interface PersonDAO {
    public Person fetchPerson( Integer personID );
    public void update( Person person );
}
