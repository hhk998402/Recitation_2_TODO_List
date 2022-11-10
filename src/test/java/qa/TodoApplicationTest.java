package qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

class TodoApplicationTest {

	private TodoApplication todoApp;

	private PersonService personServiceMock;

	private TodoService todoServiceMock;


	@BeforeEach
	void setup() {
		//TODO
	}

	@ParameterizedTest
	@CsvSource({"true", "false"})
	void addTodoTest(boolean returnValue) {
		//TODO
	}

	@Test
	void completeAllTodosTest() {
		//TODO
	}

	@Test
	void completeAllTodosWithNoNotes() {
		//TODO
	}


}
