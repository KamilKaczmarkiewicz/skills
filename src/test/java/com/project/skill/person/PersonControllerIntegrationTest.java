package com.project.skill.person;


import com.project.skill.TestContainersConfig;
import com.project.skill.person.dto.CreatePersonRequest;
import com.project.skill.person.dto.PersonDto;
import com.project.skill.person.dto.UpdatePersonRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerIntegrationTest extends TestContainersConfig {

    private static final String BASE_URL = "/api/v1/persons";

    @Test
    void crudOperationsIntegrationTest() throws Exception {
        // Step 1: GET all persons. Expect an empty list initially.
        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());

        // Step 2: Create a new Person using POST.
        var createReq = new CreatePersonRequest("Jon", "Wood", "Beans", LocalDate.of(1992, 1, 21));
        var createReqJson = mapper.writeValueAsString(createReq);
        var createResp = mvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createReqJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Jon")))
                .andExpect(jsonPath("$.surname", is("Wood")))
                .andExpect(jsonPath("$.company", is("Beans")))
                .andExpect(jsonPath("$.birthDate", is("1992-01-21")))
                .andReturn()
                .getResponse()
                .getContentAsString();
        var createdPerson = mapper.readValue(createResp, PersonDto.class);
        var personId = createdPerson.id();

        // Step 3: GET the newly created person by ID and verify the returned data.
        mvc.perform(get("%s/%s".formatted(BASE_URL, personId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(personId.toString())))
                .andExpect(jsonPath("$.name", is("Jon")))
                .andExpect(jsonPath("$.surname", is("Wood")))
                .andExpect(jsonPath("$.company", is("Beans")))
                .andExpect(jsonPath("$.birthDate", is("1992-01-21")));

        // Step 4: GET all persons and confirm that the list contains exactly 1 record.
        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));

        // Step 5: Update the created person using PUT.
        var updateReq = new UpdatePersonRequest("John", "Woody", "Beans2");
        var updateReqJson = mapper.writeValueAsString(updateReq);
        mvc.perform(put("%s/%s".formatted(BASE_URL, personId))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateReqJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.surname", is("Woody")))
                .andExpect(jsonPath("$.company", is("Beans2")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Step 6: GET the updated person by ID and verify the changes.
        mvc.perform(get("%s/%s".formatted(BASE_URL, personId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.surname", is("Woody")))
                .andExpect(jsonPath("$.company", is("Beans2")));

        // Step 7: Delete the person using DELETE.
        mvc.perform(delete("%s/%s".formatted(BASE_URL, personId)))
                .andExpect(status().isNoContent());

        // Step 8: GET all persons again and expect the list to be empty.
        mvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isEmpty());
    }
}