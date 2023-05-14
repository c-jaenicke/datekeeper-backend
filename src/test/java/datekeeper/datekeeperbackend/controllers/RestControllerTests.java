package datekeeper.datekeeperbackend.controllers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.*;
import datekeeper.datekeeperbackend.modells.Event;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Get ping endpoint, expect 418")
    public void pingTest() throws Exception {
        this.mockMvc.perform(get("/ping"))
                .andDo(print())
                .andExpect(status().isIAmATeapot())
                .andExpect(content().string(containsString("pong")));
    }

    @Test
    @DisplayName("Get events without specifying an even id. expect 404")
    public void noEventTest() throws Exception {
        this.mockMvc.perform(get("/events/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Create a new event using the /new endpoint with an event in body, formatted as json")
    public void newEventTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"TEST EVENT TITLE\",\"description\":\"TEST EVENT DESCRIPTION\",\"eventDate\":\"2023-05-16\"}")
        )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Get all events")
    public void getEvents() throws Exception {
        this.mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TEST EVENT TITLE")));
    }

    @Test
    @DisplayName("Get all events, find 'TEST EVENT TITLE' event and delete it")
    public void deleteEvent() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TEST EVENT TITLE")))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        ArrayList<Event> eventList = objectMapper.readValue(content, new TypeReference<>() {
        });
        for (Event event : eventList) {
            if (event.getTitle().equals("TEST EVENT TITLE")) {
                this.mockMvc.perform(delete(String.format("/event/%d/delete", event.getId())))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().string(not(containsString("TEST EVENT TITLE"))));
            }
        }
    }
}
