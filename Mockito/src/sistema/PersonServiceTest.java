package sistema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonServiceTest {

	
		 @Mock //definem os objetos a serem mockados
		    private PersonDAO personDAO;
		    private PersonService personService;
		    
		    @Before //define um comportamente a ser seguido durante a execução dos testes
		    public void setUp()
		        throws Exception //lança exceção
		    {
		        MockitoAnnotations.initMocks( this );
		        personService = new PersonService( personDAO );
		    }
		    @Test
		    public void shouldUpdatePersonName() //testa se é possivel dar 'update' no nome de um objeto do tipo pessoa
		    {
		        Person person = new Person( 1, "Phillip" ); //define um novo objeto do tipo pessoa cujo 'ID' é 1 e o nome é 'Phillip'
		        when( personDAO.fetchPerson( 1 ) ).thenReturn( person ); //busca no banco o objeto pessoa com 
		        boolean updated = personService.update( 1, "David" ); //lança o método para realizar update de nome na pessoa de 'ID = 1'
		        assertTrue( updated ); //retorna true caso a funçnao seja utilizada
		        verify( personDAO ).fetchPerson( 1 ); //assegura que o objeto é o de 'ID = 1'
		        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass( Person.class );
		        verify( personDAO ).update( personCaptor.capture() );
		        Person updatedPerson = personCaptor.getValue();
		        assertEquals( "David", updatedPerson.getPersonName() ); //verifica se o novo nome é 'david'
		        verifyNoMoreInteractions( personDAO );  // assegura que durante o teste o objeto mock não possui outras chamadas
		    }
		    @Test
		    public void shouldNotUpdateIfPersonNotFound() //testa se é possível fazer 'update' em objeto do tipo pessoa inexistente(null)
		    {
		        when( personDAO.fetchPerson( 1 ) ).thenReturn( null ); //define que deve ser retornado null ao objeto do tipo pessoa de 'ID = 1' no banco de dados
		        boolean updated = personService.update( 1, "David" ); //lança o método para realizar update de nome na pessoa de 'ID = 1'
		        assertFalse( updated ); //retorna false caso o update seja bem sucedido
		        verify( personDAO ).fetchPerson( 1 ); //assegura que o objeto é o de 'ID = 1'
		        verifyZeroInteractions( personDAO ); //assegura que não houveram interações com o objeto mock
		        verifyNoMoreInteractions( personDAO ); //assegura que durante o testo o objeto mock não possui outras chamadas
		    }
		} 
