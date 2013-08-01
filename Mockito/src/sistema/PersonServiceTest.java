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
		    
		    @Before //define um comportamente a ser seguido durante a execu��o dos testes
		    public void setUp()
		        throws Exception //lan�a exce��o
		    {
		        MockitoAnnotations.initMocks( this );
		        personService = new PersonService( personDAO );
		    }
		    @Test
		    public void shouldUpdatePersonName() //testa se � possivel dar 'update' no nome de um objeto do tipo pessoa
		    {
		        Person person = new Person( 1, "Phillip" ); //define um novo objeto do tipo pessoa cujo 'ID' � 1 e o nome � 'Phillip'
		        when( personDAO.fetchPerson( 1 ) ).thenReturn( person ); //busca no banco o objeto pessoa com 
		        boolean updated = personService.update( 1, "David" ); //lan�a o m�todo para realizar update de nome na pessoa de 'ID = 1'
		        assertTrue( updated ); //retorna true caso a fun�nao seja utilizada
		        verify( personDAO ).fetchPerson( 1 ); //assegura que o objeto � o de 'ID = 1'
		        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass( Person.class );
		        verify( personDAO ).update( personCaptor.capture() );
		        Person updatedPerson = personCaptor.getValue();
		        assertEquals( "David", updatedPerson.getPersonName() ); //verifica se o novo nome � 'david'
		        verifyNoMoreInteractions( personDAO );  // assegura que durante o teste o objeto mock n�o possui outras chamadas
		    }
		    @Test
		    public void shouldNotUpdateIfPersonNotFound() //testa se � poss�vel fazer 'update' em objeto do tipo pessoa inexistente(null)
		    {
		        when( personDAO.fetchPerson( 1 ) ).thenReturn( null ); //define que deve ser retornado null ao objeto do tipo pessoa de 'ID = 1' no banco de dados
		        boolean updated = personService.update( 1, "David" ); //lan�a o m�todo para realizar update de nome na pessoa de 'ID = 1'
		        assertFalse( updated ); //retorna false caso o update seja bem sucedido
		        verify( personDAO ).fetchPerson( 1 ); //assegura que o objeto � o de 'ID = 1'
		        verifyZeroInteractions( personDAO ); //assegura que n�o houveram intera��es com o objeto mock
		        verifyNoMoreInteractions( personDAO ); //assegura que durante o testo o objeto mock n�o possui outras chamadas
		    }
		} 
