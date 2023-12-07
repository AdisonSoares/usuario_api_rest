package br.com.adison.api_rest_testes.controller.resources;

import br.com.adison.api_rest_testes.model.domain.Users;
import br.com.adison.api_rest_testes.model.domain.dto.UserDTO;
import br.com.adison.api_rest_testes.model.service.implement.UserServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
class UserResourceTest {
    public static final Integer ID = 1;
    public static final Integer INDEX_ZERO = 0;
    public static final String NOME = "nomeTeste";
    public static final String EMAIL = "emailTeste@gmail.com";
    public static final String PASSWORD = "123";

    private Users users;
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserServiceImplement service;

    /**
     * @Finalidade: Método criado para inicializar os mocks desta/this classe,
     * e iniciar o método que foi feito para criar construtores aos objetos user
     * para não acontecer um lançamento de nullpointerexception.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "findById" que retorna um objeto
     * "ResponseEntity" do tipo "<UserDTO>" de acordo com um id especificado nos parâmetros ou lança
     * uma exception caso nao tenha no banco. (findById)<p>
     *
     * @Nomeação: Quando executar "FindById" retorna um objeto "ResponseEntity" com sucesso, mapeando um
     * objeto "users" para "userDTO". (whenFindByIdThenReturnSucess).<p>
     *
     * @Mockito:
     * - PRIMEIRA: É mockado o chamado do método "findById" da classe "UserServiceImplement"
     * passando qualquer/any número de Id e retornando um objeto "users" com sucesso.<p>
     * - SEGUNDA: É mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o método "findById" da classe "UserResource" passando o ID criado é possível
     * armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "UserDTO" para testar as assertivas.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se o corpo/body do response está nulo.<p>
     * - TERCEIRA: verifica se a classe do response corresponde a uma classe "ResponseEntity.class".<p>
     * - QUARTA: verifica se a classe do corpo do response corresponde a uma classe "UserDTO.class".<p>
     * - QUINTA: verifica se o id do corpo do response corresponde ao ID estático passado.<p>
     * - SEXTA: verifica se o nome do corpo do response corresponde ao NOME_TESTE estático passado.<p>
     * - SETIMA: verifica se o email do corpo do response corresponde ao EMAIL estático passado.<p>
     * - OITAVA: verifica se a senha do corpo do response corresponde ao PASSWORD estático passado.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Recomendação: Para que o teste seja completo é preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os códigos serão.
     */
    @Test
    void whenFindByIdThenReturnSucess() {
        Mockito
                .when(service
                        .findById(Mockito.anyInt()))
                .thenReturn(users);

        Mockito
                .when(mapper
                        .map(any(), any()))
                .thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "findAll" que retorna uma lista
     * de objetos "ResponseEntity" do tipo "<List<UserDTO>>" sem parâmetros. (findAll)<p>
     *
     * @Nomeação: Quando executar "FindAll" retorna um objeto "ResponseEntity" com sucesso, mapeando a lista
     * de objetos "users" para "userDTO" e retornando essa lista no corpo. (whenFindAllThenReturnAListOfUserDTO).<p>
     *
     * @Mockito:
     * - PRIMEIRA: É mockado o chamado do método "findAll" da classe "UserServiceImplement" e retornando
     * uma lista de "users" com sucesso.<p>
     * - SEGUNDA: É mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o método "findAll" da classe "UserResource" é possível armazenar seu
     * retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "<List<UserDTO>>" para
     * testar as assertivas.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se o corpo/body do response está nulo.<p>
     * - TERCEIRA: verifica se a mensagem do response corresponde a "HttpStatus.OK".<p>
     * - QUARTA: verifica se a classe do responde corresponde a "ResponseEntity.class".<p>
     * - QUINTA: verifica se o corpo do response está retornando uma classe "ArrayList.class".<p>
     * - SEXTA: verifica se no primeiro indice do corpo do response está retornado uma classe do tipo "UserDTO.class".<p>
     * - SETIMA: verifica se o id do primeiro indice do corpo do response está sendo retornado o ID estatico.<p>
     * - OITAVA: verifica se o nome do primeiro indice do corpo do response está sendo retornado o NOME estatico.<p>
     * - NONA: verifica se o email do primeiro indice do corpo do response está sendo retornado o EMAIL estatico.<p>
     * - DECIMA: verifica se a senha do primeiro indice do corpo do response está sendo retornado o PASSWORD estatico.<p>
     *
     * @Assertivas_notnull: Verifica se o objeto da classe passada não está nulo.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Recomendação: Para que o teste seja completo é preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os códigos serão.
     */
    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        Mockito
                .when(service
                        .findAll())
                .thenReturn(List.of(users));
        Mockito
                .when(mapper
                        .map(any(), any()))
                .thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(ArrayList.class, response.getBody().getClass());

        Assertions.assertEquals(UserDTO.class, response.getBody().get(INDEX_ZERO).getClass());
        Assertions.assertEquals(ID, response.getBody().get(INDEX_ZERO).getId());
        Assertions.assertEquals(NOME, response.getBody().get(INDEX_ZERO).getName());
        Assertions.assertEquals(EMAIL, response.getBody().get(INDEX_ZERO).getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().get(INDEX_ZERO).getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "create" que retorna uma lista
     * de objetos "ResponseEntity" do tipo "<List<UserDTO>>" tendo como parâmetro um objeto "UserDTO"
     * para ser incluso no banco. (create)<p>
     *
     * @Nomeação: Quando executar "create" lendo o parâmetro com objeto "UserDTO" retorna um objeto
     * "ResponseEntity" com sucesso, mapeando a lista de objetos "users" para "userDTO" e
     * retornando essa lista no corpo. (whenCreateThenReturnCreated).<p>
     *
     * @Mockito:
     * - PRIMEIRA: É mockado o chamado do método "create" da classe "UserServiceImplement" passando qualquer/any
     * parâmetro e retornando uma lista de "users" com sucesso.<p>
     *
     * @Response: Ao chamar o método "create" da classe "UserResource" passando um objeto "userDTO" no parâmetro
     * é possível armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "<UserDTO>"
     * para testar as assertivas.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se a classe do response corresponde a uma classe do tipo "ResponseEntity.class".<p>
     * - SEGUNDA: verifica se o status do response corresponde a um status do tipo "HttpStatus.CREATED".<p>
     * - TERCEIRA: verifica se o "location" do "headers" do response não está vindo nulo, ele precisa de valor
     * pois é a uri para localizar o novo objeto.<p>
     * - QUARTA: verifica se o body/corpo do response está vindo nulo, pois não precisa ter retorno no body.<p>
     *
     * @Assertivas_notnull: Verifica se o objeto da classe passada não está nulo.<p>
     *
     * @Assertivas_null: Verifica se o objeto da classe passada está nulo.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.
     */
    @Test
    void whenCreateThenReturnCreated() {
        Mockito
                .when(service
                        .create(any()))
                .thenReturn(users);
        ResponseEntity<UserDTO> response = resource.create(userDTO);

        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getHeaders().get("Location"));
        Assertions.assertNull(response.getBody());
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "update" que retorna um objeto
     * "ResponseEntity" do tipo "<UserDTO>" de acordo com um id e um objeto do tipo "UserDTO" especificado
     * nos parâmetros atualizando no banco o objeto especificado. (update)<p>
     *
     * @Nomeação: Quando executar "update" retorna um objeto "ResponseEntity" com sucesso no corpo da solicitação,
     * mapeando um objeto "users" para "userDTO". (whenUpdateThenReturnSucess).<p>
     *
     * @Mockito:
     * - PRIMEIRA: É mockado o chamado do método "update" da classe "UserServiceImplement"
     * passando o objeto estático "userDTO" e retornando um objeto "users" com sucesso.<p>
     * - SEGUNDA: É mockado o mapeamento de qualquer tipo de objeto para qualquer tipo de objeto para
     * retornar um objeto do tipo "userDTO" com sucesso.<p>
     *
     * @Response: Ao chamar o método "update" da classe "UserResource" passando o ID e userDTO estatico criados
     * é possível armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "UserDTO"
     * para testar as assertivas.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se o corpo/body do response está nulo.<p>
     * - TERCEIRA: verifica se a mensagem do response corresponde a "HttpStatus.OK".<p>
     * - QUARTA: verifica se a classe do responde corresponde a "ResponseEntity.class".<p>
     * - QUINTA: verifica se o corpo do response está retornando uma classe "ArrayList.class".<p>
     * - SEXTA: verifica se no primeiro indice do corpo do response está retornado uma classe do tipo "UserDTO.class".<p>
     * - SETIMA: verifica se o id do primeiro indice do corpo do response está sendo retornado o ID estatico.<p>
     * - OITAVA: verifica se o nome do primeiro indice do corpo do response está sendo retornado o NOME estatico.<p>
     * - NONA: verifica se o email do primeiro indice do corpo do response está sendo retornado o EMAIL estatico.<p>
     * - DECIMA: verifica se a senha do primeiro indice do corpo do response está sendo retornado o PASSWORD estatico.<p>
     *
     * @Assertivas_notnull: Verifica se o objeto da classe passada não está nulo.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.
     *
     * @Recomendação: Para que o teste seja completo é preciso verificar todos os atributos, quanto mais
     * atributos testados mais seguro o sistema e os códigos serão.
     */
    @Test
    void whenUpdateThenReturnSucess() {
        Mockito
                .when(service
                        .update(userDTO))
                .thenReturn(users);
        Mockito
                .when(mapper
                        .map(any(), any()))
                .thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(ID, response.getBody().getId());
        Assertions.assertEquals(NOME, response.getBody().getName());
        Assertions.assertEquals(EMAIL, response.getBody().getEmail());
        Assertions.assertEquals(PASSWORD, response.getBody().getPassword());
    }

    /**
     * @Funcionalidade_original_testada: Esse método é para testar o "delete" que retorna um objeto
     * "ResponseEntity" do tipo "<UserDTO>" de acordo com um id e um objeto do tipo "UserDTO" especificado
     * nos parâmetros atualizando no banco o objeto especificado. (update)<p>
     *
     * @Nomeação: Quando executar "delete" retorna um objeto "ResponseEntity" vazio no corpo
     * da solicitação deletando o objeto indicado no id dos parametros. (whenDeleteThenReturnSucess).<p>
     *
     * @Mockito:
     * - PRIMEIRA: É mockado o chamado do método "delete" da classe "UserServiceImplement" passando
     * qualquer valor inteiro de id, porém como não tem retorno é indicado para nao fazer nada/doNothing.<p>
     *
     * @Response: Ao chamar o método "delete" da classe "UserResource" passando o ID estatico criado
     * é possível armazenar seu retorno "ResponseEntity" com o "status", "headers" e "body" do tipo "UserDTO"
     * para testar as assertivas.<p>
     *
     * @Assertivas:
     * - PRIMEIRA: verifica se o response está nulo.<p>
     * - SEGUNDA: verifica se o corpo/body do response está nulo.<p>
     * - TERCEIRA: verifica se a classe do responde corresponde a "ResponseEntity.class".<p>
     * - QUARTA: verifica se a operacao foi bem sucedida mas sem conteúdo de retorno.<p>
     *
     * @Verify:
     * - PRIMEIRA: verifica se o metodo delete está sendo chamado apenas uma vez para deletar.<p>
     *
     * @Assertivas_notnull: Verifica se o objeto da classe passada não está nulo.<p>
     *
     * @Assertivas_equals: Na primeira parte o atributo que deveria retornar e na segunda
     * o que está retornando.<p>
     *
     * @Mockito.verify: Essa função é usada para verificar se um determinado método em um
     * mock foi chamado um número específico de vezes.<p>
     *
     */
    @Test
    void whenDeleteThenReturnSucess() {
        Mockito
                .doNothing()
                .when(service)
                        .delete(anyInt());

        ResponseEntity<UserDTO> response = resource.delete(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(service, Mockito.times(1)).delete(anyInt());
    }

    /**
     * @Finalidade: Método de inicialização dos objetos users, caso não sejam iniciados
     * ao serem usados acontece uma exception do tipo nullpointerexception.<p>
     */
    private void startUser(){
        users = new Users(ID, NOME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NOME, EMAIL, PASSWORD);
    }
}