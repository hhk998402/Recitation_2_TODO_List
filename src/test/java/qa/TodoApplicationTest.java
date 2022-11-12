package qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

class TodoApplicationTest {

	private TodoApplication todoApp;

	private PersonService personServiceMock;

	private TodoService todoServiceMock;


	@BeforeEach
	void setup() {
		String dummyUsername = "dummyUsername1";
		personServiceMock = mock(PersonService.class);
		todoServiceMock = mock(TodoService.class);
		Mockito.lenient().when(personServiceMock.findUsernameById(123L)).thenReturn(dummyUsername);
		Mockito.lenient().when(personServiceMock.findUsernameById(234L)).thenReturn("noDataUsername");
		Mockito.lenient().when(todoServiceMock.addTodo(dummyUsername, "newTodo")).thenReturn(true);
		Mockito.lenient().when(todoServiceMock.retrieveTodos(dummyUsername)).thenReturn(new ArrayList<>(Arrays.asList(
				"todo1",
				"todo2",
				"todo3"
		)));
		Mockito.lenient().when(todoServiceMock.retrieveTodos("noDataUsername")).thenReturn(new ArrayList<>());
		Mockito.doNothing().when(todoServiceMock).completeTodo("todo1");
		Mockito.doNothing().when(todoServiceMock).completeTodo("todo2");
		Mockito.doNothing().when(todoServiceMock).completeTodo("todo3");

		todoApp = new TodoApplication(todoServiceMock, personServiceMock);
	}

	@Test
	void addTodoTest() {
		Assertions.assertTrue(todoApp.addTodo(123L, "newTodo"));
		Mockito.verify(todoServiceMock).addTodo("dummyUsername1", "newTodo");
	}

	@Test
	void completeAllTodosTest() {
		todoApp.completeAllTodos(123L);
		Mockito.verify(todoServiceMock).retrieveTodos("dummyUsername1");
		Mockito.verify(todoServiceMock).completeTodo("todo1");
		Mockito.verify(todoServiceMock).completeTodo("todo2");
		Mockito.verify(todoServiceMock).completeTodo("todo1");

	}

	@Test
	void completeAllTodosWithNoNotes() {
		todoApp.completeAllTodos(234L);
		Mockito.verify(todoServiceMock, never()).completeTodo(any());
	}


}
